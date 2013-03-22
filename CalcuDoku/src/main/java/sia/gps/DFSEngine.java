package sia.gps;

import java.util.Deque;

public class DFSEngine extends GPSEngine{

	@Override
	public void addNode(GPSNode node) {
		visited.add(node.getState());
			@SuppressWarnings("unchecked")
			Deque<GPSNode> dequeue = (Deque<GPSNode>) open;
			dequeue.push(node);
		
	}

}
