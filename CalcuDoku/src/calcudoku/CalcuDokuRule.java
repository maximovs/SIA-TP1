package calcudoku;

import java.util.ArrayList;

import gps.api.GPSRule;
import gps.api.GPSState;
import gps.exception.NotAppliableException;

public class CalcuDokuRule implements GPSRule {

	int value;
	
	@Override
	public Integer getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
		return new CalcuDokuState((CalcuDokuState)state, value);
		
	}

}
