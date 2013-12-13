package net.canang.gridiron.core.dao;

import net.canang.gridiron.core.graph.dao.NodeDataRepository;
import net.canang.gridiron.core.graph.node.NodeData;
import net.canang.gridiron.core.model.Node;
import net.canang.gridiron.core.model.NodeAttribute;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GridironConfiguration.class})
public class NodeDataDaoTest {

    private Logger log = LoggerFactory.getLogger(NodeDataDaoTest.class);

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private NodeDataRepository nodeDataRepository;

    @Autowired
    private GraphDatabaseService graphDatabase;

}
