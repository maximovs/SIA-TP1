package gps;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;
import gps.exception.NotAppliableException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import calcudoku.CalcuDokuState;

public abstract class GPSEngine {

	protected List<GPSNode> open = new LinkedList<GPSNode>();

	private List<GPSNode> closed = new ArrayList<GPSNode>();

	private GPSProblem problem;

	// Use this variable in the addNode implementation
	protected SearchStrategy strategy;

	public void engine(GPSProblem myProblem, SearchStrategy myStrategy) {

		problem = myProblem;
		strategy = myStrategy;

		GPSNode rootNode = new GPSNode(problem.getInitState(), 0);
		boolean finished = false;
		boolean failed = false;
		long explosionCounter = 0;

		open.add(rootNode);
		while (!failed && !finished) {
//			System.out.println(open);
			if (open.size() <= 0) {
				failed = true;
			} else {
				System.out.println("open: + " + open.size());
				System.out.println("closed: + " + closed.size());
				GPSNode currentNode = open.remove(0);
				if(((CalcuDokuState)currentNode.getState()).getStep() == 24)
					System.out.println("BLA");
//				System.out.println((((CalcuDokuState)currentNode.getState()).getStep()));
//				((CalcuDokuState)currentNode.getState()).getBoard().printBoard();
				closed.add(currentNode);
//				open.remove(0);
				CalcuDokuState currentState = (CalcuDokuState)currentNode.getState();
				int size = currentState.getBoard().getSize();
				if(currentState.getStep() == size*size){
					currentState.updateProblem(problem);
					if (isGoal(currentNode)) {
						finished = true;
						System.out.println(currentNode.getSolution());
						System.out.println("Expanded nodes: " + explosionCounter);
						((CalcuDokuState)currentNode.getState()).getBoard().printBoard();
					}
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
		CalcuDokuState state = (CalcuDokuState) node.getState();
		state.updateProblem(problem);
		if(problem.getRules() == null || problem.getRules().isEmpty()){
			System.err.println("No rules!");
			return false;
		}
		state.getBoard().printBoard();
		for (GPSRule rule : problem.getRules()) {
			
			GPSState newState = null;
			try {
				newState = rule.evalRule(node.getState());
			} catch (NotAppliableException e) {
				// Do nothing
				System.out.println("ERRRORRR");
			}
				if (newState != null
						&& !checkBranch(node, newState)
						&& !checkOpenAndClosed(node.getCost() + rule.getCost(),
								newState)) {
					GPSNode newNode = new GPSNode(newState, node.getCost()
							+ rule.getCost());
					newNode.setParent(node);
					addNode(newNode);
				}
//			if (newState != null) {
//				GPSNode newNode = new GPSNode(newState, node.getCost()
//						+ rule.getCost());
//				newNode.setParent(node);
////				System.out.println("estado agregado:" + (CalcuDokuState)newNode.getState());
//				addNode(newNode);
//			}
		}
		return true;
	}

	private  boolean checkOpenAndClosed(Integer cost, GPSState state) {
		for (GPSNode openNode : open) {
			if (openNode.getState().compare(state) && openNode.getCost() < cost) {
				return true;
			}
		}
		for (GPSNode closedNode : closed) {
			if (closedNode.getState().compare(state)
					&& closedNode.getCost() < cost) {
				return true;
			}
		}
		return false;
	}

	private  boolean checkBranch(GPSNode parent, GPSState state) {
		if (parent == null) {
			return false;
		}
		return checkBranch(parent.getParent(), state)
				|| state.compare(parent.getState());
	}

	public abstract  void addNode(GPSNode node);

}
