package assignment3.qbert;

import java.util.Random;

public class GreenBall {
	
	int xgball = 50000;
	int ygball = 50000;
	int i;
	boolean gb = false;
	Random random = new Random();

	public boolean GBMove(int xqbert, int yqbert) {	
		i = random.nextInt(25);
		
		switch(i) {
		case 1:
			xgball -= 50;
			ygball += 75;
		case 2:
			xgball += 50;
			ygball += 75;
		}
		if ((xgball == xqbert) && (ygball == yqbert)) {
			gb = true;
		}
		return gb;
	}
}