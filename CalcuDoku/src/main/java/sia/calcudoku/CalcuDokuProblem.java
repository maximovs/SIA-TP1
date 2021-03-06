package sia.calcudoku;

import java.util.ArrayList;
import java.util.List;


import sia.board.Board;
import sia.gps.api.GPSProblem;
import sia.gps.api.GPSRule;
import sia.gps.api.GPSState;
import sia.parser.Parser;


public class CalcuDokuProblem implements GPSProblem {

	private Board board;
	private int limit;
	private Heuristic h;

	public CalcuDokuProblem(String levelFile,int limit, Heuristic h){
		super();
		board = Parser.parse(levelFile);
		this.limit=limit==0?board.getSize()*board.getSize():limit;
		this.h = h;
	}

	@Override
	public GPSState getInitState() {
		return new CalcuDokuState(board);
	}

	@Override
	public List<GPSRule> getRules() {
		int count = 0;
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
		Board board = st.getBoard();
		switch(h){
		case GROUPS:
			return (st.cellsLeft()*board.groupsLeft())/board.totalGroups();
		case ROWSNCOLS:
			return ((st.cellsLeft()*board.rowsAndColsLeft())/(board.getSize()*2));
		case BOTH:
		default:
			return Math.max((st.cellsLeft()*board.groupsLeft())/board.totalGroups(), ((st.cellsLeft()*board.rowsAndColsLeft())/(board.getSize()*2)));
		}
	}

}
