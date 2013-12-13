package net.canang.gridiron.core.model;

import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author rafizan.baharum
 * @since 11/28/13
 */
//@Indexed
@Entity(name = "NodeRefuel")
@Table(name = "NODE_REFUEL")
public class NodeRefuelImpl implements NodeRefuel, Serializable {

    @Id
//    @DocumentId
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_NODE_REFUEL")
    @SequenceGenerator(name = "SEQ_NODE_REFUEL", sequenceName = "SEQ_NODE_REFUEL", allocationSize = 1)
    private Long id;

    @Column(name = "GALLON")
    private Double gallon;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point location;

    @ManyToOne(targetEntity = NodeRouteImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ROUTE_ID")
    private NodeRoute route;

    public NodeRefuelImpl() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGallon() {
        return gallon;
    }

    public void setGallon(Double gallon) {
        this.gallon = gallon;
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
        return "NodeRefuelImpl{" +
                "id=" + id +
                '}';
    }
}
