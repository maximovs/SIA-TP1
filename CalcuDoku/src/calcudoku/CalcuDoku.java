package calcudoku;

import gps.GPSEngine;
import gps.MultipleGPSEngine;
import gps.SearchStrategy;


public class CalcuDoku {
	
	public static void main(String args[]){
		// QuadOp6x6
		// DualOp8x8
		// 5X5.txt
		CalcuDokuProblem problem = new CalcuDokuProblem("levels/DualOp8x8");
		GPSEngine engine = new MultipleGPSEngine();
		long a = System.currentTimeMillis();
		engine.engine(problem, SearchStrategy.BFS);
		System.out.println((System.currentTimeMillis()-a)/1000  + "segundos");
	}

}
