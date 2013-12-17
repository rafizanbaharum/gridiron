package net.canang.gridiron.core.model;

import com.vividsolutions.jts.geom.Polygon;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface Fence {

    /**
     * @return
     */
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


    // transient
    Integer getHeadCount();

    void setHeadCount(Integer headCount);


    /**
     * @return
     */
    List<FenceProbation> getProbations();

    void setProbations(List<FenceProbation> probations);

}
