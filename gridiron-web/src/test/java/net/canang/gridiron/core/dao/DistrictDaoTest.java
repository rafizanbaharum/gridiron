package net.canang.gridiron.core.dao;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import net.canang.gridiron.core.model.District;
import net.canang.gridiron.core.model.DistrictImpl;
import net.canang.gridiron.core.model.Fence;
import net.canang.gridiron.core.model.Node;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class DistrictDaoTest {

    private Logger log = LoggerFactory.getLogger(DistrictDaoTest.class);

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void createDistrict() throws ParseException {
        WKTReader reader = new WKTReader();
        Polygon bound = (Polygon) reader.read(" POLYGON((1.51396 103.63293,1.52563 103.6464,1.52992 103.66803,1.52649 103.67361,1.52168 103.67988,1.50349 103.65619,1.50109 103.6561,1.49937 103.64812,1.51388 103.63301,1.51396 103.63293))");
        District district = new DistrictImpl();
        district.setBound(bound);

        districtDao.save(district);
    }


    @Test
    public void find() {
        District district = districtDao.findById(26L);
        Geometry bound = district.getBound();
        log.debug("bound: " + bound);
    }


    @Test
    public void findNodesWithin() {
        District district = districtDao.findById(26L);
        List<Node> nodes = districtDao.findNodesWithin(district);
        log.debug("node size: " + nodes.size());
    }

    @Test
    public void findNodesNotWithin() {
        District district = districtDao.findById(26L);
        List<Node> nodes = districtDao.findNodesNotWithin(district);
        log.debug("node size: " + nodes.size());
    }

    @Test
    public void findTurfWithin() {
        District district = districtDao.findById(25L);
        List<Fence> fences = districtDao.findFencesWithin(district);
        log.debug("turf size: " + fences.size());
        for (Fence fence : fences) {
            log.debug("turf id: " + fence.getId());
        }
    }
}
