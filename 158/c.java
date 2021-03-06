import java.util.Scanner;
import java.util.Stack;
import java.util.regex.*;

public class c {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    String path = "/";

    for (int i = 0; i < n; i++) {
      String cmd = sc.next();

      if (cmd.equals("pwd")) {
        System.out.println(path);
        continue;
      }

      String newPath = sc.next();

      if (newPath.equals("/")) {
        path = newPath;
        continue;
      }

      if (newPath.charAt(0) == '/') {
        path = calculatePath(newPath);
      } else {
        path = calculatePath(path + "/" + newPath);
      }
    }
  }

  private static String calculatePath(final String old) {
    StringBuilder newPath = new StringBuilder("/");
    Stack<String> stack = new Stack<String>();
    Matcher matcher = Pattern.compile("[^/]+").matcher(old);

    while (matcher.find()) {
      String match = matcher.group();

      if (match.equals("..")) {
        stack.pop();
      } else {
        stack.push(match);
      }
    }

    Stack<String> reversed = new Stack<String>();

    while ( ! stack.empty()) {
      reversed.push(stack.pop());
    }

    while ( ! reversed.empty()) {
      newPath.append(reversed.pop());
      newPath.append("/");
    }

    return newPath.toString();
  }
}
