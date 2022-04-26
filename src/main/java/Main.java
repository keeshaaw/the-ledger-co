import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = args[0];

        List<String> requestedCommands = null;
        try {
            requestedCommands = Files.readAllLines(Paths.get(filePath));
        } catch (IOException exception) {
            System.out.println("[ERROR] Could not find any file at path: " + filePath);
        }

        if(requestedCommands != null) {

        }
    }
}
