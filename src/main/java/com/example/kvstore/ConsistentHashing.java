package com.example.kvstore;

import java.security.MessageDigest;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing {
    private final SortedMap<Integer, String> circle = new TreeMap<>();
    private final int numberOfReplicas;

    public ConsistentHashing(int numberOfReplicas) {
        this.numberOfReplicas = numberOfReplicas;
    }

    private int hash(String key) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(key.getBytes());
        return Math.abs(new String(digest).hashCode());
    }

    public void addNode(String node) throws Exception {
        for (int i = 0; i < numberOfReplicas; i++) {
            int hash = hash(node + i);
            circle.put(hash, node);
        }
    }

    public String getNode(String key) throws Exception {
        if (circle.isEmpty()) {
            System.out.println("No nodes available in the circle.");
            return null;  // Return null if no nodes are available
        }

        int hash = hash(key);
        if (!circle.containsKey(hash)) {
            SortedMap<Integer, String> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }

        return circle.get(hash);
    }
}
