package calcudoku;

import java.util.List;

import board.Board;

import parser.Parser;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;

public class CalcuDokuProblem implements GPSProblem {
 
	public CalcuDokuProblem(String levelFile){
		super();
		Parser parser = new Parser();
		Board board = parser.parse(levelFile);
		
		
	}
	
	@Override
	public GPSState getInitState() {
		return null;
	}

	@Override
	public GPSState getGoalState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GPSRule> getRules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getHValue(GPSState state) {
		// TODO Auto-generated method stub
		return null;
	}

}
