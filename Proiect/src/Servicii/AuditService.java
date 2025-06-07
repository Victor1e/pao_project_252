package Servicii;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AuditService {
    private static final String AUDIT_FILE = "audit.csv";

    public static void log(String action) {
        String record = action + "," + LocalDateTime.now();
        try (FileWriter fw = new FileWriter(AUDIT_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
