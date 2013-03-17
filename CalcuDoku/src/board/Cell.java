package board;

import group.Group;

public class Cell {
	private int number = 0;
	private Group group;
	public Cell() {
		
	}
	public void setGroup(Group g){
		group = g;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + number;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (number != other.number)
			return false;
		return true;
	}
	public Group getGroup(){
		return group;
	}
	
	public void setNumber(int number){
		this.number = number;
	}
	public int getNumber(){
		return number;
	}
}
