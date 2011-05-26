package genetic.teamGenerator;

import java.util.Random;

public class TG_Selfish implements TeamGenerator {

	@Override
	public Integer[][] createTeam() {
		Integer[][] strategy = new Integer[11][3];
		Random random = new Random();
		int randomPass;
		do {
			randomPass = random.nextInt(12)-1;
		} while(randomPass == 0);
		strategy[0][0] = randomPass;
		strategy[0][1] = 0;
		strategy[0][2] = 300;
		for (int i = 1; i < 11; i++) {
			strategy[i][0] = -1;
			strategy[i][1] = random.nextInt(1000);
			strategy[i][2] = random.nextInt(600);
		}
		return strategy;
	}

	@Override
	public Integer[][] createBestSupposedTeam() {
		return createTeam();
	}

}
