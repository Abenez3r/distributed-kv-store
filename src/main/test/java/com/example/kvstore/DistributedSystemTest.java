package com.example.kvstore;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DistributedSystemTest {

    @Test
    void testReplicationAcrossNodes() throws Exception {
        ConsistentHashing consistentHashing = new ConsistentHashing(3);
        LeaderNode leaderNode = new LeaderNode(consistentHashing);

        // Simulate adding data nodes to the system
        DataNode node1 = new DataNode("node1", leaderNode);
        DataNode node2 = new DataNode("node2", leaderNode);

        consistentHashing.addNode("node1");
        consistentHashing.addNode("node2");

        // Simulate putting data into the leader and ensuring replication
        leaderNode.put("key1", "value1");

        // Verify replication to other nodes
        assertEquals("value1", node1.get("key1"));
        assertEquals("value1", node2.get("key1"));
    }
}
