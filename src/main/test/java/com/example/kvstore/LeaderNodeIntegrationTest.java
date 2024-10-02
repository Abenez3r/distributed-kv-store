package com.example.kvstore;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LeaderNodeIntegrationTest {

    @Test
    void testMultipleNodes() throws Exception {
        // Initialize ConsistentHashing with 3 replicas
        ConsistentHashing consistentHashing = new ConsistentHashing(3);
        LeaderNode leaderNode = new LeaderNode(consistentHashing);

        // Add nodes to consistent hashing
        consistentHashing.addNode("node1");
        consistentHashing.addNode("node2");
        consistentHashing.addNode("node3");

        // Register DataNodes with the LeaderNode
        DataNode dataNode1 = new DataNode("node1", leaderNode);
        DataNode dataNode2 = new DataNode("node2", leaderNode);
        DataNode dataNode3 = new DataNode("node3", leaderNode);
        leaderNode.addDataNode("node1", dataNode1);
        leaderNode.addDataNode("node2", dataNode2);
        leaderNode.addDataNode("node3", dataNode3);

        // Perform put and get operations on multiple nodes
        leaderNode.put("key1", "value1");
        leaderNode.put("key2", "value2");
        leaderNode.put("key3", "value3");

        // Verify that the values can be correctly retrieved
        assertEquals("value1", leaderNode.get("key1"));
        assertEquals("value2", leaderNode.get("key2"));
        assertEquals("value3", leaderNode.get("key3"));
    }
}
