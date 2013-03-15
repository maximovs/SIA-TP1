package group;

import java.util.List;

public abstract class Group {
	private int result;
	public Group(int result) {
		this.result= result;
	}
	public boolean equalsResult(int r){
		return result==r;
	}
	public abstract boolean satisfies();
	public abstract List<Integer> getPossibles();
}
