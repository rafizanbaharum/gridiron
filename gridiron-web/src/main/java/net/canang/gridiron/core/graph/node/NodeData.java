package net.canang.gridiron.core.graph.node;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

import java.util.HashSet;
import java.util.Set;

/**
 * @author rafizan.baharum
 * @since 12/9/13
 */
@NodeEntity
public class NodeData {

    @GraphId
    Long id;

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "search")
    private String name;

    public NodeData() {
    }

    public NodeData(String name) {
        this.name = name;
    }
}
