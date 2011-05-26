package genetic.teamGenerator;

import java.util.Random;

public class TG_442 implements TeamGenerator {

	@Override
	public Integer[][] createTeam() {
		Integer[][] strategy = new Integer[11][3];
		Random random = new Random();
		strategy[0][1] = 0;
		strategy[0][2] = 300;
		for (int i = 1; i < 11; i++) {
			strategy[i][1] = random.nextInt(1000);
			strategy[i][2] = random.nextInt(600);
		}
		
		strategy[0][0] = 1;
		strategy[1][0] = 5;
		strategy[2][0] = 6;
		strategy[3][0] = 7;
		strategy[4][0] = 8;
		strategy[5][0] = 9;
		strategy[6][0] = 9;
		strategy[7][0] = 10;
		strategy[8][0] = 10;
		strategy[9][0] = -1;
		strategy[10][0] = -1;
		
		return strategy;
	}

	@Override
	public Integer[][] createBestSupposedTeam() {
		Integer [][] ar_team = new Integer [11][3];
		
		ar_team = createTeam();
		ar_team[0][1] = 0;
		ar_team[0][2] = 0;
		
		ar_team[1][1] = 200;
		ar_team[1][2] = 130;

		ar_team[2][1] = 200;
		ar_team[2][2] = 250;
		
		ar_team[3][1] = 200;
		ar_team[3][2] = 350;
		
		ar_team[4][1] = 200;
		ar_team[4][2] = 470;
		
		ar_team[5][1] = 500;
		ar_team[5][2] = 130;
		
		ar_team[6][1] = 500;
		ar_team[6][2] = 250;
		
		ar_team[7][1] = 500;
		ar_team[7][2] = 350;
		
		ar_team[8][1] = 500;
		ar_team[8][2] = 470;
		
		ar_team[9][1] = 780;
		ar_team[9][2] = 200;
		
		ar_team[10][1] = 780;
		ar_team[10][2] = 400;
		
		return ar_team;
	}

}
