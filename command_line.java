import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class command_line {
    public static void main(String[] args) {
        System.out.println("Welcome to the SWEEP🧹.\nThis is a command line tool 🖥️ that helps organize your downloads folder such that it's minimal organizing it file extension‼️");

        String directory = System.getProperty("user.home") + "/Downloads";
        System.out.println("Current downloads directory 🗄️ is: " + directory +"\n") ;

        File downloadsFolder = new File(directory);
        File[] listFiles = downloadsFolder.listFiles();

        if (listFiles == null || listFiles.length == 0) {
            System.out.println("No files found in the downloads directory.");
            return;
        }

        Map<String, List<File>> fileMap = storeFiles(listFiles);
        Scanner scan = new Scanner(System.in);

        while (true) {
            Help();
            System.out.println();
            System.out.println("\nEnter a command 🤓");
            String input = scan.nextLine().trim().toUpperCase();

            switch (input) {
                case "H":
                    Help();
                    break;

                case "L":
                    listFiles(summary(listFiles));
                    break;

                case "O":
                    System.out.println("Please choose which extension type you would like to organize (e.g., '.pdf' or '.txt'):");
                    String extension = scan.nextLine().trim();
                    if (fileMap.containsKey(extension)) {
                        System.out.println("Please provide the destination folder name:");
                        String destination = scan.nextLine().trim();
                        moveFiles(destination, extension, fileMap);
                        System.out.println("Files with extension " + extension + " have been moved to the " + destination + " folder.");
                    } else {
                        System.out.println("Invalid extension. Please choose a valid extension type from the list.");
                    }
                    break;

                case "E":
                case "Q":
                    System.out.println("Exiting the program.");
                    scan.close();
                    return;

                default:
                    System.out.println("Invalid command. Type 'H' for help.");
                    break;
            }
        }
    }

    private static String getExtension(File file) {
        String fileName = file.getName();
        int dot = fileName.lastIndexOf(".");
        return (dot == -1) ? "No Extension" : fileName.substring(dot);
    }

    private static Map<String, List<String>> summary(File[] listFiles) {
        Map<String, List<String>> map = new HashMap<>();
        for (File file : listFiles) {
            if (file.isFile()) {
                String fileExtension = getExtension(file);
                map.computeIfAbsent(fileExtension, k -> new ArrayList<>()).add(file.getName());
            }
        }
        return map;
    }

    private static void listFiles(Map<String, List<String>> map) {
        
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            System.out.println();
            System.out.println("Extension: " + key + " | Files: " + value);
            System.out.println();

        }
    }

    private static void moveFiles(String destination, String fileExtension, Map<String, List<File>> fileMap) {
        File destDir = createDirectory(destination.toUpperCase());
        if (destDir != null) {
            List<File> files = fileMap.get(fileExtension);
            for (File file : files) {
                Path sourcePath = file.toPath();
                Path targetPath = Paths.get(destDir.getAbsolutePath(), file.getName());
                try {
                    Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Moved: " + file.getName());
                } catch (Exception e) {
                    System.out.println("Failed to move: " + file.getName() + " - " + e.getMessage());
                }
            }
        } else {
            System.out.println("Failed to create or access the destination directory: " + destination);
        }
    }

    private static Map<String, List<File>> storeFiles(File[] listFiles) {
        Map<String, List<File>> map = new HashMap<>();
        for (File file : listFiles) {
            if (file.isFile()) {
                String fileExtension = getExtension(file);
                map.computeIfAbsent(fileExtension, k -> new ArrayList<>()).add(file);
            }
        }
        return map;
    }

    private static File createDirectory(String destination) {
        String newDirectory = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + destination;
        File directory = new File(newDirectory);
        if (!directory.exists() && directory.mkdir()) {
            System.out.println("Created directory: " + newDirectory);
        }
        return directory;
    }

    private static void Help() {
        System.out.println();
        System.out.println("The following commands are:");
        System.out.println("O - organize: Organize your downloads folder by extension type.");
        System.out.println("L - list: List files in your downloads folder by extension type.");
        System.out.println("E or Q - exit: Exit the program.");
    }
}
