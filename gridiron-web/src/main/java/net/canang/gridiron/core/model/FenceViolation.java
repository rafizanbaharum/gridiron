package net.canang.gridiron.core.model;

import com.vividsolutions.jts.geom.Point;

import java.util.Date;

/**
 * @author rafizan.baharum
 * @since 12/16/13
 */
public interface FenceViolation {

    /**
     * @return
     */
    Long getId();

    void setId(Long id);

    /**
     * @return
     */
    Date getDateCreated();

    void setDateCreated(Date dateCreated);

    /**
     * @return
     */
    Point getLocation();

    void setLocation(Point point);

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
