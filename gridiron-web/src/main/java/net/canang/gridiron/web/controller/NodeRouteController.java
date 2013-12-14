package net.canang.gridiron.web.controller;

import net.canang.gridiron.biz.BizFinder;
import net.canang.gridiron.core.model.NodeRoute;
import net.canang.gridiron.web.model.Converter;
import net.canang.gridiron.web.model.CoordinateModel;
import net.canang.gridiron.web.model.NodeRouteModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Controller
@RequestMapping("/route")
public class NodeRouteController {

    private Logger log = LoggerFactory.getLogger(NodeRouteController.class);

    @Autowired
    private Converter converter;

    @Autowired
    private BizFinder finder;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(ModelMap model) {
        return "route_view";
    }


    @RequestMapping(value = "/navigate/{id}", method = RequestMethod.GET)
    public String navigate(@PathVariable Long id, ModelMap model) {
        NodeRoute route = finder.findNodeRouteById(id);
        model.put("route", converter.convert(route));
        return "route_navigate";
    }

    @RequestMapping(value = "/findRoute", method = RequestMethod.GET)
    public
    @ResponseBody
    NodeRouteModel findAllRoutes(@RequestParam Long routeId) {
        NodeRoute route = finder.findNodeRouteById(routeId);
        return converter.convert(route);
    }

    @RequestMapping(value = "/findRouteCentroid", method = RequestMethod.GET)
    public
    @ResponseBody
    CoordinateModel findRouteCentroid(@RequestParam Long routeId) {
        NodeRoute route = finder.findNodeRouteById(routeId);
        return converter.convert(route.getPath().getCentroid().getCoordinate());
    }
}
