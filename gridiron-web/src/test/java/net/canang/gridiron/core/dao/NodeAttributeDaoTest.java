package net.canang.gridiron.core.dao;

import net.canang.gridiron.core.graph.dao.NodeDataRepository;
import net.canang.gridiron.core.graph.node.NodeData;
import net.canang.gridiron.core.model.Node;
import net.canang.gridiron.core.model.NodeAttribute;
import net.canang.gridiron.core.model.NodeAttributeImpl;
import net.canang.gridiron.core.model.NodeAttributeKey;
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
import java.util.Random;


/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GridironConfiguration.class})
public class NodeAttributeDaoTest {

    private Logger log = LoggerFactory.getLogger(NodeAttributeDaoTest.class);

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private NodeDataRepository nodeDataRepository;


    @Autowired
    private GraphDatabaseService graphDatabase;


    @Test
    public void testNodeData() {
        List<Node> nodes = nodeDao.find();
        Transaction tx = graphDatabase.beginTx();
        try {
            for (Node node : nodes) {
                NodeData data = new NodeData(node.getName());
                nodeDataRepository.save(data);
            }
            tx.success();
        } finally {
            tx.finish();
        }
    }

    @Test
    public void testNodeAttribute() {
        List<Node> nodes = nodeDao.find();
        for (Node node : nodes) {
        }
    }


    @Test
    public void updateNode() {
        List<Node> nodes = nodeDao.find();
        for (Node node : nodes) {

            // occupation
            NodeAttribute occupation = new NodeAttributeImpl();
            occupation.setKey(NodeAttributeKey.WORKS_AS);
            occupation.setValue(randomizeOccupation());
            nodeDao.addAttribute(node, occupation);

            // hobby
            NodeAttribute hobby = new NodeAttributeImpl();
            hobby.setKey(NodeAttributeKey.HOBBY);
            hobby.setValue(randomizeHobby());
            nodeDao.addAttribute(node, hobby);

            // children
            NodeAttribute children = new NodeAttributeImpl();
            children.setKey(NodeAttributeKey.PARENT_TO);
            children.setValue(randomizeChild());
            nodeDao.addAttribute(node, children);

            // inclination
            NodeAttribute inclination = new NodeAttributeImpl();
            inclination.setKey(NodeAttributeKey.INCLINATION);
            inclination.setValue(randomizeInclination());
            nodeDao.addAttribute(node, inclination);

        }
    }

    private String randomizeHobby() {
        String[] hobbies = new String[]{
                "Backpacking",
                "BASE jumping",
                "Basketball",
                "Beekeeping",
                "Bird watching",
                "Board sports",
                "Bonsai",
                "Bungee jumping",
                "Camping",
                "Canoeing",
                "Cosplay",
                "Cycling",
                "Driving",
                "Foraging",
                "Gardening",
                "Geocaching",
                "Ghost Hunting",
                "Graffiti",
                "Hiking",
                "Hooping",
                "Hunting",
                "Inline Skating",
                "Jogging",
                "Kayaking",
                "Kiteboarding",
                "Kiteflying",
                "LARPing",
                "Machining",
                "Metal detecting",
                "Motor sports",
                "Mountain biking",
                "Mushroom Hunting or Mycology",
                "Nordic skating",
                "Parkour",
                "Photography",
                "Rock climbing",
                "Roller skating",
                "Rugby",
                "Running",
                "Sailing",
                "Sand castle building",
                "Sculling or Rowing",
                "Skating",
                "Skiing",
                "Skydiving",
                "Surfing",
                "Swimming",
                "Tai Chi",
                "Urban exploration",
                "Vehicle restoration",
                "Water sports"
        };

        Random rand = new Random();
        return hobbies[rand.nextInt(hobbies.length)];
    }

    public String randomizeChild() {
        String[] children = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Random rand = new Random();
        return children[rand.nextInt(children.length)];
    }

    public String randomizeOccupation() {
        Random rand = new Random();
        String[] occupations = new String[]{
                "Actuary",
                "Acupuncturist",
                "Advertising and Public Relations",
                "Advertising Manager",
                "Advertising Specialist",
                "Aeronautical Engineer",
                "Aeroplane Pilot",
                "Agricultural Consultant",
                "Agricultural Engineer",
                "Agricultural Scientist",
                "Agricultural Technician",
                "Air Traffic Controller",
                "Air Transport Professionals NEC",
                "Ambulance Officer",
                "Amusement Centre Manager",
                "Anaesthetist",
                "Analyst Programmer",
                "Anatomist or Physiologist",
                "Animal Attendants and Trainers nec",
                "Antique Dealer",
                "Apiarist",
                "Apparel Cutter",
                "Arborist",
                "Archaeologist",
                "Architect",
                "Architectural Draftsperson",
                "Archivist",
                "Art Administrator or Manager",
                "Art Director (Film, Television or Stage)",
                "Art Teacher (Private Tuition)",
                "Artistic Director",
                "Auctioneer",
                "Audiologist",
                "Authors",
                "Automotive Electrician"
        };
        return occupations[rand.nextInt(occupations.length)];
    }

    public String randomizeInclination() {
        Random rand = new Random();
        String[] inclinations = new String[]{
                "BLUE",
                "GREEN",
                "RED"
        };
        return inclinations[rand.nextInt(inclinations.length)];
    }
}
