package com.example.kvstore;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LatencyTest {

    @Test
    void testPutLatency() throws Exception {
        ConsistentHashing consistentHashing = new ConsistentHashing(3);
        LeaderNode leaderNode = new LeaderNode(consistentHashing);

        // Add nodes and register them
        consistentHashing.addNode("node1");
        consistentHashing.addNode("node2");
        DataNode dataNode1 = new DataNode("node1", leaderNode);
        leaderNode.addDataNode("node1", dataNode1);

        // Measure the time taken to perform a put operation
        long startTime = System.nanoTime();
        leaderNode.put("key1", "value1");
        long endTime = System.nanoTime();

        System.out.println("Put operation took: " + (endTime - startTime) / 1000000 + " ms");
    }
}
