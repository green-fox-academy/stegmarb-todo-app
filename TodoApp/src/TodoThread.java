public class TodoThread extends TodoMethods {
  private String[] args;

  public void application(String[] todo) {
    if (todo.length == 0) {
      printUsage();
    } else if (todo[0].equals("-la")) {
      printAllTasks();
    } else if (todo[0].equals("-l")) {
      printPendingTasks();
    } else if (todo[0].equals("-a") && todo.length == 2) {
      addTask(todo[1]);
    } else if (todo[0].equals("-r")) {
      removeTask(Integer.parseInt(todo[1]));
    } else if (todo[0].equals("-c")) {
      nowIsDone(Integer.parseInt(todo[1]));
    } else if (todo[0].equals("-s")) {
      checkStatus(Integer.parseInt(todo[1]));
    } else if (todo[0].equals("-h")) {
      printHelp();
    } else {
      System.out.println("Oops!! Unable to perform the action because of the wrong input format.\n" +
              "Please check the \"-h\" option for correct format recommendations.");
    }
  }
  public TodoThread(String taskPath) {
    super(taskPath);
  }
}
