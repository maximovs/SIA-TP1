package calcudoku;

import board.Board;
import gps.api.GPSProblem;
import gps.api.GPSState;

public class CalcuDokuState implements GPSState {

	Board board;
	private int[][] values;
	int step = 0;

	public CalcuDokuState(Board board) {
		this.board = board;
		values = new int[board.getSize()][board.getSize()];
	}

	public CalcuDokuState(CalcuDokuState state, int value) {
		setValues(state.values, state.values.length);
		this.board = state.board;
		this.step = state.step;
		values[step/values.length][step%values.length] = value;
		step++;
	}

	@Override
	public boolean compare(GPSState state) {
		CalcuDokuState st = (CalcuDokuState)state;


		return step == st.getStep() && board.equals(st.board);

	}

	@Override
	public boolean isFinished() {
		return step == board.getSize()*board.getSize() && board.isFinished();
	}

	public int[][] getValues(){
		return values;
	}
	
	private void setValues(int[][] v, int size){
		values = new int[size][size];
		for(int i=0; i<values.length; i++)
			for(int j=0; j<values.length; j++)
				values[i][j] = v[i][j];
		
	}


	public int getStep(){
		return step;
	}

	public void updateProblem(GPSProblem problem){
		for(int i=0; i<values.length; i++)
			for(int j=0; j<values.length; j++)
				board.setCellValue(i, j, values[i][j]);
		((CalcuDokuProblem)problem).setStep(step);
	}
	public Board getBoard(){
		return board;
	}

	@Override
	public String toString() {
		String s = "Step: " + step + "values:";
		for(int i=0; i<values.length; i++){
			s+="\n";
			for(int j=0; j<values.length; j++)
				s+=values[i][j] + " ";
		}
		return s;
	}

}
