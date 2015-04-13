package assignment3.qbert;

import java.util.Random;

public class RedBall {
	
	int xrball = 50000;
	int yrball = 50000;
	int i;
	boolean rb = false;
	Random random = new Random();
	
	public boolean RBMove(int xqbert, int yqbert) {		
		i = random.nextInt(15);

		switch(i) {
		case 1:
			xrball -= 50;
			yrball += 75;
		case 2:
			xrball += 50;
			yrball += 75;				
		}
		
		if ((xrball == xqbert) && (yrball == yqbert)) {
			rb = true;
		}
		return rb;
	}
}
