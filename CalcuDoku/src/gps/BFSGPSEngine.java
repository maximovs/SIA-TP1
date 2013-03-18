package gps;

import java.util.LinkedList;
import java.util.Queue;

public class BFSGPSEngine extends GPSEngine{
	@Override
	public void addNode(GPSNode node) {
		Queue<GPSNode> queue = (Queue) open;
		queue.offer(node);
	}

}
