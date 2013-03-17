package calcudoku;

import java.util.List;

import board.Board;

import parser.Parser;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;

public class CalcuDokuProblem implements GPSProblem {
 
	Board board;
	int step;
	
	public CalcuDokuProblem(String levelFile){
		super();
		board = Parser.parse(levelFile);
	}
	
	@Override
	public GPSState getInitState() {
		return new CalcuDokuState(board);
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

	public void setStep(int step) {
		this.step = step;
	}

}
