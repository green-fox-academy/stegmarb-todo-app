public class TodoThread extends TodoMethods {
  private String userName;
  private String userPath = userName + ".txt";

  public void application(String[] todo) {
    if (todo.length == 1 && todo[0] == userName ) {
      printUsage();
    } else if (todo[1].equals("-la")) {
      printAllTasks();
    } else if (todo[1].equals("-l")) {
      printPendingTasks();
    } else if (todo[1].equals("-a")) {
      addTask(todo[2]);
    } else if (todo[1].equals("-r")) {
      removeTask(Integer.parseInt(todo[2]));
    } else if (todo[1].equals("-c")) {
      nowIsDone(Integer.parseInt(todo[2]));
    } else if (todo[1].equals("-s")) {
      checkStatus(Integer.parseInt(todo[2]));
    } else if (todo[1].equals("-h")) {
      printHelp();
    } else {
      System.out.println("Oops!! Unable to perform the action because of the wrong input format.\n" +
              "Please check the \"-h\" option for correct format recommendations.");
    }
  }
  public TodoThread(String taskPath) {
    super(taskPath);
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
