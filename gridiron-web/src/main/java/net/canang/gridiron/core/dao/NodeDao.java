package net.canang.gridiron.core.dao;


import com.vividsolutions.jts.geom.Point;
import net.canang.gridiron.core.model.*;

import java.util.Date;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface NodeDao {

    Node findById(Long id);

    Node findByPhone(String phone);

    List<Node> find();

    List<Node> findAround(Point point);

    List<Node> findWithin(String filter);

    List<NodeAttribute> findAttributes(Node node);

    List<NodeRoute> findRoutes(Node node);

    List<NodeRefuel> findRefuels(Node node);

    List<NodeStop> findStops(Node node);

    NodeRoute findDailyRoute(Node node, Date date);

    NodeRoute findHourlyRoute(Node node, Date date);

    Integer count();

    boolean hasDailyRoute(Node node, Date date);

    boolean hasHourlyRoute(Node node, Date date);

    boolean hasStopAt(Node node, Double x, Double y);

    void save(Node Node);

    void update(Node Node);

    void remove(Node Node);

    void addAttribute(Node node, NodeAttribute attribute);

    void removeAttribute(Node node, NodeAttribute attribute);

    void addRoute(Node node, NodeRoute route);

    void updateRoute(Node node, NodeRoute route);

    void removeRoute(Node node, NodeRoute route);

    void addRefuel(NodeRoute route, NodeRefuel refuel);

    void updateRefuel(NodeRoute route, NodeRefuel refuel);

    void removeRefuel(NodeRoute route, NodeRefuel refuel);

    void addStop(NodeRoute route, NodeStop stop);

    void updateStop(NodeRoute route, NodeStop stop);

    void removeStop(NodeRoute route, NodeStop stop);
}
