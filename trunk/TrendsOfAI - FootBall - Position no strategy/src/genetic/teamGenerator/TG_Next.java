package genetic.teamGenerator;

import java.util.Random;

public class TG_Next implements TeamGenerator {

	@Override
	public Integer[][] createTeam() {
		/*
		 * Passes to the next player, last player shoots
		 */
		Integer[][] strategy = new Integer[11][3];
		Random random = new Random();
		strategy[0][1] = 0;
		strategy[0][2] = 300;
		for (int i = 1; i < 11; i++) {
			strategy[i][1] = random.nextInt(1000);
			strategy[i][2] = random.nextInt(600);
		}
		
		for(int i = 0; i<10; i++)
			strategy[i][0] = i+1;
		strategy[10][0] = -1;
		
		return strategy;
	}

}
