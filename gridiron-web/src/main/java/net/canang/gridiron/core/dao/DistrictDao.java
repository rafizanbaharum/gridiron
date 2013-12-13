package net.canang.gridiron.core.dao;

import net.canang.gridiron.core.model.District;
import net.canang.gridiron.core.model.Fence;
import net.canang.gridiron.core.model.Node;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface DistrictDao {

    District findById(Long id);

    List<District> find();

    List<Node> findNodesWithin(District district);

    List<Node> findNodesNotWithin(District district);

    List<Fence> findFencesWithin(District district);

    Integer countNodesWithin(District district);

    void save(District district);

    void update(District district);

    void remove(District district);
}
