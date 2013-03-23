package sia.calcudoku;

import sia.gps.AStarEngine;
import sia.gps.BFSEngine;
import sia.gps.DFSEngine;
import sia.gps.GPSEngine;
import sia.gps.MultipleGPSEngine;


public class CalcuDoku {
	
	public static void main(String args[]){
		if (args == null || args.length < 2) {
			System.out.println("Ingrese [BFS|DFS|Iter|Astar] [QuadOp3x3|QuadOp4x4|5x5|QuadOp6x6|DualOp8x8");
			return;
		}
	
		// QuadOp3x3
		// QuadOp4x4
		// 5x5
		// QuadOp6x6
		// DualOp8x8
		CalcuDokuProblem problem = new CalcuDokuProblem("src/main/resources/levels/"+args[1]);
		GPSEngine engine = new MultipleGPSEngine();
		long a = System.currentTimeMillis();
		switch (args[0]) {
		case "BFS":
			engine = new BFSEngine();
			break;
		case "DFS":
			engine = new DFSEngine();
			break;
		case "Astar":
			engine = new AStarEngine();
			break;
		case "Iter":
			iterative(problem);
			return;
		default:
			break;
		}
		engine.engine(problem);
		
		System.out.println((System.currentTimeMillis()-a)/1000  + "segundos");
	}

	private static void iterative(CalcuDokuProblem problem) {
		int maxDeep = problem.board.getSize()*problem.board.getSize();
		for(int limit = maxDeep/2; limit <= maxDeep; limit++){
			GPSEngine engine = new DFSEngine(limit);
			engine.engine(problem);
		}
		
	}

}
