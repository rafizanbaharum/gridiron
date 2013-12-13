package net.canang.gridiron.core.model;

import com.vividsolutions.jts.geom.LineString;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 12/9/13
 */
//@Indexed
@Entity(name = "NodeRoute")
@Table(name = "NODE_ROUTE")
public class NodeRouteImpl implements NodeRoute {

    @Id
//    @DocumentId
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_NODE_ROUTE")
    @SequenceGenerator(name = "SEQ_NODE_ROUTE", sequenceName = "SEQ_NODE_ROUTE", allocationSize = 1)
    private Long id;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Type(type = "org.hibernate.spatial.GeometryType")
    private LineString path;

    @ManyToOne(targetEntity = NodeImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "NODE_ID")
    private Node node;


    @JsonIgnore
    @OneToMany(targetEntity = NodeRefuelImpl.class, mappedBy = "route")
    private List<NodeRefuel> refuels;

    @JsonIgnore
    @OneToMany(targetEntity = NodeStopImpl.class, mappedBy = "route")
    private List<NodeStop> stops;


    @Transient
    private Double length;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LineString getPath() {
        return path;
    }

    public void setPath(LineString path) {
        this.path = path;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
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
}
