package net.canang.gridiron.core.model;

import com.vividsolutions.jts.geom.Polygon;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface District {

    Long getId();

    void setId(Long id);

    /**
     * @return
     */
    String getCode();

    void setCode(String code);

    /**
     * @return
     */
    String getDescription();

    void setDescription(String description);

    /**
     * @return
     */
    Polygon getBound();

    void setBound(Polygon bound);

    /**
     * @return
     */
    State getState();

    void setState(State state);

    // transient
    Integer getHeadCount();

    void setHeadCount(Integer headCount);


}
