package assignment3.qbert;

public class Disk {	 

	int xdisk1 = 110;	int ydisk1 = 645;
	int xdisk2 = 610;	int ydisk2 = 645;
		
	public boolean disk1Move(boolean disk1) {
		
		/*		Move left disk		*/
		xdisk1 += 0.3;
		ydisk1 = (int)(-1.5*xdisk1 + 810);
		if ((xdisk1 == QBertView.x) || (ydisk1 == QBertView.y))
			disk1 = false;
		
		return disk1;
	}

	public boolean disk2Move(boolean disk2) {
		
		/*		Move right disk		*/
		xdisk2 += 0.3;
		ydisk2 = (int)(1.5*xdisk2 - 270);
		if ((xdisk2 == QBertView.x) || (ydisk2 == QBertView.y))
			disk2 = false;

		return disk2;
	}

}
