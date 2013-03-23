package sia.gps;

import java.util.Comparator;

import sia.gps.api.GPSProblem;

public class NodeComparator implements Comparator<GPSNode> {

	private GPSProblem problem;
	
	public NodeComparator(GPSProblem problem) {
		this.problem = problem;
	}

	@Override
	public int compare(GPSNode node1, GPSNode node2) {
		int hValueDif = problem.getHValue(node2.getState()) - problem.getHValue(node1.getState());
		if(hValueDif != 0){
			return hValueDif;
		}
		return 1;
	}

}