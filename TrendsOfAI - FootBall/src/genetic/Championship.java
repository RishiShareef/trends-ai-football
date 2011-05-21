package genetic;

import java.awt.Dimension;
import java.util.ArrayList;

import model.Team;

public class Championship {
	
	private ArrayList<Integer[]> _ar_strategies;
	private ArrayList<Team> _ar_teams;
	private Dimension _fieldDimension;
	
	public Championship(ArrayList<Integer[]> strategies){
		_fieldDimension = new Dimension(1000, 600);
		_ar_strategies = strategies;
		for(int i=0;i<_ar_strategies.size();i++)
			_ar_teams.add(new Team(_fieldDimension,_ar_strategies.get(i)));
	}
	
	
}
