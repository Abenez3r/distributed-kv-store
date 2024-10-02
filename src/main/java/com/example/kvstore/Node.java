package com.example.kvstore;

import java.util.List;
import java.util.ArrayList;

public class Node {
    private String id;
    private List<String> log = new ArrayList<>();

    public Node(String id) {
        this.id = id;
    }

    public boolean vote(Node candidate) {
        return true; // Voting logic
    }

    public void appendLog(String logEntry) {
        log.add(logEntry);
    }

    public String getId() {
        return id;
    }
}
