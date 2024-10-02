package com.example.kvstore;

import java.io.*;
import java.util.zip.*;

public class CompressionService {
    public byte[] compress(String data) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipStream = new GZIPOutputStream(byteStream)) {
            gzipStream.write(data.getBytes());
        }
        return byteStream.toByteArray();
    }

    public String decompress(byte[] compressedData) throws IOException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(compressedData);
        try (GZIPInputStream gzipStream = new GZIPInputStream(byteStream);
             InputStreamReader reader = new InputStreamReader(gzipStream);
             BufferedReader in = new BufferedReader(reader)) {
            StringBuilder decompressed = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                decompressed.append(line);
            }
            return decompressed.toString();
        }
    }
}
