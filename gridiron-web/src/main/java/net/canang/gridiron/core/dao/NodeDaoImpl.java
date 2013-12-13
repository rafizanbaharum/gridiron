package net.canang.gridiron.core.dao;

import com.vividsolutions.jts.geom.Point;
import net.canang.gridiron.core.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Transactional
@Repository("nodeDao")
public class NodeDaoImpl implements NodeDao {

    private Logger log = LoggerFactory.getLogger(NodeDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;
    public static final SimpleDateFormat FORMAT_DAILY = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat FORMAT_HOURLY = new SimpleDateFormat("dd/MM/yyyy hh");

    @Override
    public Node findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Node) session.get(NodeImpl.class, id);
    }

    @Override
    public Node findByPhone(String phone) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Node i where i.phone = :phone");
        query.setString("phone", phone);
        return (Node) query.uniqueResult();
    }


    @Override
    public List<Node> find() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Node i");
        return (List<Node>) query.list();
    }

    @Override
    public List<Node> findAround(Point point) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Node i");
        return (List<Node>) query.list();
    }

    @Override
    public List<Node> findWithin(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Node i where within(i.location, '" + filter + "') = true"); // TODO: set param
        return (List<Node>) query.list();
    }

    @Override
    public List<NodeAttribute> findAttributes(Node node) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from NodeAttribute i where i.node = :node");
        query.setEntity("node", node);
        return (List<NodeAttribute>) query.list();
    }


    @Override
    public List<NodeRoute> findRoutes(Node node) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from NodeRoute i where i.node = :node");
        query.setEntity("node", node);
        return (List<NodeRoute>) query.list();
    }

    @Override
    public List<NodeRefuel> findRefuels(Node node) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from NodeRefuel i where i.node = :node");
        query.setEntity("node", node);
        return (List<NodeRefuel>) query.list();
    }

    @Override
    public List<NodeStop> findStops(Node node) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from NodeStop i where i.node = :node");
        query.setEntity("node", node);
        return (List<NodeStop>) query.list();
    }

    @Override
    public NodeRoute findDailyRoute(Date date) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from NodeRoute i where to_char(i.dateCreated, 'dd/MM/YYYY') = :date");
        query.setString("date", FORMAT_DAILY.format(date));
        return (NodeRoute) query.uniqueResult();
    }

    @Override
    public NodeRoute findHourlyRoute(Date date) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from NodeRoute i where to_char(i.dateCreated, 'dd/MM/YYYY hh') = :date");
        query.setString("date", FORMAT_HOURLY.format(date));
        return (NodeRoute) query.uniqueResult();
    }


    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(i) from Node i");
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean hasDailyRoute(Node node, Date date) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from NodeRoute i where " +
                "i.node = :node and " +
                "to_char(i.dateCreated, 'dd/MM/yyyy') = :date");
        query.setEntity("node", node);
        query.setString("date", FORMAT_DAILY.format(date));
        return query.uniqueResult() != null;

    }

    @Override
    public boolean hasHourlyRoute(Node node, Date date) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from NodeRoute i where " +
                "i.node = :node and " +
                "to_char(i.dateCreated, 'dd/MM/yyyy hh') = :date");
        query.setEntity("node", node);
        query.setString("date", FORMAT_HOURLY.format(date));
        return query.uniqueResult() != null;
    }

    @Override
    public boolean hasStopAt(Node node, Double x, Double y) {
        // TODO
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from NodeStop i where " +
                "i.node = :node " +
                "to_char(i.dateCreated, 'dd/MM/yyyy hh') = :date");
        query.setEntity("node", node);
        return query.uniqueResult() != null;
    }

    @Override
    public void save(Node Node) {
        Session session = sessionFactory.getCurrentSession();
        session.save(Node);
    }

    @Override
    public void update(Node Node) {
        Session session = sessionFactory.getCurrentSession();
        session.update(Node);
    }

    @Override
    public void remove(Node Node) {
        // TODO:
    }

    @Override
    public void addAttribute(Node node, NodeAttribute attribute) {
        attribute.setNode(node);
        sessionFactory.getCurrentSession().save(attribute);
    }

    @Override
    public void removeAttribute(Node node, NodeAttribute attribute) {
        // TODO:

    }

    @Override
    public void addRoute(Node node, NodeRoute route) {
        route.setNode(node);
        sessionFactory.getCurrentSession().save(route);
    }

    @Override
    public void updateRoute(Node node, NodeRoute route) {
        route.setNode(node);
        sessionFactory.getCurrentSession().update(route);
    }

    @Override
    public void removeRoute(Node node, NodeRoute route) {
        // TODO:

    }

    @Override
    public void addRefuel(NodeRoute route, NodeRefuel refuel) {
        refuel.setRoute(route);
        sessionFactory.getCurrentSession().save(refuel);
    }

    @Override
    public void updateRefuel(NodeRoute route, NodeRefuel refuel) {
        // TODO:

    }

    @Override
    public void removeRefuel(NodeRoute route, NodeRefuel refuel) {
        // TODO:
    }

    @Override
    public void addStop(NodeRoute route, NodeStop stop) {
        stop.setRoute(route);
        sessionFactory.getCurrentSession().save(stop);
    }

    @Override
    public void updateStop(NodeRoute route, NodeStop stop) {
        // TODO:

    }

    @Override
    public void removeStop(NodeRoute route, NodeStop stop) {
        // TODO:

    }
}
