public class Main {
  public static void main(String[] args) {
    TodoThread user = new TodoThread("Tasks.txt");
    user.application(args);
  }
}

