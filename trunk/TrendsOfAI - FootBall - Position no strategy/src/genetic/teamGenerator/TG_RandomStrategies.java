package genetic.teamGenerator;

import java.util.Random;

public class TG_RandomStrategies implements TeamGenerator {

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
			int myStrategy;
			do {
				myStrategy = random.nextInt(12) - 1;
			} while(myStrategy == i);
			strategy[i][0] = myStrategy;
			strategy[i][1] = random.nextInt(1000);
			strategy[i][2] = random.nextInt(600);
		}
		if (containCycle(strategy))
			return createTeam();
		return strategy;
	}
	
	private static boolean containCycle(Integer[][] strategy) {
		/*
		 * Return true if strategy contains minimum 1 cycle
		 */
		int currentPlayer;
		for (int i = 0; i < 11; i++) {
			currentPlayer = i;
			nocycle: for (int j = 0; j < 12; j++) {
				if (strategy[currentPlayer][0] == -1)
					break nocycle;
				else
					currentPlayer = strategy[currentPlayer][0];
				if (j == 12)
					return true;
			}
		}
		return false;
	}
}
