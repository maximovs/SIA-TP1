package group;

import java.util.List;

import board.Cell;

public class AddGroup extends Group{
	private List<Cell> cells;
	
	public AddGroup(int result) {
		super(result);
	}
	
	@Override
	public boolean satisfies() {
		int r=0;
		for(Cell c:cells){
			r+=c.getNumber();
		}
		return equalsResult(r);
	}

	@Override
	public List<Integer> getPossibles() {
		// TODO Auto-generated method stub
		return null;
	}

}
