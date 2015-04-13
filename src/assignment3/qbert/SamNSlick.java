package assignment3.qbert;

import java.util.Random;

public class SamNSlick {	
	
	int xsns = 50000;
	int ysns = 50000;
	int k;
	boolean sns = false;
	Random random = new Random();
	
	public void SamBSlickMove(int[][] xpos, int[][] ypos, boolean[][] flipColor) {
		k = random.nextInt(18);

		switch(k) {
		case 1:
			xsns -= 50;
			ysns += 75;
			break;
		case 2:
			xsns += 50;
			ysns += 75;
			break;
		}
		if (ysns >= QBertView.y+520) {
			sns = true;
		}
	}
}


