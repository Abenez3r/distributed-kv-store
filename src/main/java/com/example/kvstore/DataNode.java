package com.example.kvstore;

import java.util.concurrent.ConcurrentHashMap;

public class DataNode {
    private String nodeId;
    private ConcurrentHashMap<String, String> keyValueStore;
    private LeaderNode leaderNode;

    public DataNode(String nodeId, LeaderNode leaderNode) {
        this.nodeId = nodeId;
        this.keyValueStore = new ConcurrentHashMap<>();
        this.leaderNode = leaderNode;
    }

    // Store key-value pair in this node
    public void put(String key, String value) {
        keyValueStore.put(key, value);
        System.out.println("Node " + nodeId + " stored key: " + key + " with value: " + value);
    }

    // Retrieve value from this node
    public String get(String key) {
        return keyValueStore.get(key);
    }

    // Add the missing getKeyValueStore method
    public ConcurrentHashMap<String, String> getKeyValueStore() {
        return keyValueStore;
    }

    public void replicate(String key, String value) {
        keyValueStore.put(key, value);
        System.out.println("Node " + nodeId + " replicated key: " + key + " with value: " + value);
    }

    public void delete(String key) {
        keyValueStore.remove(key);
    }
}
