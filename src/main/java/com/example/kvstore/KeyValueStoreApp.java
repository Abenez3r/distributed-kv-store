package com.example.kvstore;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class KeyValueStoreApp {
    public static void main(String[] args) throws Exception {
        // Initialize consistent hashing and leader node
        ConsistentHashing consistentHashing = new ConsistentHashing(3);
        LeaderNode leaderNode = new LeaderNode(consistentHashing);

        // Add nodes to consistent hashing
        consistentHashing.addNode("node1");
        consistentHashing.addNode("node2");

        // Register DataNodes with the LeaderNode
        DataNode dataNode1 = new DataNode("node1", leaderNode);
        DataNode dataNode2 = new DataNode("node2", leaderNode);
        leaderNode.addDataNode("node1", dataNode1);
        leaderNode.addDataNode("node2", dataNode2);

        // Initialize RaftLeaderElection and add nodes for election
        RaftLeaderElection raftElection = new RaftLeaderElection();
        raftElection.addNode(new Node("node1"));
        raftElection.addNode(new Node("node2"));

        // Start the leader election
        raftElection.startElection(new Node("node1"));

        // Simulate put and get operations
        leaderNode.put("key1", "value1");
        System.out.println("Retrieved value: " + leaderNode.get("key1"));

        // Heartbeat service (this will continue to run)
        HeartbeatService heartbeatService = new HeartbeatService();
        heartbeatService.startHeartbeat("node1");
        heartbeatService.startHeartbeat("node2");

        // Stop the heartbeat after 10 seconds (for testing)
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            System.out.println("Stopping heartbeats...");
            System.exit(0); // Stop the application after 10 seconds
        }, 10, TimeUnit.SECONDS);
    }
}
