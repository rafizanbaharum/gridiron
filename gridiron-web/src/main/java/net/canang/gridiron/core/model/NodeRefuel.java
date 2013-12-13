package net.canang.gridiron.core.model;

import com.vividsolutions.jts.geom.Point;

import java.util.Date;

/**
 * @author rafizan.baharum
 * @since 12/9/13
 */
public interface NodeRefuel {

    /**
     * @return
     */
    Long getId();

    void setId(Long id);

    /**
     * @return
     */
    Double getGallon();

    void setGallon(Double gallon);

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
    NodeRoute getRoute();

    void setRoute(NodeRoute route);

}
