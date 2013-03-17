package group;



public class AddGroup extends Group{
	
	public AddGroup(int result, int n, int s) {
		super(result, n, s);
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
	protected void generatePossibles() {
		// TODO Auto-generated method stub
		
	}


	

}
