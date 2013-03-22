package sia.calcudoku;

import sia.gps.GPSEngine;
import sia.gps.MultipleGPSEngine;
import sia.gps.SearchStrategy;


public class CalcuDoku {
	
	public static void main(String args[]){
		// QuadOp3x3
		// QuadOp4x4
		// 5x5
		// QuadOp6x6
		// DualOp8x8
		CalcuDokuProblem problem = new CalcuDokuProblem("src/main/resources/levels/QuadOp4x4");
		GPSEngine engine = new MultipleGPSEngine();
		long a = System.currentTimeMillis();
		engine.engine(problem, SearchStrategy.BFS);
		System.out.println((System.currentTimeMillis()-a)/1000  + "segundos");
	}

}
