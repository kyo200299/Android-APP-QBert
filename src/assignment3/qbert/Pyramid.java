package assignment3.qbert;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Pyramid {
	
	Path Tpath = new Path();
	Path Rpath = new Path();
	Path Lpath = new Path();
	Path flipBlock = new Path();;
	
	Paint Tpaint = new Paint();
	Paint Rpaint = new Paint();
	Paint Lpaint = new Paint();
	Paint qbFlip = new Paint();
	
	public void draw(Canvas c, int[][] xpos, int[][] ypos, boolean[][] flipColor, QBertView qv) {
		int i, j;
		
		Rpaint.setColor(Color.rgb(255, 165, 0));	//Right initial color
		Lpaint.setColor(Color.rgb(130, 120, 50));	//Left initial color
		Tpaint.setColor(Color.rgb(255, 255, 0));	//Top initial color
		qbFlip.setColor(Color.rgb(0, 34, 255));		//Success when Q*Bert steps on		
		
		if (Tpath.isEmpty() || Tpath.isEmpty() || Tpath.isEmpty()) {
			for (i=0; i<7; i++) {	//Row of the pyramid
				for (j=0; j<=i; j++) {	//Column of the pyramid
					
					/*		Top Surface		*/
					Tpath.moveTo(xpos[i][j], ypos[i][j]);
					Tpath.rLineTo(50, -25);
					Tpath.rLineTo(-50, -25);
					Tpath.rLineTo(-50, 25);
					Tpath.close();
					
					
					/*		Right Surface		*/
					Rpath.moveTo(xpos[i][j], ypos[i][j]);
					Rpath.rLineTo(50, -25);
					Rpath.rLineTo(0, 50);
					Rpath.rLineTo(-50, 25);
					Rpath.close();
					
					
					/*		Left Surface		*/
					Lpath.moveTo(xpos[i][j], ypos[i][j]);
					Lpath.rLineTo(-50, -25);
					Lpath.rLineTo(0, 50);
					Lpath.rLineTo(50, 25);
					Lpath.close();			
				}
			}
		}
		c.drawPath(Tpath, Tpaint);
		c.drawPath(Rpath, Rpaint);
		c.drawPath(Lpath, Lpaint);
		
		flipBlock = new Path();
		for (i=0; i<7; i++) {	//Row of the pyramid
			for (j=0; j<=i; j++) {	//Column of the pyramid
				if (flipColor[i][j]) {
					flipBlock.moveTo(xpos[i][j], ypos[i][j]);
					flipBlock.rLineTo(50, -25);
					flipBlock.rLineTo(-50, -25);
					flipBlock.rLineTo(-50, 25);
					flipBlock.close();
				}					
			}
		}
		c.drawPath(flipBlock, qbFlip);
	}
}
