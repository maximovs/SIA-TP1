package sia.gps;

import java.util.Queue;


public class BFSEngine extends GPSEngine{

	@Override
	public void addNode(GPSNode node) {
		visited.add(node.getState());
		Queue<GPSNode> queue = (Queue<GPSNode>) open;
		queue.offer(node);
	}

}
