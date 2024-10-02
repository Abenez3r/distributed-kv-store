package com.example.kvstore;

import java.util.ArrayList;
import java.util.List;

public class RaftLeaderElection {
    private List<Node> nodes = new ArrayList<>(); // Initialize the list of nodes
    private Node leader;

    // Method to add nodes to the election process
    public void addNode(Node node) {
        nodes.add(node);
    }

    public void startElection(Node candidate) {
        if (nodes == null || nodes.isEmpty()) {
            throw new IllegalStateException("No nodes available for election.");
        }
        int votes = 0;
        for (Node node : nodes) {
            if (node.vote(candidate)) {
                votes++;
            }
        }
        if (votes > nodes.size() / 2) {
            leader = candidate;
            System.out.println("New leader elected: " + leader.getId());
        } else {
            System.out.println("No leader elected.");
        }
    }

    public void replicateLog(String logEntry) {
        if (leader != null) {
            leader.appendLog(logEntry);
        }
    }
}
