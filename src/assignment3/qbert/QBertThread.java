package assignment3.qbert;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class QBertThread extends Thread {
	
	QBertView qv;
	
	public QBertThread(QBertView qv){
		this.qv = qv;
	}
	
	@Override
	public void run() {
		
		boolean gamestillrunning=true;
		
		System.out.println("Pyramid Created!");
		
		while(gamestillrunning) {
			
    		qv.updateBoard();			
			
			SurfaceHolder sh=qv.getHolder();
			Canvas c=sh.lockCanvas();
			
			if (c!=null) {
				qv.drawBoard(c);
				sh.unlockCanvasAndPost(c);
			}	else try {
				Thread.sleep(100);
				} catch(Exception e) {
					System.out.println("Error Detected!");
				}
		}

	}
}
