package net.canang.gridiron.core.model;

import com.vividsolutions.jts.geom.Polygon;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/28/13
 */
//@Indexed
@Entity(name = "Fence")
@Table(name = "FENCE")
public class FenceImpl implements Fence, Serializable {

    @Id
//    @DocumentId
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_FENCE")
    @SequenceGenerator(name = "SEQ_FENCE", sequenceName = "SEQ_FENCE", allocationSize = 1)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    @Type(type = "org.hibernate.spatial.GeometryType")
    private Polygon bound;


    @OneToMany(targetEntity = FenceProbationImpl.class, mappedBy = "fence")
    private List<FenceProbation> probations;

    @Transient
    private Integer headCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Polygon getBound() {
        return bound;
    }

    public void setBound(Polygon bound) {
        this.bound = bound;
    }

    public List<FenceProbation> getProbations() {
        return probations;
    }

    public void setProbations(List<FenceProbation> probations) {
        this.probations = probations;
    }

    public Integer getHeadCount() {
        return headCount;
    }

    public void setHeadCount(Integer headCount) {
        this.headCount = headCount;
    }
}
