package net.canang.gridiron.core.model;

import javax.persistence.*;

/**
 * @author rafizan.baharum
 * @since 12/16/13
 */
@Entity(name = "FenceProbation")
@Table(name = "FENCE_PROBATION")
public class FenceProbationImpl implements FenceProbation {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_FENCE_PROBATION")
    @SequenceGenerator(name = "SEQ_FENCE_PROBATION", sequenceName = "SEQ_FENCE_PROBATION", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = FenceImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "FENCE_ID")
    private Fence fence;

    @ManyToOne(targetEntity = NodeImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "NODE_ID")
    private Node node;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fence getFence() {
        return fence;
    }

    public void setFence(Fence fence) {
        this.fence = fence;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
