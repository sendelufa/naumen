import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class Solver implements ConundrumSolver {

  private final int[] TERMINAL_STATE = new int[]{1, 2, 3, 4, 0, 5, 6, 7};

  @Override
  public int[] resolve(int[] initialState) {

    State startState = generateState(initialState);
    State finishState = generateState(TERMINAL_STATE);

    Set<String> close = new HashSet<>();
    List<State> open = new LinkedList<>();

    open.add(startState);

    while (!open.isEmpty()) {

      List<State> tempOpens = new LinkedList<>();

      for (State state : open) {
        if (close.contains(state.getInitialString())) {
          continue;
        }
        close.add(state.getInitialString());

        if (state.equals(finishState)) {
          return state.getMoves();
        }

        int[] neighborsAmounts = state.getZeroNode().getNeighbors().stream()
            .mapToInt(Node::getAmount).toArray();

        for (int amount : neighborsAmounts) {
          State newState = generateState(state.getInitialState());
          newState.setMoves(state.getMoves());
          newState.replaceNodes(amount);
          if (!close.contains(newState.getInitialString())) {
            tempOpens.add(newState);
          }
        }
      }
      open = new LinkedList<>(tempOpens);
    }

    return null;
  }

  private State getStateWithMinF(Collection<State> open) {
    State res = null;
    int min = Integer.MAX_VALUE;
    for (State state : open) {
      if (state.getF() < min) {
        min = state.getF();
        res = state;
      }
    }
    return res;
  }

  private Collection<State> completeSolution(State terminate) {
    Queue<State> path = new LinkedList<>();
    State c = terminate;
    while (c != null) {
      path.add(c);
      c.getParent();
    }
    return path;
  }

//    public List<State> getNeighbors(State currentState) {
//
//        ArrayList<State> res = new ArrayList<State>();
//        for (int i = 0; i < currentState.getMoves().length; i++) {
//
//        }
//
//    }

  private State generateState(int[] initialState) {
    Set<Node> nodesSet = new TreeSet<>(Comparator.comparing(Node::getIndex));
    Node[] nodes = new Node[8];
    for (int i = 0; i < nodes.length; i++) {
      nodes[i] = new Node(i, initialState[i]);
    }

    nodes[0] = nodes[0].addNeighbor(nodes[1], nodes[2]);
    nodes[1] = nodes[1].addNeighbor(nodes[0], nodes[2], nodes[3]);
    nodes[2] = nodes[2].addNeighbor(nodes[0], nodes[1], nodes[5]);
    nodes[3] = nodes[3].addNeighbor(nodes[1], nodes[4], nodes[6]);
    nodes[4] = nodes[4].addNeighbor(nodes[3], nodes[5]);
    nodes[5] = nodes[5].addNeighbor(nodes[2], nodes[7], nodes[4]);
    nodes[6] = nodes[6].addNeighbor(nodes[3], nodes[7]);
    nodes[7] = nodes[7].addNeighbor(nodes[5], nodes[6]);

    for (Node node : nodes) {
      nodesSet.add(node);
    }

    return new State(nodesSet);
  }


}
