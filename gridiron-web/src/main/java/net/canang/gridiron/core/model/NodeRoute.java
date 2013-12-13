package net.canang.gridiron.core.model;


import com.vividsolutions.jts.geom.LineString;

import java.util.Date;

/**
 * @author rafizan.baharum
 * @since 12/9/13
 */
public interface NodeRoute {

    Long getId();

    void setId(Long id);

    LineString getPath();

    void setPath(LineString lineString);

    Date getDateCreated();

    void setDateCreated(Date dateCreated);

    Node getNode();

    void setNode(Node node);

    Double getLength();

    void setLength(Double length);
}
