package net.canang.gridiron.core.model;

import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * @author rafizan.baharum
 * @since 12/16/13
 */
@Entity(name = "FenceViolation")
@Table(name = "FENCE_VIOLATION")
public class FenceViolationImpl implements FenceViolation {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_FENCE_VIOLATION")
    @SequenceGenerator(name = "SEQ_FENCE_VIOLATION", sequenceName = "SEQ_FENCE_VIOLATION", allocationSize = 1)
    private Long id;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point location;

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
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
