package net.canang.gridiron.core.model;

import com.vividsolutions.jts.geom.Polygon;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author rafizan.baharum
 * @since 6/28/13
 */
@Indexed
@Entity(name = "District")
@Table(name = "DISTRICT")
public class DistrictImpl implements District, Serializable {

    @Id
    @DocumentId
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_DISTRICT")
    @SequenceGenerator(name = "SEQ_DISTRICT", sequenceName = "SEQ_DISTRICT", allocationSize = 1)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    @Type(type = "org.hibernate.spatial.GeometryType")
    private Polygon bound;

    @ManyToOne(targetEntity = StateImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "STATE_ID")
    private State state;

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getHeadCount() {
        return headCount;
    }

    public void setHeadCount(Integer headCount) {
        this.headCount = headCount;
    }
}
