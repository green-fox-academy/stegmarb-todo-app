import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class UserManager {
  private HashMap<String, TodoThread> users = new HashMap<>();
  private String userName;

  public void checkUser() {
    Path hashMap = Paths.get("Users.txt");
    try {
      List<String> userList = Files.readAllLines(hashMap);
      if (userList.size() == 0) {
        addUser(userName);
        userList.add(userName);
      } else if (userList.size() > 0) {
        int count = 0;
        for (int i = 0; i < userList.size() ; i++) {
          if (userList.get(i).equals(userName)) {
            count++;
          }
        }
        if (count == 0) {
          addUser(userName);
          userList.add(userName);
        } else {
          this.users.put(userName, new TodoThread(userName + ".txt"));
        }
      }
      try {
        Files.write(hashMap, userList);
      } catch (IOException e) {
        System.out.println("User data can not be saved");
      }
    } catch (IOException e) {
      System.out.println("Users file can not be loaded");
    }
  }


  public void addUser(String userName) {
    this.users.put(userName, new TodoThread(taskTxtCreator(userName)));
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
    this.userName = userName;
  }

  public HashMap<String, TodoThread> getUsers() {
    return users;
  }
}