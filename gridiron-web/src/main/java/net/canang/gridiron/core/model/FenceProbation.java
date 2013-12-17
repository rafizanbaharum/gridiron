package net.canang.gridiron.core.model;

/**
 * @author rafizan.baharum
 * @since 12/16/13
 */
public interface FenceProbation {

    /**
     * @return
     */
    Long getId();

    void setId(Long id);

    /**
     * @return
     */
    Fence getFence();

    void setFence(Fence fence);

    /**
     * @return
     */
    Node getNode();

    void setNode(Node node);

}
