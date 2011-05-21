package genetic;

import java.awt.Dimension;
import java.util.ArrayList;

import model.Match;
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
	
	public Integer[] GetScores(){
		Winner winner;
		Match match;
		Integer[] scores = new Integer[11];
		for(int i=0;i<_ar_teams.size();i++){
			for(int j=i+1;j<_ar_teams.size();j++){
				match = new Match(_ar_teams.get(i),_ar_teams.get(j),null);
			}
		}
		return scores;
	}
	
	
}
