import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import junit.framework.TestCase;
import org.junit.Test;

public class TestGraf extends TestCase {

  private NaumenGraf graf;
  private Set<Node> nodes = new TreeSet<>(Comparator.comparing(Node::getIndex));

  @Override
  protected void setUp() throws Exception {

    Node node0 = new Node(0, 2);
    Node node1 = new Node(1, 1);
    Node node2 = new Node(2, 3);
    Node node3 = new Node(3, 4);
    Node node4 = new Node(4, 0);
    Node node5 = new Node(5, 5);
    Node node6 = new Node(6, 6);
    Node node7 = new Node(7, 7);
    node0 = node0.addNeighbor(node1, node2);
    node1 = node1.addNeighbor(node0, node2, node3);
    node2 = node2.addNeighbor(node0, node1, node5);
    node3 = node3.addNeighbor(node1, node4, node6);
    node4 = node4.addNeighbor(node3, node5);
    node5 = node5.addNeighbor(node2, node7, node4);
    node6 = node6.addNeighbor(node3, node7);
    node7 = node7.addNeighbor(node5, node6);
    nodes.add(node0);
    nodes.add(node1);
    nodes.add(node2);
    nodes.add(node3);
    nodes.add(node4);
    nodes.add(node5);
    nodes.add(node6);
    nodes.add(node7);
    graf = new NaumenGraf(nodes);
    super.setUp();
  }

  @Test
  public void testPrintGraf() {
    System.out.println(graf);
  }

  @Test
  public void test_move() {
    int[] moves = new int[]{5, 3, 2, 1, 2, 3, 5};
    System.out.println(graf);
    for (int i = 0; i < moves.length; i++) {
      graf.replaceNodes(moves[i]);
      System.out.println(graf);
       Arrays.stream(graf.getMoves()).forEach(System.out::println);
    }
  }

  @Test(expected = RuntimeException.class)
  public void test_findNeighbor() {
    nodes.forEach(n -> n.getNeighborWithAmount(0));
  }
}
