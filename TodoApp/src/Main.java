import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    if (args.length == 0){
      printUsage();
    } else if (args[0].equals("-l")) {
      printTasks();
    } else if (args[0].equals("-a") && args.length == 2) {
      addTask(args[1]);
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

  public static void printTasks() {
    Path tasks = Paths.get("Tasks.txt");
    try {
      List<String> taskList = Files.readAllLines(tasks);
      if (taskList.size() == 0) {
        System.out.println("No todos for today! :)");
      } else {
        for (int i = 0; i < taskList.size(); i++) {
          System.out.println(i + 1 + " - " + taskList.get(i));
        }
      }
    } catch (IOException e) {
      System.out.println("Something wrong with tasks file");
    }
  }

  public static void addTask(String task) {
    Path tasks = Paths.get("Tasks.txt");
    try {
      List<String> taskList = Files.readAllLines(tasks);
      taskList.add(task);
      Files.write(tasks, taskList);
    } catch (IOException e) {
      System.out.println("Something wrong with tasks file");
    }
  }
}
