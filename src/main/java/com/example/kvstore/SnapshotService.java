package com.example.kvstore;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class SnapshotService {
    private final String snapshotFile = "kvstore_snapshot.dat";

    public void takeSnapshot(ConcurrentHashMap<String, String> data) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(snapshotFile))) {
            oos.writeObject(data);
            System.out.println("Snapshot taken successfully.");
        }
    }

    public ConcurrentHashMap<String, String> loadSnapshot() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(snapshotFile))) {
            return (ConcurrentHashMap<String, String>) ois.readObject();
        }
    }
}
