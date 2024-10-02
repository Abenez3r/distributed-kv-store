package com.example.kvstore;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConsistencyTest {

    @Test
    void testReplicationConsistency() throws Exception {
        ConsistentHashing consistentHashing = new ConsistentHashing(3);
        LeaderNode leaderNode = new LeaderNode(consistentHashing);

        // Add two nodes to consistent hashing
        consistentHashing.addNode("node1");
        consistentHashing.addNode("node2");

        // Register DataNodes with LeaderNode
        DataNode dataNode1 = new DataNode("node1", leaderNode);
        DataNode dataNode2 = new DataNode("node2", leaderNode);
        leaderNode.addDataNode("node1", dataNode1);
        leaderNode.addDataNode("node2", dataNode2);

        // Perform operations on node1 and check consistency
        leaderNode.put("key1", "value1");

        // Ensure key is present on both nodes
        assertEquals("value1", leaderNode.get("key1"));
        assertEquals("value1", dataNode2.get("key1")); // DataNode2 should have replicated data
    }
}
