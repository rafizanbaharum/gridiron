package net.canang.gridiron.core.model;

import com.vividsolutions.jts.geom.Point;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 11/28/13
 */
@Indexed
@Entity(name = "Node")
@Table(name = "NODE")
public class NodeImpl implements Node, Serializable {

    @Id
    @DocumentId
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_NODE")
    @SequenceGenerator(name = "SEQ_NODE", sequenceName = "SEQ_NODE", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "MPG")
    private Double mpg;

    @Column(name = "ROLLING_TYPE")
    private RollingType rollingType;

    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point location;

    @JsonIgnore
    @OneToMany(targetEntity = NodeAttributeImpl.class, mappedBy = "node")
    private List<NodeAttribute> attributes;

    @JsonIgnore
    @OneToMany(targetEntity = NodeRouteImpl.class, mappedBy = "node")
    private List<NodeRoute> routes;

    @JsonIgnore
    @OneToMany(targetEntity = NodeRefuelImpl.class, mappedBy = "node")
    private List<NodeRefuel> refuels;

    @JsonIgnore
    @OneToMany(targetEntity = NodeStopImpl.class, mappedBy = "node")
    private List<NodeStop> stops;

    public NodeImpl() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getMpg() {
        return mpg;
    }

    public void setMpg(Double mpg) {
        this.mpg = mpg;
    }

    public List<NodeRoute> getRoutes() {
        return routes;
    }

    public void setRoutes(List<NodeRoute> routes) {
        this.routes = routes;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public RollingType getRollingType() {
        return rollingType;
    }

    public void setRollingType(RollingType rollingType) {
        this.rollingType = rollingType;
    }

    public List<NodeAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<NodeAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<NodeRefuel> getRefuels() {
        return refuels;
    }

    public void setRefuels(List<NodeRefuel> refuels) {
        this.refuels = refuels;
    }

    public List<NodeStop> getStops() {
        return stops;
    }

    public void setStops(List<NodeStop> stops) {
        this.stops = stops;
    }

    @Override
    public String toString() {
        return "NodeImpl{" +
                "id=" + id +
                '}';
    }
}
