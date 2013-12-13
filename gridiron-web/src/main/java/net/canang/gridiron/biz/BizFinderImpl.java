package net.canang.gridiron.biz;

import com.sun.org.apache.regexp.internal.RE;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequenceFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import net.canang.gridiron.core.dao.DistrictDao;
import net.canang.gridiron.core.dao.FenceDao;
import net.canang.gridiron.core.dao.NodeDao;
import net.canang.gridiron.core.model.*;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Component("bizFinder")
@Transactional
public class BizFinderImpl implements BizFinder {

    private Logger log = LoggerFactory.getLogger(BizFinderImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private FenceDao fenceDao;
    public static final WKTReader READER = new WKTReader();

    @Override
    public District findDistrictById(Long id) {
        return decorate(districtDao.findById(id));
    }

    @Override
    public Node findNodeById(Long id) {
        return nodeDao.findById(id);
    }

    @Override
    public Fence findFenceById(Long id) {
        return decorate(fenceDao.findById(id));
    }

    @Override
    public List<District> findDistricts() {
        return decorateDistrict(districtDao.find());
    }

    @Override
    public List<Fence> findFences() {
        return fenceDao.find();
    }

    @Override
    public List<Fence> findFencesWithinDistrict(District district) {
        return decorateTurfs(districtDao.findFencesWithin(district));
    }

    @Override
    public List<Node> findNodesWithinDistrict(District district) {
        return districtDao.findNodesWithin(district);
    }

    @Override
    public List<Node> findNodesWithinFence(Fence fence) {
        return fenceDao.findNodesWithin(fence);
    }

    @Override
    public List<Node> findNodes() {
        return nodeDao.find();
    }

    @Override
    public List<NodeRoute> findNodeRoutes(Node node) {
        return nodeDao.findRoutes(node);
    }

    @Override
    public District findDistrictOuterFence(Fence fence) {
        return decorate(fenceDao.findDistrictOuter(fence));
    }

    @Override
    public void addDistrict(District district) {
        districtDao.save(district);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addFence(Fence fence) {
        fenceDao.save(fence);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addNode(Node node) {
        nodeDao.save(node);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addToNodeRoute(Node node, Double x, Double y) {
        // check node rolling type
        switch (node.getRollingType()) {
            case HOURLY:
                addToHourlyNodeRoute(node, x, y);
                break;
            case DAILY:
                addToDailyNodeRoute(node, x, y);
                break;
        }
    }

    @Override
    public void addToNodeRefuel(NodeRoute route, Double gallon, Double x, Double y) {
        try {
            StringBuffer point = new StringBuffer();
            point.append("POINT(")
                    .append(x)
                    .append(" ")
                    .append("y")
                    .append(")");

            NodeRefuel refuel = new NodeRefuelImpl();
            refuel.setGallon(gallon);
            refuel.setLocation((Point) READER.read(point.toString()));
            refuel.setRoute(route);
            nodeDao.addRefuel(route, refuel);
        } catch (ParseException e) {
            log.error("error", e);
        }
    }

    @Override
    public void addToNodeStop(NodeRoute route, Integer duration, Double x, Double y) {
        try {
            StringBuffer point = new StringBuffer();
            point.append("POINT(")
                    .append(x)
                    .append(" ")
                    .append("y")
                    .append(")");

            NodeStop stop = new NodeStopImpl();
            stop.setDuration(duration);
            stop.setLocation((Point) READER.read(point.toString()));
            stop.setRoute(route);
            nodeDao.addStop(route, stop);
        } catch (ParseException e) {
            log.error("error", e);
        }
    }

    private void addToDailyNodeRoute(Node node, Double x, Double y) {
        try {
            // find or create
            NodeRoute daily = null;
            Date today = new Date();
            if (nodeDao.hasDailyRoute(node, today)) {
                daily = nodeDao.findDailyRoute(today);
                daily.setPath(addToLineString(daily.getPath(), x, y));
                nodeDao.updateRoute(node, daily);
                log.debug("daily path: " + daily.getPath().toText());


            } else {
                daily = new NodeRouteImpl();
                daily.setNode(node);
                daily.setPath((LineString) READER.read(initializeLineString(node, x, y)));
                daily.setDateCreated(new Date());
                nodeDao.addRoute(node, daily);
            }
            sessionFactory.getCurrentSession().flush();

            // TODO:!!!
            // check if this a stop
            // check equal x and y
            // if equal then this is a stop
            // record how long we start with 0
            LineString path = daily.getPath();
            Point endPoint = path.getEndPoint();
            if (endPoint.getCoordinate().x == x && endPoint.getCoordinate().y == y) {
                // check if this is recorded previously
                // if not record

                // if yes increase duration
            }

        } catch (ParseException e) {
            log.error("error", e);
        }
    }

    private void addToHourlyNodeRoute(Node node, Double x, Double y) {
        try {
            // find or create
            NodeRoute daily = null;
            Date today = new Date();
            if (nodeDao.hasHourlyRoute(node, today)) {
                daily = nodeDao.findHourlyRoute(today);
                daily.setPath(addToLineString(daily.getPath(), x, y));
                nodeDao.updateRoute(node, daily);
                log.debug("daily path: " + daily.getPath().toText());
            } else {
                daily = new NodeRouteImpl();
                daily.setNode(node);
                daily.setPath((LineString) READER.read(initializeLineString(node, x, y)));
                daily.setDateCreated(new Date());
                nodeDao.addRoute(node, daily);
            }
            sessionFactory.getCurrentSession().flush();
        } catch (ParseException e) {
            log.error("error", e);
        }
    }

    private String initializeLineString(Node node, Double x, Double y) {
        StringBuffer lineString = new StringBuffer();
        lineString.append("LINESTRING(")
                .append(node.getLocation().getCoordinate().x)
                .append(" ")
                .append(node.getLocation().getCoordinate().y)
                .append(",")
                .append(x)
                .append(" ")
                .append(y)
                .append(")");
        return lineString.toString();
    }

    private LineString addToLineString(LineString path, Double x, Double y) {
        Coordinate[] coordinates = path.getCoordinates();
        Coordinate addedCoordinate = new Coordinate(x, y);
        Coordinate[] newCoordinates = new Coordinate[coordinates.length + 1];
        for (int i = 0; i < coordinates.length; i++) {
            newCoordinates[i] = coordinates[i];
        }
        newCoordinates[coordinates.length] = addedCoordinate;
        CoordinateSequence coordinateSequence = CoordinateArraySequenceFactory.instance().create(newCoordinates);
        return new LineString(coordinateSequence, path.getFactory());
    }

    private List<Fence> decorateTurfs(List<Fence> fences) {
        for (Fence fence : fences) {
            decorate(fence);
        }
        return fences;
    }

    private Fence decorate(Fence fence) {
        fence.setHeadCount(fenceDao.countNodesWithin(fence));
        return fence;
    }

    private List<District> decorateDistrict(List<District> districts) {
        for (District district : districts) {
            decorate(district);
        }
        return districts;
    }

    private District decorate(District district) {
        district.setHeadCount(districtDao.countNodesWithin(district));
        return district;
    }
}
