package net.canang.gridiron.core.model;


import com.vividsolutions.jts.geom.LineString;

import java.util.Date;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 12/9/13
 */
public interface NodeRoute {

    /**
     * @return
     */
    Long getId();

    void setId(Long id);

    /**
     * @return
     */
    LineString getPath();

    void setPath(LineString lineString);

    /**
     * @return
     */
    Date getDateCreated();

    void setDateCreated(Date dateCreated);

    /**
     * @return
     */
    Node getNode();

    void setNode(Node node);


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
    Double getLength();

    void setLength(Double length);
}
