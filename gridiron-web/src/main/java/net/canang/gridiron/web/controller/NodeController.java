package net.canang.gridiron.web.controller;

import com.vividsolutions.jts.geom.Point;
import net.canang.gridiron.biz.BizFinder;
import net.canang.gridiron.core.model.District;
import net.canang.gridiron.core.model.Node;
import net.canang.gridiron.core.model.NodeRoute;
import net.canang.gridiron.web.model.Converter;
import net.canang.gridiron.web.model.NodeModel;
import net.canang.gridiron.web.model.NodeRouteModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * /nearme_json/Nodes?lat=1.5333&lon=103.388
 *
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Controller
@RequestMapping("/node")
public class NodeController {

    private Logger log = LoggerFactory.getLogger(NodeController.class);

    @Autowired
    private Converter converter;

    @Autowired
    private BizFinder finder;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(ModelMap model) {
        return "node_view";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.put("nodes", converter.convertNodes(finder.findNodes()));
        return "node_list";
    }

    @RequestMapping(value = "/navigate/{id}", method = RequestMethod.GET)
    public String navigate(@PathVariable Long id, ModelMap model) {
        Node node = finder.findNodeById(id);
        model.put("node", converter.convert(node));
        model.put("nodeRoutes", converter.convertNodeRoutes(finder.findNodeRoutes(node)));
        return "node_navigate";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String data(ModelMap model) {
        return "node_data";
    }

    @RequestMapping(value = "/draw/{id}", method = RequestMethod.GET)
    public String draw(@PathVariable Long id, ModelMap model) {
        Node node = finder.findNodeById(id);
        model.put("node", converter.convert(node));
        return "node_draw";
    }


    @RequestMapping(value = "/findNodesWithinDistrict", method = RequestMethod.GET)
    public
    @ResponseBody
    List<NodeModel> findNodesWithinDistrict(@RequestParam Long districtId) {
        District district = finder.findDistrictById(districtId);
        List<Node> nodes = finder.findNodesWithinDistrict(district);
        List<NodeModel> models = new ArrayList<NodeModel>();
        for (Node node : nodes) {
            Point location = node.getLocation();
            NodeModel model = new NodeModel(
                    node.getId(),
                    node.getName(),
                    node.getPhone(),
                    location.getCoordinate().x,
                    location.getCoordinate().y);
            models.add(model);
        }
        log.debug("result: " + nodes.size());
        return models;
    }

    @RequestMapping(value = "/findAllRoutes", method = RequestMethod.GET)
    public
    @ResponseBody
    List<NodeRouteModel> findAllRoutes(@RequestParam Long nodeId) {
        Node node = finder.findNodeById(nodeId);
        List<NodeRoute> routes = finder.findNodeRoutes(node);
        List<NodeRouteModel> models = new ArrayList<NodeRouteModel>();
        for (NodeRoute route : routes) {
            if (null != route.getPath()) {
                models.add(converter.convert(route));
            }
        }
        log.debug("result: " + routes.size());
        return models;
    }

    @RequestMapping(value = "/beacon", method = RequestMethod.GET)
    @ResponseBody
    public String addNode(@RequestParam Long nodeId, @RequestParam Double x, @RequestParam Double y) {
        log.debug("adding to route: " + x);
        log.debug("adding to route: " + y);
        Node node = finder.findNodeById(nodeId);
        finder.addToNodeRoute(node, x, y);
        return "success";
    }

    @RequestMapping(value = "/addRefuel", method = RequestMethod.GET)
    @ResponseBody
    public String addNode(@RequestParam Long nodeId, @RequestParam Double gallon, @RequestParam Double x, @RequestParam Double y) {
        Node node = finder.findNodeById(nodeId);
//        finder.addToNodeRefuel(node, x, y, gallon);
        return "success";
    }
}
