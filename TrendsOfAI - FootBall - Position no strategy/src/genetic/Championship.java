package genetic;

import java.awt.Dimension;
import java.util.ArrayList;

import model.Match;
import model.Team;

public class Championship {
	
	private ArrayList<Integer[][]> _ar_strategies;
	private ArrayList<Team> _ar_teams;
	private Dimension _fieldDimension;
	
	public Championship(ArrayList<Integer[][]> strategies){
		_ar_teams = new ArrayList<Team>();
		_fieldDimension = new Dimension(1000, 600);
		_ar_strategies = strategies;
		for(int i=0;i<_ar_strategies.size();i++)
			_ar_teams.add(new Team(_fieldDimension,_ar_strategies.get(i)));
	}
	
	public Integer[] getScores(){
		Match match;
		int score;
		Integer[] scores = new Integer[_ar_teams.size()+1];
		for(int i=0;i<scores.length;i++)
			scores[i]=0;
		for(int k=0;k<5;k++){
			for(int i=0;i<_ar_teams.size();i++){
				for(int j=i+1;j<_ar_teams.size();j++){
					match = new Match(_ar_teams.get(i),_ar_teams.get(j),null);
					score = match.run();
					if(score == 0){
						scores[i]+=1;
						scores[j]+=1;
					}else if(score > 0)
						scores[i]+=3;
					else
						scores[j]+=3;
				}
			}
		}
		return scores;
	}
	
	public Integer[] getGoalDifferences(){
		int score;
		Winner winner;
		Match match;
		Integer[] scores = new Integer[_ar_teams.size()+1];
		for(int i=0;i<scores.length;i++)
			scores[i]=0;
		for(int i=0;i<_ar_teams.size();i++){
			for(int j=i+1;j<_ar_teams.size();j++){
				match = new Match(_ar_teams.get(i),_ar_teams.get(j),null);
				score = match.run();
				scores[i]+=score;
				scores[j]-=score;
			}
		}
		
		for(int i=0;i<_ar_teams.size();i++)
			System.out.println("Team : " + i + ": " + scores[i]);
		
		return scores;
	}
	
	
}
