package sia.gps;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import sia.calcudoku.CalcuDokuState;
import sia.gps.api.GPSProblem;
import sia.gps.api.GPSRule;
import sia.gps.api.GPSState;
import sia.gps.exception.NotAppliableException;


public abstract class GPSEngine {

	protected Collection<GPSNode> open = new LinkedList<GPSNode>();
	
	protected Set<GPSState> visited = new HashSet<GPSState>();

	protected GPSProblem problem;

	// Use this variable in the addNode implementation
	protected SearchStrategy strategy;

	public void engine(GPSProblem myProblem) {

		problem = myProblem;

		GPSNode rootNode = new GPSNode(problem.getInitState(), 0);
		boolean finished = false;
		boolean failed = false;
		long explosionCounter = 0;

		open.add(rootNode);
		visited.add(rootNode.getState());
		while (!failed && !finished) {
//			System.out.println(open);
			if (open.size() <= 0) {
				failed = true;
			} else {
				GPSNode currentNode = removeFirst();
				CalcuDokuState currentState = (CalcuDokuState)currentNode.getState();
				currentState.updateProblem(problem);
				if (isGoal(currentNode)) {
					finished = true;
					System.out.println(currentNode.getSolution());
					System.out.println("Expanded nodes: " + explosionCounter);
					((CalcuDokuState)currentNode.getState()).getBoard().printBoard();
				}
				else {
					explosionCounter++;
					explode(currentNode);
				}
			}
		}

		if (finished) {
			System.out.println("OK! solution found!");
			
		} else if (failed) {
			System.err.println("FAILED! solution not found!");
		}
	}

	private  boolean isGoal(GPSNode currentNode) {
		return currentNode.getState() != null
				&& currentNode.getState().isFinished();
	}

	protected  boolean explode(GPSNode node) {
		List<GPSRule> rules = problem.getRules(); 
		if(rules == null || rules.isEmpty()){
			System.err.println("No rules!");
			return false;
		}
		List<GPSNode> newNodes = new ArrayList<>();
		for (GPSRule rule : rules) {
			GPSState newState = null;
			try {
				((CalcuDokuState)node.getState()).updateProblem(problem);
				newState = rule.evalRule(node.getState());
			} catch (NotAppliableException e) {
				// Do nothing
			}
			if (newState != null && !checkBranch(node, newState)
					&& !checkVisited(node.getCost() + rule.getCost(),
					newState)){
					GPSNode newNode = new GPSNode(newState, node.getCost()
							+ rule.getCost());
					newNode.setParent(node);
					newNodes.add(newNode);
				}
		}
		sortChildren(newNodes);
		for(GPSNode n: newNodes){
			addNode(n);
		}
		return true;
	}

	//It's used as a hook so it can be used by subclasses.
	protected void sortChildren(List<GPSNode> newNodes) {}

	private  boolean checkVisited(Integer cost, GPSState state) {
		return visited.contains(state);
	}

	private  boolean checkBranch(GPSNode parent, GPSState state) {
		if (parent == null) {
			return false;
		}
		return checkBranch(parent.getParent(), state)
				|| state.compare(parent.getState());
	}

	public abstract  void addNode(GPSNode node);

	protected GPSNode removeFirst() {
		return ((List<GPSNode>)open).remove(0);
	}

}
