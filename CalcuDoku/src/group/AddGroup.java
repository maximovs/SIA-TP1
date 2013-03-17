package group;

import java.util.LinkedList;
import java.util.List;



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
	protected void generatePossibles() {
		int[] values = new int[getBoardMax()];
		for(int i=0; i < values.length; i++)
			values[i] = i+1;
		generatePossibles(values, 0, getResult(), new LinkedList<Integer>());
	}


	@SuppressWarnings("unchecked")
	private void generatePossibles(int[] values, int index, int sum, 
			LinkedList<Integer> stack) {
		if(sum == 0 && stack.size() == getCells().size())
			possibleLists.add((List<Integer>) stack.clone());

		if(values[index] > sum || stack.size() >= getCells().size())
			return;
		for(int i = index; i < values.length; i++) {
			stack.push(values[i]);
			generatePossibles(values, i, sum - values[i], stack);
			stack.pop();
		}
	}

}
