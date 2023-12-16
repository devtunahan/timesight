package at.tunahan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
  List<String> processes = getLinuxProcesses();

        // Print the process names
        for (String process : processes) {
            System.out.println(process);
        }
    }

    private static List<String> getLinuxProcesses() {
        List<String> processes = new ArrayList<>();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("wmctrl", "-l");
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                    processes.add(line);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Error executing wmctrl command. Exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return processes;
    }        
}
