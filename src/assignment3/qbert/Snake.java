package assignment3.qbert;

import java.util.Random;

public class Snake {
	
	int xsnake = 50000;
	int ysnake = 50000;
	int i;
	boolean sk = false;
	boolean skon = false;
	Random random = new Random();
	
	public boolean SnakeMove(int xqbert, int yqbert) {
		i = random.nextInt(25);
		
		if (!skon) {
			switch(i) {
			case 1:
				xsnake -= 50;
				ysnake += 75;
			case 2:
				xsnake += 50;
				ysnake += 75;
			}
		}
		if (ysnake >= QBertView.y+445)
			skon = true;
		
		if ((i == 5 || i == 10 || i == 15) && skon) {
			if (xsnake <= xqbert)
				xsnake += 50;
			else
				xsnake -= 50;
			
			if (ysnake < yqbert)
				ysnake += 75;
			else
				ysnake -= 75;
		}
		if ((xsnake == xqbert) && (ysnake == yqbert) && skon) {
			sk = true;
		}
		return sk;		
	}
}
