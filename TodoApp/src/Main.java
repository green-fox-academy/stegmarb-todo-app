public class Main {
  public static void main(String[] args) {
    TodoMethods todoApp = new TodoMethods();
    if (args.length == 0){
      todoApp.printUsage();
    } else if (args[0].equals("-la")) {
      todoApp.printAllTasks();
    } else if (args[0].equals("-l")) {
      todoApp.printPendingTasks();
    } else if (args[0].equals("-a") && args.length == 2) {
      todoApp.addTask(args[1]);
    } else if (args[0].equals("-r")) {
      todoApp.removeTask(Integer.parseInt(args[1]));
    } else if (args[0].equals("-c")) {
      todoApp.nowIsDone(Integer.parseInt(args[1]));
    } else if (args[0].equals("-s")) {
      todoApp.checkStatus(Integer.parseInt(args[1]));
    } else if (args[0].equals("-h")) {
      todoApp.printHelp();
    } else {
      System.out.println("Oops!! Unable to perform the action because of the wrong input format.\n" +
              "Please check the \"-h\" option for correct format recommendations.");
    }
  }
}

