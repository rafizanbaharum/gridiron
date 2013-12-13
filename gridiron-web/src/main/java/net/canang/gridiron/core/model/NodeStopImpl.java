package net.canang.gridiron.core.model;

import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author rafizan.baharum
 * @since 11/28/13
 */
@Indexed
@Entity(name = "Stop")
@Table(name = "STOP")
public class NodeStopImpl implements NodeStop, Serializable {

    @Id
    @DocumentId
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_STOP")
    @SequenceGenerator(name = "SEQ_STOP", sequenceName = "SEQ_STOP", allocationSize = 1)
    private Long id;

    @Column(name = "DURATION")
    private Integer duration;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point location;

    @ManyToOne(targetEntity = NodeRouteImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ROUTE_ID")
    private NodeRoute route;

    public NodeStopImpl() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public NodeRoute getRoute() {
        return route;
    }

    public void setRoute(NodeRoute route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "StopImpl{" +
                "id=" + id +
                '}';
    }
}
