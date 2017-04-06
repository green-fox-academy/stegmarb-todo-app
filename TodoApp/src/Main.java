import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    if (args.length == 0){
      printUsage();
    }
  }

  public static void printUsage() {
    Path usage = Paths.get("Usage.txt");
    try {
      List<String> usageText = Files.readAllLines(usage);
      for (int i = 0; i < usageText.size(); i++) {
        System.out.println(usageText.get(i));
      }
    } catch (IOException e) {
      System.out.println("Something wrong with usage file");
    }
  }
}
