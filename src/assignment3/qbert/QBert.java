package assignment3.qbert;

public class QBert {
	
	int xqbert = QBertView.x;
	int yqbert = QBertView.y;
	boolean dead;

	public void goTR() {
		xqbert += 50;
		yqbert -= 75;
	}
	
	public void goTL() {
		xqbert -= 50;
		yqbert -= 75;
	}

	public void goBL() {
		xqbert -= 50;
		yqbert += 75;
	}
	
	public void goBR() {
		xqbert += 50;
		yqbert += 75;
	}
	
	public boolean checkLife() {		
			
		if ((yqbert < (float)(-1.5*xqbert + 820)) ||
				(yqbert < (float)(1.5*xqbert - 260)) ||
				(yqbert > (QBertView.y+520)))
			dead = true;
		return dead;
	}
}
