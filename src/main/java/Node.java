import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Node {

  private final int index;
  private int amount;
  private Set<Node> neighbors = new HashSet<>();

  public Node(int index, int amount) {
    this.index = index;
    this.amount = amount;
  }

  private Node(int index, int amount, Set<Node> newNodes) {
    this(index, amount);
    this.neighbors = newNodes;
  }


  public void setAmount(int amount) {
    this.amount = amount;
  }



  public Node addNeighbor(Node... neighbor) {
    neighbors.addAll(Arrays.asList(neighbor));
    return this;
  }

//  public Optional<Node> getNeighborWithAmount(int amount) {
//    for (Node node : neighbors) {
//      if (node.getAmount() == amount) {
//        return Optional.of(node);
//      }
//    }
//    return Optional.empty();
//  }


  public Node getNeighborWithAmount(int amount) {
    for (Node node : neighbors) {
      if (node.getAmount() == amount) {
        return node;
      }
    }
    throw new RuntimeException("neighbor not found");
  }

  public Set<Node> getNeighbors() {
    return neighbors;
  }

  public int getAmount() {
    return amount;
  }

  public int getIndex() {
    return index;
  }


  @Override
  public String toString() {
    return
        "[" + index +
        " " + amount + "]";
      //  ",n.size=" + neighbors.size() +

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Node node = (Node) o;
    return getIndex() == node.getIndex();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getIndex());
  }
}
