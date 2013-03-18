package calcudoku;

import gps.BFSGPSEngine;
import gps.DFSGPSEngine;
import gps.GPSEngine;
import parser.Parser;
import board.Board;


public class CalcuDoku {
	
	public static void main(String args[]){
		CalcuDokuProblem problem = new CalcuDokuProblem("levels/QuadOp6x6");
		GPSEngine engine = new DFSGPSEngine();
		engine.engine(problem, null);
	}

}
