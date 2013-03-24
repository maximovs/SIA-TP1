package sia.calcudoku;


import sia.board.Board;
import sia.gps.api.GPSRule;
import sia.gps.api.GPSState;
import sia.gps.exception.NotAppliableException;

public class CalcuDokuRule implements GPSRule {

	int value;
	int position;
	public CalcuDokuRule(int value, int position) {
		this.value = value;
		this.position = position;
	}
	
	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		return "value: " + value;
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
		Board board = ((CalcuDokuState)state).getBoard();
		if(!(board.getPossibbles(position/board.getSize(), position%board.getSize()).contains(value)))
			throw new NotAppliableException();
		return new CalcuDokuState((CalcuDokuState)state, value,position);
	}
	
	@Override
	public String toString(){
		return "Value: " + value + "  at position: " + position;
	}

}
