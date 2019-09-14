package rocks.danielw.mockito.examples.dummys;

public class MathUtils {

  public int add(int x, int y) {
    return x + y;
  }

  public boolean isInteger(String s) {
    try {
      Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

}
