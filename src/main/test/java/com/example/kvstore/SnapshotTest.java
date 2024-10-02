package com.example.kvstore;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.ConcurrentHashMap;

class SnapshotTest {

    @Test
    void testSnapshotAndRestore() throws Exception {
        // Initialize LeaderNode and DataNode
        LeaderNode leaderNode = new LeaderNode(new ConsistentHashing(3));
        DataNode dataNode1 = new DataNode("node1", leaderNode);

        // Put some key-value pairs
        dataNode1.put("key1", "value1");
        dataNode1.put("key2", "value2");

        // Take a snapshot of the current key-value store
        SnapshotService snapshotService = new SnapshotService();
        snapshotService.takeSnapshot(dataNode1.getKeyValueStore());

        // Delete some keys and restore the snapshot
        dataNode1.delete("key1");
        ConcurrentHashMap<String, String> restoredData = snapshotService.loadSnapshot();
        dataNode1.restoreSnapshot(restoredData);

        // Ensure that the restored data is correct
        assertEquals("value1", dataNode1.get("key1"));
        assertEquals("value2", dataNode1.get("key2"));
    }
}
