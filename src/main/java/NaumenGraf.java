import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NaumenGraf {

  private Node zeroNode;
  private Set<Node> nodes;
  private List<Integer> moves = new ArrayList<>();

  public NaumenGraf(Set<Node> nodes) {
    this.nodes = nodes;
    zeroNode = nodes.stream()
        .filter(n -> n.getAmount() == 0)
        .findFirst()
        .orElse(null);
    if (nodes == null) {
      throw new IllegalArgumentException();
    }
  }

  public NaumenGraf replaceNodes(int amount) {
    Node neighbor;
    try {
      neighbor = zeroNode.getNeighborWithAmount(amount);
    } catch (RuntimeException e) {
      System.err.println(e.getMessage());
      return this;
    }
    moves.add(amount);
    int amountNeighbor = neighbor.getAmount();
    int zeroNodeAmount = zeroNode.getAmount();

    Node nextNode = zeroNode;

    neighbor.setAmount(zeroNodeAmount);
    zeroNode = neighbor;

    nextNode.setAmount(amountNeighbor);

    return this;
  }

  public int[] getMoves() {
    return moves.stream().mapToInt(Integer::intValue).toArray();
  }

  @Override
  public String toString() {
    return "NaumenGraf{" +
        "zeroNode=" + zeroNode +
        ", nodes=" + nodes +
        '}';
  }
}
