import java.util.ArrayList;
import java.util.List;

public class Data {
private List<String> strings = new ArrayList<String>();

  public Data() {
    for (int i = 0; i < 100; i++) {
      strings.add(String.valueOf(i));
    }
  }

  public List<String> getStrings() {
    return new ArrayList<>(strings);
  }

}
