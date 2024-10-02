package com.example.kvstore;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LeaderNode {
    private final ConsistentHashing consistentHashing;
    private final Map<String, DataNode> dataNodes = new ConcurrentHashMap<>(); // Store DataNodes

    public LeaderNode(ConsistentHashing consistentHashing) {
        if (consistentHashing == null) {
            throw new IllegalArgumentException("ConsistentHashing cannot be null");
        }
        this.consistentHashing = consistentHashing;
    }

    // Register a DataNode in the system
    public void addDataNode(String nodeId, DataNode dataNode) {
        dataNodes.put(nodeId, dataNode);
    }

    public void put(String key, String value) throws Exception {
        String primaryNode = consistentHashing.getNode(key);
        if (primaryNode != null) {
            DataNode node = dataNodes.get(primaryNode);
            if (node != null) {
                node.put(key, value); // Store key-value pair in the node
                System.out.println("Stored key '" + key + "' with value '" + value + "' on primary node " + primaryNode);
            }
        }
    }

    public String get(String key) throws Exception {
        String primaryNode = consistentHashing.getNode(key);
        if (primaryNode != null) {
            DataNode node = dataNodes.get(primaryNode);
            if (node != null) {
                return node.get(key); // Fetch value from the primary node
            }
        }
        return null; // If no value found
    }
}
