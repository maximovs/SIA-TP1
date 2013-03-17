package calcudoku;

import board.Board;
import gps.api.GPSState;

public class CalcuDokuState implements GPSState {
	
	Board board;

	public CalcuDokuState(Board b) {
		this.board = b;
	}

	@Override
	public boolean compare(GPSState state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFinished() {
		return board.isFinished();
	}

}
