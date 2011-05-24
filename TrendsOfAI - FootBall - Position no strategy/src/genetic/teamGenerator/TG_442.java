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

}
