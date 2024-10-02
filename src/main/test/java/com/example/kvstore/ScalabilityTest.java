package com.example.kvstore;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScalabilityTest {

    @Test
    void testNodeScaling() throws Exception {
        ConsistentHashing consistentHashing = new ConsistentHashing(3);
        LeaderNode leaderNode = new LeaderNode(consistentHashing);

        // Add initial nodes
        consistentHashing.addNode("node1");
        consistentHashing.addNode("node2");
        DataNode dataNode1 = new DataNode("node1", leaderNode);
        DataNode dataNode2 = new DataNode("node2", leaderNode);
        leaderNode.addDataNode("node1", dataNode1);
        leaderNode.addDataNode("node2", dataNode2);

        // Add more nodes
        consistentHashing.addNode("node3");
        consistentHashing.addNode("node4");
        DataNode dataNode3 = new DataNode("node3", leaderNode);
        DataNode dataNode4 = new DataNode("node4", leaderNode);
        leaderNode.addDataNode("node3", dataNode3);
        leaderNode.addDataNode("node4", dataNode4);

        // Perform operations and check that the system adapts
        leaderNode.put("key1", "value1");
        leaderNode.put("key2", "value2");
        leaderNode.put("key3", "value3");

        assertEquals("value1", leaderNode.get("key1"));
        assertEquals("value2", leaderNode.get("key2"));
        assertEquals("value3", leaderNode.get("key3"));
    }
}
