package com.example.kvstore;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LeaderNodeTest {

    @Test
    void testPutAndGet() throws Exception {
        // Initialize ConsistentHashing
        ConsistentHashing consistentHashing = new ConsistentHashing(3);
        System.out.println("ConsistentHashing initialized: " + consistentHashing);

        // Initialize LeaderNode
        LeaderNode leaderNode = new LeaderNode(consistentHashing);
        System.out.println("LeaderNode initialized: " + leaderNode);

        // Add a node to consistent hashing and register it with LeaderNode
        consistentHashing.addNode("node1");
        DataNode dataNode1 = new DataNode("node1", leaderNode);
        leaderNode.addDataNode("node1", dataNode1); // Register DataNode

        // Put a key-value pair
        leaderNode.put("key1", "value1");
        System.out.println("Put key 'key1' with value 'value1'");

        // Retrieve the value
        String value = leaderNode.get("key1");
        System.out.println("Retrieved value for 'key1': " + value);

        // Validate the result
        assertEquals("value1", value);
    }
}
