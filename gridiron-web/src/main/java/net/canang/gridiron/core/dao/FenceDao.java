package net.canang.gridiron.core.dao;

import net.canang.gridiron.core.model.District;
import net.canang.gridiron.core.model.Fence;
import net.canang.gridiron.core.model.Node;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface FenceDao {

    Fence findById(Long id);

    List<Fence> find();

    District findDistrictOuter(Fence fence);

    List<Node> findNodesWithin(Fence fence);

    List<Node> findNodesNotWithin(Fence fence);

    List<Fence> findFencesWithin(District district);

    Integer countNodesWithin(Fence fence);

    void save(Fence fence);

    void update(Fence fence);

    void remove(Fence fence);

}
