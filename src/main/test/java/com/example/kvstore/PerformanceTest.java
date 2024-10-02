package com.example.kvstore;

import org.junit.jupiter.api.Test;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;

class PerformanceTest {

    @Test
    void testConcurrentAccess() throws InterruptedException {
        // Initialize the ConsistentHashing and LeaderNode
        ConsistentHashing consistentHashing = new ConsistentHashing(3);
        LeaderNode leaderNode = new LeaderNode(consistentHashing);

        // Add nodes and register them
        consistentHashing.addNode("node1");
        DataNode dataNode1 = new DataNode("node1", leaderNode);
        leaderNode.addDataNode("node1", dataNode1);

        // Simulate concurrent requests using threads
        int numThreads = 100;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            final int keyNum = i;
            executor.submit(() -> {
                try {
                    leaderNode.put("key" + keyNum, "value" + keyNum);
                    assertEquals("value" + keyNum, leaderNode.get("key" + keyNum));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        // Shutdown the executor and await termination
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}
