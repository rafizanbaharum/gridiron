package net.canang.gridiron.core.model;

/**
 * @author rafizan.baharum
 * @since 11/29/13
 */
public interface NodeTransaction {

    /**
     * @return
     */
    Node getNode();

    void setNode(Node node);

    /**
     * @return
     */
    Integer getAmount();

    void setAmount(Integer amount);

}
