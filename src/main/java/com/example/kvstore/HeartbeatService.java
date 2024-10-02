package com.example.kvstore;

import java.util.concurrent.*;

public class HeartbeatService {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final ConcurrentMap<String, Boolean> nodeStatus = new ConcurrentHashMap<>();

    public void startHeartbeat(String node) {
        scheduler.scheduleAtFixedRate(() -> sendHeartbeat(node), 0, 2, TimeUnit.SECONDS);
    }

    private void sendHeartbeat(String node) {
        System.out.println("Heartbeat from node " + node);
        nodeStatus.put(node, true);
    }

    public void checkNodes() {
        nodeStatus.forEach((node, status) -> {
            if (!status) {
                System.out.println("Node " + node + " is down!");
                redistributeData(node);
            }
            nodeStatus.put(node, false);
        });
    }

    private void redistributeData(String node) {
        System.out.println("Redistributing data from node " + node);
    }
}
