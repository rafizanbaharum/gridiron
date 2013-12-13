package net.canang.gridiron.web.controller;

import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import net.canang.gridiron.biz.BizFinder;
import net.canang.gridiron.core.model.*;
import net.canang.gridiron.core.model.Fence;
import net.canang.gridiron.core.model.FenceImpl;
import net.canang.gridiron.web.Utils;
import net.canang.gridiron.web.model.Converter;
import net.canang.gridiron.web.model.FenceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Controller
@RequestMapping("/fence")
public class FenceController {

    private Logger log = LoggerFactory.getLogger(FenceController.class);

    @Autowired
    private Converter converter;

    @Autowired
    private BizFinder finder;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.put("fences", finder.findFences());
        return "fence_list";
    }

    @RequestMapping(value = "/navigate", method = RequestMethod.GET)
    public String navigate(ModelMap model) {
        return "fence_navigate";
    }

    @RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
    public String report(@PathVariable Long id, ModelMap model) {
        Fence fence = finder.findFenceById(id);
        List<Node> nodes = finder.findNodesWithinFence(fence);
        model.put("fence", converter.convert(fence));
        model.put("nodes", converter.convertNodes(nodes));
        return "fence_report";
    }

    @RequestMapping(value = "/trend/{id}", method = RequestMethod.GET)
    public String trend(@PathVariable Long id, ModelMap model) {
        Fence fence = finder.findFenceById(id);
        List<Node> nodes = finder.findNodesWithinFence(fence);
        model.put("fence", converter.convert(fence));
        model.put("nodes", converter.convertNodes(nodes));
        return "fence_trend";
    }


    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, ModelMap model) {
        Fence fence = finder.findFenceById(id);
        District district = finder.findDistrictOuterFence(fence);
        List<Node> nodes = finder.findNodesWithinFence(fence);
        model.put("fence", converter.convert(fence));
        model.put("district", converter.convert(district));
        model.put("nodes", converter.convertNodes(nodes));
        return "fence_view";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public String view(@RequestParam String polyStr, ModelMap model) {
        try {
            WKTReader reader = new WKTReader();
            Fence fence = new FenceImpl();
            String code = Utils.randomizeCode();
            fence.setCode(code);
            fence.setDescription("Fence " + code);
            fence.setBound((Polygon) reader.read(polyStr));
            finder.addFence(fence);
        } catch (ParseException e) {
        }
        return "fence added!";
    }


    @RequestMapping(value = "/draw", method = RequestMethod.GET)
    public String draw(@RequestParam Long districtId, ModelMap model) {
        District district = finder.findDistrictById(districtId);
        model.put("district", converter.convert(district));
        return "fence_draw";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String data(ModelMap model) {
        return "fence_data";
    }

    @RequestMapping(value = "/findFence", method = RequestMethod.GET)
    public
    @ResponseBody
    FenceModel findFenceBound(@RequestParam Long id) {
        Fence fence = finder.findFenceById(id);
        return converter.convert(fence);
    }

    @RequestMapping(value = "/findFenceCenteroid", method = RequestMethod.GET)
    public
    @ResponseBody
    FenceModel findFenceCenteroid(@RequestParam Long id) {
        Fence fence = finder.findFenceById(id);
        return converter.convert(fence);
    }

    @RequestMapping(value = "/findAllFences", method = RequestMethod.GET)
    public
    @ResponseBody
    List<FenceModel> findAllFences(@RequestParam Long districtId) {
        District district = finder.findDistrictById(districtId);
        List<Fence> fences = finder.findFencesWithinDistrict(district);
        return converter.convertFences(fences);
    }
}