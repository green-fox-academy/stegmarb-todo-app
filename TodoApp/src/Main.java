public class Main {
  private static String userName;
  public static void main(String[] args) {
    userName = args[0];
    UserManager manager = new UserManager(userName);
    manager.checkUser(userName);
    TodoThread user =(manager.getUsers().get(userName));
    user.setUserName(userName);
    user.application(args);
  }
}

