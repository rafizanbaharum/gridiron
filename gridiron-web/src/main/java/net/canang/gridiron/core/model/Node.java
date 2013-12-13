package net.canang.gridiron.core.model;


import com.vividsolutions.jts.geom.Point;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/28/13
 */
public interface Node {

    /**
     * @return
     */
    Long getId();

    void setId(Long id);

    /**
     * @return
     */
    String getName();

    void setName(String name);

    /**
     * @return
     */
    String getPhone();

    void setPhone(String phone);

    /**
     * @return
     */
    Double getMpg();

    void setMpg(Double mpg);

    /**
     * @return
     */
    RollingType getRollingType();

    void setRollingType(RollingType rollingType);

    /**
     * @return
     */
    Point getLocation();

    void setLocation(Point point);

    /**
     * @return
     */
    List<NodeRefuel> getRefuels();

    void setRefuels(List<NodeRefuel> refuels);

    /**
     * @return
     */
    List<NodeStop> getStops();

    void setStops(List<NodeStop> nodeStops);

    /**
     * @return
     */
    List<NodeRoute> getRoutes();

    void setRoutes(List<NodeRoute> nodes);

    /**
     * @return
     */
    List<NodeAttribute> getAttributes();

    void setAttributes(List<NodeAttribute> attributes);

}
