package sia.group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class DivideGroup extends Group{

	public DivideGroup(int result, int n) {
		super(result, n, 2);
	}

	@Override
	public boolean satisfies() {
		List<Integer> l = getCellNumbers();
		/* Deben ser dos números*/
		if(l.size()!=2) return false;
		Collections.sort(l);
		/* Deben dar un entero*/
		if(l.get(1)%l.get(0)!=0) return false;
		return equalsResult(l.get(1)/l.get(0));
	}


	@Override
	public void generatePossibles() {
		possibleLists = new ArrayList<>();
		ArrayList<Integer> aux;
		int r = getResult();
		for(int i = getBoardMax(); i>0 ; i--){
			if(i%r==0){
				aux = new ArrayList<>();
				aux.add(i);
				aux.add(i/r);
				possibleLists.add(aux);
			}
		}
	}

	@Override
	protected String Operation() {
		return "/";
	}
}
