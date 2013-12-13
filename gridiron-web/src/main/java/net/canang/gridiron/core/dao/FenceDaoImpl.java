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
@Repository("turfDao")
public class FenceDaoImpl implements FenceDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Fence findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Fence) session.get(FenceImpl.class, id);
    }

    @Override
    public List<Fence> find() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Turf i");
        return (List<Fence>) query.list();
    }

    @Override
    public District findDistrictOuter(Fence fence) {
        if (null == fence.getBound()) return null;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from District i where within('" + fence.getBound().toString() + "', i.bound) = true"); // TODO: set param
        return (District) query.uniqueResult();
    }


    @Override
    public List<Node> findNodesWithin(Fence fence) {
        if (null == fence.getBound()) return Collections.EMPTY_LIST;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Node i where within(i.location, '" + fence.getBound().toString() + "') = true"); // TODO: set param
        return (List<Node>) query.list();
    }

    @Override
    public List<Node> findNodesNotWithin(Fence fence) {
        if (null == fence.getBound()) return Collections.EMPTY_LIST;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Node i where within(i.location, '" + fence.getBound().toString() + "') = false"); // TODO: set param
        return (List<Node>) query.list();
    }

    @Override
    public List<Fence> findFencesWithin(District district) {
        if (null == district.getBound()) return Collections.EMPTY_LIST;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Turf i where contains(i.bound, '" + district.getBound().toString() + "') = true"); // TODO: set param
        return (List<Fence>) query.list();
    }

    @Override
    public Integer countNodesWithin(Fence fence) {
        if (null == fence.getBound()) return 0;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(i) from Node i where within(i.location, '" + fence.getBound().toString() + "') = true"); // TODO: set param
        return ((Long) query.uniqueResult()).intValue();
    }


    @Override
    public void save(Fence fence) {
        Session session = sessionFactory.getCurrentSession();
        session.save(fence);
    }

    @Override
    public void update(Fence fence) {
        Session session = sessionFactory.getCurrentSession();
        session.update(fence);
    }

    @Override
    public void remove(Fence issue) {
        // TODO:
    }
}
