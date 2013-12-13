package net.canang.gridiron.core.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author rafizan.baharum
 * @since 11/29/13
 */
@Entity(name = "NodeAttribute")
@Table(name = "NODE_ATTRIBUTE")
public class NodeAttributeImpl implements NodeAttribute, Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_NODE_ATTRIBUTE")
    @SequenceGenerator(name = "SEQ_NODE_ATTRIBUTE", sequenceName = "SEQ_NODE_ATTRIBUTE", allocationSize = 1)
    private Long id;

    @NotNull
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "ATTRIBUTE_KEY", nullable = false)
    private NodeAttributeKey key;

    @Column(name = "ATTRIBUTE_VALUE")
    private String value;

    @ManyToOne(targetEntity = NodeImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "NODE_ID")
    private Node node;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NodeAttributeKey getKey() {
        return key;
    }

    public void setKey(NodeAttributeKey key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
