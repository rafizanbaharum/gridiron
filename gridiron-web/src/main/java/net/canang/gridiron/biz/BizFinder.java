package net.canang.gridiron.biz;

import net.canang.gridiron.core.model.District;
import net.canang.gridiron.core.model.Fence;
import net.canang.gridiron.core.model.Node;
import net.canang.gridiron.core.model.NodeRoute;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface BizFinder {

    District findDistrictById(Long id);

    Fence findFenceById(Long id);

    Node findNodeById(Long id);

    NodeRoute findNodeRouteById(Long id);

    List<District> findDistricts();

    List<Fence> findFences();

    List<Fence> findFencesWithinDistrict(District district);

    List<Node> findNodesWithinDistrict(District district);

    List<Node> findNodesWithinFence(Fence fence);

    List<Node> findNodes();

    List<NodeRoute> findNodeRoutes(Node node);

    District findDistrictOuterFence(Fence fence);

    void addDistrict(District district);

    void addFence(Fence fence);

    void addNode(Node Node);

    void addToNodeRoute(Node node, Double x, Double y);

    void addToNodeRefuel(NodeRoute route, Double x, Double y, Double gallon);

    void addToNodeStop(NodeRoute route, Integer duration, Double x, Double y);

}
