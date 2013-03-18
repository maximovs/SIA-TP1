package gps;

import java.util.Deque;
import java.util.Queue;

public class MultipleGPSEngine extends GPSEngine{
	
	public MultipleGPSEngine() {
		strategy = SearchStrategy.DFS;
	}
	
	@Override
	public void addNode(GPSNode node) {
		switch (strategy) {
		case DFS:
			@SuppressWarnings("unchecked")
			Deque<GPSNode> dequeue = (Deque<GPSNode>) open;
			dequeue.push(node);
			break;
		case BFS:
			@SuppressWarnings("unchecked")
			Queue<GPSNode> queue = (Queue<GPSNode>) open;
			queue.offer(node);
			break;
		default:
			break;
		}

	}

}
