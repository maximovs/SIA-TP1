package calcudoku;


import gps.api.GPSRule;
import gps.api.GPSState;
import gps.exception.NotAppliableException;

public class CalcuDokuRule implements GPSRule {

	int value;
	
	public CalcuDokuRule(int value) {
		this.value = value;
	}
	
	@Override
	public Integer getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		return "value: " + value;
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
		return new CalcuDokuState((CalcuDokuState)state, value);
		
	}

}
