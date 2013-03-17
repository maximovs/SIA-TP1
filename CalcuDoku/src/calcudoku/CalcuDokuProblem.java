package calcudoku;

import java.util.List;

import board.Board;

import parser.Parser;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;

public class CalcuDokuProblem implements GPSProblem {
 
	String levelFile;
	
	public CalcuDokuProblem(String levelFile){
		super();
		this.levelFile = levelFile;
	}
	
	@Override
	public GPSState getInitState() {
		Board b = Parser.parse(levelFile);
		if(b == null){
			return null;
		}
		return new CalcuDokuState(b);
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
