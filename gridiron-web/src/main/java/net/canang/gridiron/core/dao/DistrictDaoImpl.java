package net.canang.gridiron.core.dao;

import net.canang.gridiron.core.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Transactional
@Repository("districtDao")
public class DistrictDaoImpl implements DistrictDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public District findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (District) session.get(DistrictImpl.class, id);
    }

    @Override
    public List<District> find() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from District i");
        return (List<District>) query.list();
    }

    @Override
    public List<Node> findNodesWithin(District district) {
        if(null == district.getBound()) return Collections.EMPTY_LIST;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Node i where within(i.location, '" + district.getBound().toString() + "') = true"); // TODO: set param
        return (List<Node>) query.list();
    }

    @Override
    public List<Node> findNodesNotWithin(District district) {
        if(null == district.getBound()) return Collections.EMPTY_LIST;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Node i where within(i.location, '" + district.getBound().toString() + "') = false"); // TODO: set param
        return (List<Node>) query.list();
    }

    @Override
    public List<Fence> findFencesWithin(District district) {
        if(null == district.getBound()) return Collections.EMPTY_LIST;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Turf i where within(i.bound, '" + district.getBound().toString() + "') = true"); // TODO: set param
        return (List<Fence>) query.list();
    }

    @Override
    public Integer countNodesWithin(District district) {
        if(null == district.getBound()) return 0;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(i) from Node i where within(i.location, '" + district.getBound().toString() + "') = true"); // TODO: set param
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public void save(District issue) {
        Session session = sessionFactory.getCurrentSession();
        session.save(issue);
    }

    @Override
    public void update(District issue) {
        // TODO:

    }

    @Override
    public void remove(District issue) {
        // TODO:
    }
}
