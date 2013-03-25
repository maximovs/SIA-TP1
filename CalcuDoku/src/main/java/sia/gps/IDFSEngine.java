package sia.gps;

import sia.calcudoku.CalcuDokuState;

public class IDFSEngine extends DFSEngine{


	private int limit;

	public IDFSEngine(int limit) {
		this.limit = limit;
	}


	protected  boolean explode(GPSNode node) {
		if(((CalcuDokuState)node.getState()).getDepth() > limit ){
			open.clear();
			visited.clear();
			open.add(new GPSNode(problem.getInitState(), 0));
			limit++;
			return true;
		}
		return super.explode(node);
	}

}
