package sia.calcudoku;

import java.util.ArrayList;
import java.util.List;


import sia.board.Board;
import sia.gps.api.GPSProblem;
import sia.gps.api.GPSRule;
import sia.gps.api.GPSState;
import sia.parser.Parser;


public class CalcuDokuProblem implements GPSProblem {

	Board board;

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
		int count = 0;
		int limit = 1;
		List<GPSRule> rules = new ArrayList<>();
		for(int position=0; count<limit && position<board.getSize()*board.getSize();position++)
			if(board.getCell(position/board.getSize(), position%board.getSize()).getNumber()==0){
				count++;
				for(int i = 1; i < board.getSize()+1 ; i++){
					rules.add(new CalcuDokuRule(i,position));
				}
			}
		return rules;
	}

	@Override
	public Integer getHValue(GPSState state) {
		CalcuDokuState st = (CalcuDokuState) state;
		st.updateProblem(this);
		return st.getBoard().groupsLeft();
	}

}
