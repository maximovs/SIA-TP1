package calcudoku;

import gps.GPSEngine;
import gps.MultipleGPSEngine;
import gps.SearchStrategy;


public class CalcuDoku {
	
	public static void main(String args[]){
		// QuadOp6x6
		// DualOp8x8
		// 5X5.txt
		CalcuDokuProblem problem = new CalcuDokuProblem("levels/QuadOp6x6");
		GPSEngine engine = new MultipleGPSEngine();
		engine.engine(problem, SearchStrategy.DFS);
	}

}
