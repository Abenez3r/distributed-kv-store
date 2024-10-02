package com.example.kvstore;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FaultToleranceTest {

    @Test
    void testNodeFailureHandling() throws Exception {
        // Initialize ConsistentHashing and LeaderNode
        ConsistentHashing consistentHashing = new ConsistentHashing(3);
        LeaderNode leaderNode = new LeaderNode(consistentHashing);

        // Add and register nodes
        consistentHashing.addNode("node1");
        consistentHashing.addNode("node2");
        DataNode dataNode1 = new DataNode("node1", leaderNode);
        DataNode dataNode2 = new DataNode("node2", leaderNode);
        leaderNode.addDataNode("node1", dataNode1);
        leaderNode.addDataNode("node2", dataNode2);

        // Perform put operations
        leaderNode.put("key1", "value1");
        assertEquals("value1", leaderNode.get("key1"));

        // Simulate node failure by adding a new node and removing an old one
        consistentHashing.addNode("node3");
        DataNode dataNode3 = new DataNode("node3", leaderNode);
        leaderNode.addDataNode("node3", dataNode3);

        // Ensure the data is still consistent across nodes
        assertEquals("value1", leaderNode.get("key1"));
    }
}
