import java.io.*;
import java.nio.file.*;
import java.util.*;

public class MulCsvintoBinary {

    public static void main(String[] args) {
        String[] csvPaths = { "E:/New folder/New/one.csv",
                "E:/New folder/New/two.csv",
                "E:/New folder/New/three.csv" };

        Map<String, byte[]> csvBytesByPath = new LinkedHashMap<>();// map to store inmemory

        for (String pathStr : csvPaths) {
            Path csvPath = Paths.get(pathStr);

            if (Files.exists(csvPath) && csvPath.toString().toLowerCase().endsWith(".csv")) {
                try {
                    byte[] data = Files.readAllBytes(csvPath);
                    csvBytesByPath.put(csvPath.toString(), data);
                    System.out.printf("Loaded: %s (%,d bytes)%n", csvPath.getFileName(), data.length);
                } catch (IOException e) {
                    System.err.printf("Failed to read %s: %s%n", csvPath, e.getMessage());
                }
            } else {
                System.err.println("Invalid path or not a CSV: " + pathStr);
            }
        }

        System.out.println("\n===Details of file store in memory ===");
        long totalBytes = 0;
        for (Map.Entry<String, byte[]> entry : csvBytesByPath.entrySet()) {
            totalBytes += entry.getValue().length;
            System.out.printf("- %s -> %,d bytes%n", entry.getKey(), entry.getValue().length);
        }
        System.out.printf("Total files: %d, Total bytes: %,d%n", csvBytesByPath.size(), totalBytes);

    }

}
