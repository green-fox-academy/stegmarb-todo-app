import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TodoMethodsTest {
  public static TodoMethods testMethod;
  private static List<String> testList = new ArrayList<>();
  public static String testPath = "TestList.txt";

  @Before
  public void instantiation() {
    testMethod = new TodoMethods(testPath);
    Path test = Paths.get(testPath);
    try {
      testList = Files.readAllLines(test);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  @Test
  public void testPrintAllTasks() {

  }

  @Test
  public void testRemoveTask() {
    int size = testList.size();
    testMethod.removeTask(1);
    assertArrayEquals();
  }


}