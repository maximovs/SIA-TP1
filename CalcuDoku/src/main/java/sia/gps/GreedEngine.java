package sia.gps;

import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class GreedEngine extends DFSEngine {
	
	@Override
	protected void sortChildren(List<GPSNode> newNodes) {
		Comparator<GPSNode> comparator = new NodeComparator(this.problem);
		SortedSet<GPSNode> ordered = new TreeSet<>(comparator);
		ordered.addAll(newNodes);
		newNodes.clear();
		newNodes.addAll(ordered);
	}
}
