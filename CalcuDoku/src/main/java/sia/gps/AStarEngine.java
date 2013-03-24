package sia.gps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarEngine extends GPSEngine{

	private final int INITIAL_SIZE = 1000;
	
	public AStarEngine() {
		open = new PriorityQueue<GPSNode>(INITIAL_SIZE, new Comparator<GPSNode>() {
			@Override
			public int compare(GPSNode node1, GPSNode node2) {
				return (node1.f() != node2.f()) ? node1.f()-node2.f() : node1.h() - node2.h();
			}
		});
	}
	
	@Override
	public void addNode(GPSNode node) {
		node.setHValue(problem.getHValue(node.getState()));
		open.add(node);
	}
	
	@Override
	protected GPSNode removeFirst() {
		return ((PriorityQueue<GPSNode>) open).remove();
	}
}
