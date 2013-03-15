package group;

import java.util.List;

import board.Cell;

public class MultiplyGroup extends Group{
	private List<Cell> cells;
	
	public MultiplyGroup(int result, int n) {
		super(result, n);
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
	public List<Integer> getPossibles() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void generatePossibles() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCell(Cell c) {
		c.setGroup(this);
	}
	

}
