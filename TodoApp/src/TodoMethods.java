import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TodoMethods {
  public final static String SEPARATOR = ";";
  public  String taskPath;

  public void printUsage() {
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

  public void printAllTasks() {
    Path tasks = Paths.get(taskPath);
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

  public void printPendingTasks() {
    Path tasks = Paths.get(taskPath);
    try {
      List<String> taskList = Files.readAllLines(tasks);
      List<String> pendingTasks = new ArrayList<>();
      if (taskList.size() == 0) {
        System.out.println("No todos for today! :)");
      } else {
        for (int i = 0; i < taskList.size(); i++) {
          String[] subList = taskList.get(i).split(SEPARATOR);
          if (subList[1].equals("pending")) {
            pendingTasks.add(taskList.get(i));
          }
        }
        Files.write(Paths.get("PendingTasks.txt"), pendingTasks);
        for (int i = 0; i < pendingTasks.size(); i++) {
          System.out.println(i + 1 + " - " + isDoneToString(pendingTasks.get(i)) + " " + onlyTask(pendingTasks.get(i)));
        }
        System.out.println("\n!!!Don't forget, that the removal option takes into account the numbering of AllTasks!!!\n" +
                "Call the -la option before using it!!!");
      }
    }catch(IOException e) {
      System.out.println("Something wrong with tasks file");
    }
  }

  public void addTask(String task) {
    Path tasks = Paths.get(taskPath);
    try {
      List<String> taskList = Files.readAllLines(tasks);
      String finalTask = task + ";pending";
      if (taskList.size() == 0) {
        taskList.add(0, finalTask);
      } else {
        taskList.add(finalTask);
      }
      Files.write(tasks, taskList);
      System.out.println("Task has been added!");
    } catch (IOException e) {
      System.out.println("Something wrong with tasks file");
    }
  }

  public void removeTask(int task) {
    try {
      Path tasks = Paths.get(taskPath);
      try {
        List<String> taskList = Files.readAllLines(tasks);
        if (taskList.size() <= 2) {
          return;
        } else {
          taskList.remove(task - 1);
        }
        Files.write(tasks, taskList);
        System.out.println("Task has been removed");
      } catch (IOException e) {
        System.out.println("Something wrong with tasks file");
      }
    } catch (IndexOutOfBoundsException iob) {
      System.out.println("Index is out of bounds");
    } catch (NumberFormatException nfe) {
      System.out.println("Index is not a number");
    }
  }

  public String isDoneToString(String task) {
    String[] subList = task.split(SEPARATOR);
    if (subList[1].equals("done")) {
      return "[X]";
    } else {
      return "[ ]";
    }
  }

  public void nowIsDone(int orderNumber) {
    try {
      Path tasks = Paths.get(taskPath);
      try {
        List<String> taskList = Files.readAllLines(tasks);
        String[] subList = taskList.get(orderNumber - 1).split(SEPARATOR);
        subList[1] = "done";
        taskList.set(orderNumber - 1, subList[0] + ";" + subList[1]);
        Files.write(tasks, taskList);
      } catch (IOException e) {
        System.out.println("Something wrong with tasks file");
      }
    } catch (IndexOutOfBoundsException iob) {
      System.out.println("Index is out of bounds");
    } catch (NumberFormatException nfe) {
      System.out.println("Index is not a number");
    }
  }

  public void checkStatus(int orderNumber) {
    try {
      Path tasks = Paths.get(taskPath);
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
    } catch (IndexOutOfBoundsException iob) {
      System.out.println("Index is out of bounds");
    } catch (NumberFormatException nfe) {
      System.out.println("Index is not a number");
    }
  }

  public String onlyTask(String task) {
    String[] subTasks = task.split(SEPARATOR);
    return subTasks[0];
  }

  public void printHelp() {
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

  public TodoMethods(String taskPath){
    this.taskPath = taskPath;
  }
}
