package sia.gps;


import java.util.ArrayList;
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

	protected List<GPSNode> open = new LinkedList<GPSNode>();
	
	protected Set<GPSState> visited = new HashSet<GPSState>();

	private List<GPSNode> closed = new LinkedList<GPSNode>();

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
//				System.out.println("open: + " + open.size());
//				System.out.println("closed: + " + closed.size());
				GPSNode currentNode = open.remove(0);
//				System.out.println((((CalcuDokuState)currentNode.getState()).getStep()));
//				((CalcuDokuState)currentNode.getState()).getBoard().printBoard();
//				closed.add(currentNode);
//				open.remove(0);
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

	private  boolean explode(GPSNode node) {
//		CalcuDokuState state = (CalcuDokuState) node.getState();
//		state.updateProblem(problem);
//		state.getBoard().printBoard();
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
//				if (newState != null) {
			if (newState != null && !checkBranch(node, newState)
					&& !checkOpenAndClosed(node.getCost() + rule.getCost(),
					newState)){
					GPSNode newNode = new GPSNode(newState, node.getCost()
							+ rule.getCost());
					newNode.setParent(node);
					newNodes.add(newNode);
				}
			
			else{
//				System.out.println("REPETIDO");
			}
			
		}
		sortChildren(newNodes);
		for(GPSNode n: newNodes){
			addNode(n);
		}
		return true;
	}

	protected void sortChildren(List<GPSNode> newNodes) {
		//Por default los dejo en el orden que vienen.
		
	}

	private  boolean checkOpenAndClosed(Integer cost, GPSState state) {
//		long a = System.currentTimeMillis();
//		String b = "tiempo: ";

		for (GPSNode closedNode : closed) {
			if (closedNode.getState().compare(state)) {
				System.out.println("ASDASDSA");
				return true;
			}
		}
		if(visited.contains(state)){
			return true;
		}
			
		for (GPSNode openNode : open) {
			if (openNode.getState().compare(state) && openNode.getCost() <= cost) {
				return true;
			}
		}
//		b+= (a-System.currentTimeMillis()) + " ";
//		a=System.currentTimeMillis();
//		b+= (System.currentTimeMillis()-a) + " ";
//		System.out.println(b);
		return false;
	}

	private  boolean checkBranch(GPSNode parent, GPSState state) {
//		return false;
		if (parent == null) {
			return false;
		}
		return checkBranch(parent.getParent(), state)
				|| state.compare(parent.getState());
	}

	public abstract  void addNode(GPSNode node);

}
