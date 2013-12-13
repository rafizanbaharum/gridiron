package net.canang.gridiron.core.model;

/**
 * @author rafizan.baharum
 * @since 11/29/13
 */
public interface NodeAttribute {

    /**
     * @return
     */
    NodeAttributeKey getKey();

    void setKey(NodeAttributeKey key);

    /**
     * @return
     */
    String getValue();

    void setValue(String value);


    /**
     * @return
     */
    Node getNode();

    void setNode(Node node);

}
