package sia.gps;

import java.util.Queue;

import sia.calcudoku.CalcuDokuProblem;
import sia.gps.api.GPSProblem;

public class BFSEngine extends GPSEngine{

	@Override
	public void addNode(GPSNode node) {
		visited.add(node.getState());
		@SuppressWarnings("unchecked")
		Queue<GPSNode> queue = (Queue<GPSNode>) open;
		queue.offer(node);
		
	}

}
