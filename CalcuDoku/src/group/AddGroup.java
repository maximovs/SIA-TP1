package group;

import java.util.List;

import board.Cell;

public class AddGroup extends Group{
	
	public AddGroup(int result, int n) {
		super(result, n);
	}
	
	@Override
	public boolean satisfies() {
		int r=0;
		for(Integer i:getCellNumbers()){
			r+=i;
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
