package calcudoku;

import java.util.ArrayList;
import java.util.List;

import board.Board;

import parser.Parser;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;

public class CalcuDokuProblem implements GPSProblem {
 
	Board board;
	int step = 0;
	
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
		List<GPSRule> rules = new ArrayList<>();
		if(step==board.getSize()*board.getSize()) return rules;
		for(int i: board.getPossibbles(step/board.getSize(), step%board.getSize())){
			rules.add(new CalcuDokuRule(i));
		}
		return rules;
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
