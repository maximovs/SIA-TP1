package gps;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DFSGPSEngine extends GPSEngine{
	@Override
	public void addNode(GPSNode node) {
		Deque<GPSNode> dequeue = (Deque) open;
		dequeue.push(node);
	}

}
