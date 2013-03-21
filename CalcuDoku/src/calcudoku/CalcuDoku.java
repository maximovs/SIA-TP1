package calcudoku;

import gps.GPSEngine;
import gps.MultipleGPSEngine;
import gps.SearchStrategy;


public class CalcuDoku {
	
	public static void main(String args[]){
		// QuadOp3x3
		// QuadOp4x4
		// 5x5
		// QuadOp6x6
		// DualOp8x8
		CalcuDokuProblem problem = new CalcuDokuProblem("levels/QuadOp3x3");
		GPSEngine engine = new MultipleGPSEngine();
		long a = System.currentTimeMillis();
		engine.engine(problem, SearchStrategy.DFS);
		System.out.println((System.currentTimeMillis()-a)/1000  + "segundos");
	}

}
