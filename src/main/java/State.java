
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class State {

  private Node zeroNode;


  private Set<Node> nodes;
  private List<Integer> moves = new ArrayList<>();
  private int g = 0;
  private int h;
  private int f = h + g;
  private State parent;

  public State(Set<Node> nodes_) {
    nodes = new HashSet<>();
    nodes.addAll(nodes_);
    zeroNode = nodes.stream()
        .filter(n -> n.getAmount() == 0)
        .findFirst()
        .orElse(null);
  }

  public int[] getInitialState() {
    return nodes.stream().sorted(Comparator.comparing(Node::getIndex)).mapToInt(Node::getAmount)
        .toArray();
  }

  public String getInitialString() {
    StringBuilder res = new StringBuilder(8);
    for (int node : getInitialState()) {
      res.append(node);
    }
    return res.toString();
  }


  public State replaceNodes(int amount) {
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

  public void setMoves(int[] moves) {
    this.moves = new ArrayList<>();
    for (int move : moves) {
      this.moves.add(move);
    }
  }


  public Set<Node> getNodes() {
    return nodes;
  }

  public int[] getMoves() {
    return moves.stream().mapToInt(Integer::intValue).toArray();
  }

  public Node getZeroNode() {
    return zeroNode;
  }

  public int getG() {
    return g;
  }

  public void setG(int g) {
    this.g = g;
  }

  public int getH() {
    return h;
  }

  public void setH(int h) {
    this.h = h;
  }

  public int getF() {
    return f;
  }

  public State getParent() {
    return parent;
  }

  public void setParent(State parent) {
    this.parent = parent;
  }

  public boolean isTerminate() {
    if (h == 0) {
      return true;
    }
    return false;
  }

  @Override
  public boolean equals(Object o) {

    State state = (State) o;

    return this.nodes.toString().equals(state.nodes.toString());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getNodes());
  }

  @Override
  public String toString() {
    return "NaumenGraf{" +
        "zeroNode=" + zeroNode +
        ", nodes=" + nodes +
        '}';
  }
}
