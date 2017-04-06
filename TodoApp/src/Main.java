import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
  public final static String SEPARATOR = ";";
  public static void main(String[] args) {
    if (args.length == 0){
      printUsage();
    } else if (args[0].equals("-l")) {
      printTasks();
    } else if (args[0].equals("-a") && args.length == 3) {
      addTask(args[1], args[2]);
    } else if (args[0].equals("-r")) {
      removeTask(Integer.parseInt(args[1]));
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
    Path tasks = Paths.get("Tasks.csv");
    try {
      List<String> taskList = Files.readAllLines(tasks);
      if (taskList.size() == 0) {
        System.out.println("No todos for today! :)");
      } else {
        for (int i = 0; i < taskList.size(); i++) {
          System.out.println(i + 1 + " - " + isDone(taskList.get(i)) + " " + onlyTask(taskList.get(i)));
        }
      }
    } catch (IOException e) {
      System.out.println("Something wrong with tasks file");
    }
  }

  public static void addTask(String task, String done) {
    Path tasks = Paths.get("Tasks.csv");
    try {
      List<String> taskList = Files.readAllLines(tasks);
      String finalTask = task + ";" + done;
      taskList.add(finalTask);
      Files.write(tasks, taskList);
    } catch (IOException e) {
      System.out.println("Something wrong with tasks file");
    }
  }

  public static void removeTask(int task) {
    Path tasks = Paths.get("Tasks.csv");
    try {
      List<String> taskList = Files.readAllLines(tasks);
      if (taskList.size() <= 2) {
        return;
      } else {
        taskList.remove(task - 1);
      }
      Files.write(tasks, taskList);
    } catch (IOException e) {
      System.out.println("Something wrong with tasks file");
    }
  }

  public static String isDone(String task) {
    String[] subList = task.split(SEPARATOR);
    if (subList[1].equals("true")) {
      return "[X]";
    } else {
      return "[ ]";
    }
  }

  public static String onlyTask(String task) {
    String[] subTasks = task.split(SEPARATOR);
    return subTasks[0];
  }
}
