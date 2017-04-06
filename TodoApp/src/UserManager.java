import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class UserManager {
  private HashMap<String, TodoThread> users;
  private String userName;

  public void addUser(String userName) {
    this.users.put(userName, new TodoThread(taskTxtCreator(userName)));
  }

  public void checkUser(String userName) {
    if (users.size() == 0) {
      addUser(userName);
    } else {
      for (int i = 0; i < users.size(); i++) {
        if (!users.containsKey(userName)) {
          addUser(userName);
        }
      }
    }
  }

  public String taskTxtCreator(String userName) {
    String textName = userName + ".txt";
    try {
      PrintWriter writer = new PrintWriter(textName, "UTF-8");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return textName;
  }

  public UserManager(String userName){
    this.users = new HashMap<>();
    this.userName = userName;
  }

  public HashMap<String, TodoThread> getUsers() {
    return users;
  }
}
