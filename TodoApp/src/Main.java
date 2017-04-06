import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
  public final static String SEPARATOR = ";";
  public final static String TASKPATH = "Tasks.txt";
  public static void main(String[] args) {
    if (args.length == 0){
      printUsage();
    } else if (args[0].equals("-l")) {
      printTasks();
    } else if (args[0].equals("-a") && args.length == 2) {
      addTask(args[1]);
    } else if (args[0].equals("-r")) {
      try {
        removeTask(Integer.parseInt(args[1]));
      } catch (IndexOutOfBoundsException iob) {
        System.out.println("Index is out of bounds");
      } catch (NumberFormatException nfe) {
        System.out.println("Index is not a number");
      }
    } else if (args[0].equals("-c")) {
      nowIsDone(Integer.parseInt(args[1]));
    } else if (args[0].equals("-s")) {
      checkStatus(Integer.parseInt(args[1]));
    } else if (args[0].equals("-h")) {
      printHelp();
    } else {
      System.out.println("Oops!! Unable to perform the action because of the wrong input format. Please check the -h option for correct format recommendations.");
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
    Path tasks = Paths.get(TASKPATH);
    try {
      List<String> taskList = Files.readAllLines(tasks);
      if (taskList.size() == 0) {
        System.out.println("No todos for today! :)");
      } else {
        for (int i = 0; i < taskList.size(); i++) {
          System.out.println(i + 1 + " - " + isDoneToString(taskList.get(i)) + " " + onlyTask(taskList.get(i)));
        }
      }
    } catch (IOException e) {
      System.out.println("Something wrong with tasks file");
    }
  }

  public static void addTask(String task) {
    Path tasks = Paths.get(TASKPATH);
    try {
      List<String> taskList = Files.readAllLines(tasks);
      String finalTask = task + ";pending";
      taskList.add(finalTask);
      Files.write(tasks, taskList);
    } catch (IOException e) {
      System.out.println("Something wrong with tasks file");
    }
  }

  public static void removeTask(int task) {
    Path tasks = Paths.get(TASKPATH);
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

  public static String isDoneToString(String task) {
    String[] subList = task.split(SEPARATOR);
    if (subList[1].equals("done")) {
      return "[X]";
    } else {
      return "[ ]";
    }
  }

  public static void nowIsDone(int orderNumber) {
    Path tasks = Paths.get(TASKPATH);
    try {
      List<String> taskList = Files.readAllLines(tasks);
      String[] subList = taskList.get(orderNumber-1).split(SEPARATOR);
      subList[1] = "done";
      taskList.set(orderNumber-1, subList[0] + ";" + subList[1]);
      Files.write(tasks, taskList);
    } catch (IOException e) {
      System.out.println("Something wrong with tasks file");
    }
  }

  public static void checkStatus(int orderNumber) {
    Path tasks = Paths.get(TASKPATH);
    try {
      List<String> taskList = Files.readAllLines(tasks);
      String[] subList = taskList.get(orderNumber - 1).split(SEPARATOR);
      if (subList[1].equals("done")) {
        System.out.println("The \"" + subList[0] + "\" task is done!");
      } else {
        System.out.println("The \"" + subList[0] + "\" task is pending!");
      }
    } catch (IOException e) {
      System.out.println("Something wrong with tasks file");
    }
  }

  public static String onlyTask(String task) {
    String[] subTasks = task.split(SEPARATOR);
    return subTasks[0];
  }

  public static void printHelp() {
    Path usage = Paths.get("Help.txt");
    try {
      List<String> usageText = Files.readAllLines(usage);
      for (int i = 0; i < usageText.size(); i++) {
        System.out.println(usageText.get(i));
      }
    } catch (IOException e) {
      System.out.println("Something wrong with help file");
    }
  }
}
