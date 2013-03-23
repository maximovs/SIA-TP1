package sia.gps;

import java.util.Deque;

import sia.calcudoku.CalcuDokuState;

public class DFSEngine extends GPSEngine{

	private int limit = 0;
	
	public DFSEngine() {
		super();
	}
	
	public DFSEngine(int limit) {
		this.limit = limit;
	}

	@Override
	public void addNode(GPSNode node) {
		if( ((CalcuDokuState)node.getState()).getDepth() > limit ){
			//Se alcanzo la altura maxima
			return;
		}
		visited.add(node.getState());
			@SuppressWarnings("unchecked")
			Deque<GPSNode> dequeue = (Deque<GPSNode>) open;
			dequeue.push(node);
		
	}

}
