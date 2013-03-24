package sia.gps;

import java.util.Deque;


public class DFSEngine extends GPSEngine{


	public DFSEngine() {
		super();
	}
	

	@Override
	public void addNode(GPSNode node) {
		visited.add(node.getState());
			Deque<GPSNode> dequeue = (Deque<GPSNode>) open;
			dequeue.push(node);
		
	}

}
