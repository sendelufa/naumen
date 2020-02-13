import java.util.Arrays;

public class Main {

  public static void main(String[] args) {


    Solver solver = new Solver();

    int[] moves = solver.resolve(new int[]{0, 2, 4, 7, 5, 3, 6, 1});

    Arrays.stream(moves).forEach(System.out::print);
    System.out.println();
    System.out.println(moves.length);

  }
}
