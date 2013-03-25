package sia.gps;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import sia.calcudoku.CalcuDokuState;

public class HIDFSEngine extends DFSEngine{


	private int BFSLimit;
	private int DFSLimit;
	private static final int BFS = 0;
	private static final int DFS = 1;
	private int mode = BFS;


	private List<GPSNode> frontera = new LinkedList<GPSNode>();

	public HIDFSEngine(int limit) {
		this.BFSLimit = limit;
		DFSLimit = BFSLimit+1;
	}

	@Override
	public void addNode(GPSNode node) {
		if(mode == BFS){
			Queue<GPSNode> queue = (Queue<GPSNode>) open;
			queue.offer(node);
		}else{
			Deque<GPSNode> dequeue = (Deque<GPSNode>) open;
			dequeue.push(node);
		}
	}

	protected  boolean explode(GPSNode node) {
		if(mode == BFS){
			if(((CalcuDokuState)node.getState()).getDepth() == BFSLimit ){
				frontera.add(node);
				frontera.addAll(open);
				mode = DFS;
				return super.explode(node);
			}
		}else{
			if(((CalcuDokuState)node.getState()).getDepth() > DFSLimit ){
				open.clear();
				visited.clear();
				open.addAll(frontera);
				DFSLimit++;
				return true;
			}
		}
		return super.explode(node);
	}

}
