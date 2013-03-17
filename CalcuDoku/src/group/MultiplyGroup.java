package group;

import java.util.List;

import board.Cell;

public class MultiplyGroup extends Group{
	private List<Cell> cells;
	
	public MultiplyGroup(int result, int n, int s) {
		super(result, n, s);
	}
	
	@Override
	public boolean satisfies() {
		int r=1;
		for(Cell c:cells){
			r*=c.getNumber();
		}
		return equalsResult(r);
	}


	@Override
	protected void generatePossibles() {
		// TODO Auto-generated method stub
		
	}


	

}
