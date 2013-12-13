package net.canang.gridiron.core.dao;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import net.canang.gridiron.biz.BizFinder;
import net.canang.gridiron.core.model.Node;
import net.canang.gridiron.core.model.NodeImpl;
import net.canang.gridiron.core.model.RollingType;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class NodeDaoTest {

    private Logger log = LoggerFactory.getLogger(NodeDaoTest.class);

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private BizFinder finder;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void createNode() {
        try {
            WKTReader reader = new WKTReader();
            Node node = new NodeImpl();
            node.setName("NISSAN VANETTE");
            node.setPhone("012 260 6845");
            node.setMpg(20D);
            node.setLocation((Point) reader.read("POINT(" + 1.5243D + " " + 103.64988D + ")"));
            node.setRollingType(RollingType.DAILY);
            nodeDao.save(node);
        } catch (ParseException e) {
            log.error("error", e);
        }
    }

    @Test
    public void addRoute() {
        Node phone = nodeDao.findByPhone("012 260 6845");
        finder.addToNodeRoute(phone, 1.5223D, 103.64538D);
        finder.addToNodeRoute(phone, 1.5212D, 103.6123488D);
    }

    @Test
    public void find() {
        List<Node> nodes = nodeDao.find();
        log.debug("size: " + nodes.size());
        for (Node node : nodes) {
            log.debug("Node: " + node);
            log.debug("Node: " + node.getLocation().toString());
        }
    }


    /**
     * POLYGON((1.51396 103.63293,1.52563 103.6464,1.52992 103.66803,1.52649 103.67361,1.52168 103.67988,1.50349 103.65619,1.50109 103.6561,1.49937 103.64812,1.51388 103.63301,1.51396 103.63293))
     */
    @Test
    public void findWithin() {
        List<Node> nodes = nodeDao.findWithin("" +
                "POLYGON((" +
                "1.51396 103.63293," +
                "1.52563 103.6464," +
                "1.52992 103.66803," +
                "1.52649 103.67361," +
                "1.52168 103.67988," +
                "1.50349 103.65619," +
                "1.50109 103.6561," +
                "1.49937 103.64812," +
                "1.51388 103.63301," +
                "1.51396 103.63293" +
                "))");
        log.debug("size: " + nodes.size());
        for (Node node : nodes) {
            log.debug("Node: " + node);
            log.debug("Node: " + node.getLocation().toString());
        }
    }


    @Test
    public void updateNode() {
        String[] names = new String[]{
                "Ahmad", "Shah", "Yusof", "Salleh", "Noor", "Nasir",
                "Said", "Yasin", "Yunos", "Zin", "Isa", "Sharif", "Khalid",
                "Nizam", "Taib", "Yatim", "Yazid", "Zain", "Arif", "Fauzi", "Rashid",
                "Razali", "Esa", "Fadil", "Aris", "Saad", "Kamal",
                "Ismail", "Azmi", "Hashim", "Nazri", "Jamil", "Zaini", "Zamri",
                "Kasim", "Fuad", "Din", "Ariffin", "Najib", "Hassan", "Sani",
                "Ishak", "Nordin", "Farid", "Hatta", "Ghazali", "Jais", "Khairi",
                "Suhaimi", "Zaidi", "Zaki"};

        List<Node> nodes = nodeDao.find();
        for (Node node : nodes) {
            randomizePhone();
            node.setPhone(randomizePhone());
            nodeDao.update(node);
        }
    }


    @Test
    public void findAround() {
//        List<Node> Nodes = nodeDao.findAround(100.0D, 1.5333D, 103.388D);
//        for (Node node : Nodes) {
//            log.debug("Node: " + node);
//        }
    }

    @Test
    public void saveAndFind() {
        Node Node = new NodeImpl();
        nodeDao.save(Node);
//        List<Node> Nodes = nodeDao.findAround(1D, 1.5555D, 103.3333D);
//        for (Node i : Nodes) {
//            log.debug("Node: " + i);
//        }
    }

    @Test
    public void findAndUpdate() {
        List<Node> Nodes = nodeDao.find();
        for (Node node : Nodes) {
            nodeDao.update(node);
        }
    }


    public String randomizeNricNo() {
        String[] nos = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuffer nric = new StringBuffer();

        Random rand = new Random();
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        return nric.toString();
    }

    public String randomizePhone() {
        String[] nos = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuffer phone = new StringBuffer();

        Random rand = new Random();
        phone.append("01");
        phone.append(rand.nextInt(nos.length));
        phone.append(" ");
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        return phone.toString();
    }
}
