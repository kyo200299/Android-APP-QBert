package assignment3.qbert;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class QBertView extends SurfaceView {
	
	final static int x = 360;	//x coordinate of top block
	final static int y = 420;	//y coordinate of top block
	int[][] xpos = new int[7][7];
	int[][] ypos = new int[7][7];
	boolean[][] flipColor = new boolean[7][7];	
	boolean dead = false;
	boolean complete = false;
	boolean disk1 = true;
	boolean disk2 = true;
	boolean frz = false;
	int xqbert, yqbert;
	int lifeCount = 15;
	long score = 0;
	int pop, counter, ii, jj; 
	Random random = new Random();
	Paint scorePaint = new Paint();
	Paint gameover = new Paint();	
	Pyramid pmd;
	QBert qb;
	Disk dsk;
	RedBall rb;
	GreenBall gb;
	Snake sk;
	SamNSlick sns;
	Bitmap qbert=BitmapFactory.decodeResource(getResources(), R.drawable.qbert);
	Bitmap coily=BitmapFactory.decodeResource(getResources(), R.drawable.coily);
	Bitmap sam=BitmapFactory.decodeResource(getResources(), R.drawable.sam);
	Bitmap purpleball=BitmapFactory.decodeResource(getResources(), R.drawable.purpleball);
	Bitmap redball=BitmapFactory.decodeResource(getResources(), R.drawable.redball);
	Bitmap greenball=BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
	Bitmap disk=BitmapFactory.decodeResource(getResources(), R.drawable.disk);	
	Rect dst=new Rect();

	public QBertView(Context context) {
		super(context);
		pmd = new Pyramid();
		qb = new QBert();
		dsk = new Disk();
		rb = new RedBall();
		gb = new GreenBall();
		sk = new Snake();
		sns = new SamNSlick();
		getBlockXY();
	}

	public boolean onTouchEvent(MotionEvent event) {
    	int touchx;
    	int touchy;
		
		switch(event.getAction()) {
		    case MotionEvent.ACTION_DOWN:		    	
		    	synchronized(this) {		//Referred from the Tetris example, detecting movement from touched quadrant
		    		touchx = (int)event.getX();
		    		touchy = (int)event.getY();
		    		
		    		if ((touchx > (getWidth()/2)) && (touchy < (getHeight()/2))) {
		    			qb.goTR();					
		    		}
		    		if ((touchx < (getWidth()/2)) && (touchy < (getHeight()/2))) {
						qb.goTL();   			
		    		}
		    		if ((touchx < (getWidth()/2)) && (touchy > (getHeight()/2))) {
						qb.goBL();		
		    		}
		    		if ((touchx > (getWidth()/2)) && (touchy > (getHeight()/2))) {
						qb.goBR();
		    		}
		    	}		    	
		    	return true;
		    case MotionEvent.ACTION_UP:
		    	touchx = -1;
		    	touchy = -1;
		    	return true;		    	
		}
		return false;
	}	
	
	public void drawBoard(Canvas c) {	//Referred from example "Tetris"
		c.drawColor(Color.BLACK);
		pmd.draw(c, xpos, ypos, flipColor, this);
		scorePaint.setColor(Color.BLUE);		
		scorePaint.setTextSize(52);
		c.drawText("LIFE : "+ lifeCount, 40, 100, scorePaint);
		c.drawText("SCORE : "+ score, 40, 150, scorePaint);
		gameover.setColor(Color.RED);
		gameover.setTextSize(80);
		
		if (lifeCount == 0 && dead) {
			c.drawText("GAME OVER !!", 130, 300, gameover);
		} else if (complete)
			c.drawText("LEVEL COMPLETE", 30, 300, gameover);
		
		if (disk1) {
			dst.set(dsk.xdisk1 -45, dsk.ydisk1 -15, dsk.xdisk1 +25, dsk.ydisk1 +15);
			c.drawBitmap(disk, null, dst, null);
		}
		
		if (disk2) {
			dst.set(dsk.xdisk2 -25, dsk.ydisk2 -15, dsk.xdisk2 +45, dsk.ydisk2 +15);
			c.drawBitmap(disk, null, dst, null);
		}
		
		if (!dead) {
			dst.set(qb.xqbert -30, qb.yqbert -90, qb.xqbert +30, qb.yqbert -10);
			c.drawBitmap(qbert, null, dst, null);	//Draw the Q*bert
		}		
		
		if (rb.yrball < y+520 && rb.rb == false) {
			dead = rb.RBMove(xqbert, yqbert);
			if (lifeCount > 0 && dead) {
				lifeCount --;
				rb = new RedBall();
				if (lifeCount > 0)
					qb= new QBert();				
			}				
			dst.set(rb.xrball -30, rb.yrball -70, rb.xrball +30, rb.yrball -10);
			c.drawBitmap(redball, null, dst, null);
		}
		if (gb.ygball < y+520 && gb.gb == false) {
			frz = gb.GBMove(xqbert, yqbert);
			if (frz) {
				gb = new GreenBall();
				for (counter=0; counter<1000000; counter++){}
			}
			dst.set(gb.xgball - 30, gb.ygball -70, gb.xgball +30, gb.ygball -10);
			c.drawBitmap(greenball, null, dst, null);
		}
		if (sns.ysns < y+520) {
			sns.SamBSlickMove(xpos, ypos, flipColor);
			dst.set(sns.xsns - 35, sns.ysns -80, sns.xsns +35, sns.ysns -10);
			c.drawBitmap(sam, null, dst, null);
		}
		if (sk.ysnake < y+520 && sk.sk == false) {
			dead = sk.SnakeMove(xqbert, yqbert);
			if (lifeCount > 0 && dead && sk.skon) {
				sk = new Snake();
				lifeCount --;
				if (lifeCount > 0)
					qb= new QBert();				
			}
			if (!sk.skon) {
				dst.set(sk.xsnake - 30, sk.ysnake -70, sk.xsnake +30, sk.ysnake -10);
				c.drawBitmap(purpleball, null, dst, null);
			} else if (sk.skon) {
				dst.set(sk.xsnake -30, sk.ysnake -100, sk.xsnake +30, sk.ysnake -10);
				c.drawBitmap(coily, null, dst, null);
			}
		}
	}

	public void updateBoard() {
		xqbert = qb.xqbert;
		yqbert = qb.yqbert;
		pop = random.nextInt(100);
		//pop = 30;		//Test Dummy

		if (disk1 && (qb.xqbert == xpos[3][5]) && (qb.yqbert == ypos[3][5])) {
			while (disk1) {
				//disk1 = dsk.disk1Move(disk1);
				disk1 = false;
				sk = new Snake();
				score += 500;
			}
			qb = new QBert();
		} else if (disk2 && (qb.xqbert == xpos[3][6]) && (qb.yqbert == ypos[3][6])) {			
			while (disk2) {
				//disk2 = dsk.disk2Move(disk2);
				disk2 = false;
				sk = new Snake();
				score += 500;
			}
			qb = new QBert();
		} else
			dead = qb.checkLife();		
			
		if (lifeCount > 0 && dead) {
			lifeCount --;
			if (lifeCount > 0)
				qb= new QBert();			
		} else if (!dead)
			qbertFlip();
		
		for (ii=0; ii<7; ii++) {
			for (jj=0; jj<=ii; jj++) {
				if ((sns.xsns == xpos[ii][jj]) && (sns.ysns == ypos[ii][jj])) {
					flipColor[ii][jj] = false;
				}
			}
		}
		
		switch(pop) {
			case 1:
				if (rb.yrball >= y+520) {
					rb = new RedBall();
					rb.xrball = x;
					rb.yrball = y;
				}
				break;
			case 10:
				if (gb.ygball >= y+520) {
					gb = new GreenBall();
					gb.xgball = x;
					gb.ygball = y;
				}
				break;
			case 20:
				if (sns.ysns >= y+520) {
					sns = new SamNSlick();
					sns.xsns = x;
					sns.ysns = y;
				}
				break;
			case 30:
				if (sk.ysnake >= y+520) {
					sk = new Snake();
					sk.xsnake = x;
					sk.ysnake = y;
				}
				break;
		}		
	}
	
	public void getBlockXY() {
		int i, j;
		int xtemp = x;
		int ytemp = y;		

		for (i=0; i<7; i++) {	//Row of the pyramid
			xtemp = x - i*50;
			ytemp = y + i*75;
			for (j=0; j<=i; j++) {
				/*	Position of blocks	*/
				xpos[i][j]= xtemp + j*100;		//x coordinate of block[i][j]
				ypos[i][j]= ytemp;				//y coordinate of block[i][j]
			}
		}
		xpos[3][5]= xpos[3][0] -100;
		ypos[3][5]= ypos[3][0];
		xpos[3][6]= xpos[3][3] +100;
		ypos[3][6]= ypos[3][3];
	}
	
	public void qbertFlip() {
		int i, j;
		int count = 0;
				
		for (i=0; i<7; i++) {
			for (j=0; j<=i; j++) {
				if ((qb.xqbert == xpos[i][j]) && (qb.yqbert == ypos[i][j]) && !flipColor[i][j]) {
					flipColor[i][j] = true;
					if (!dead)
						score +=25;
				}
			}
		}
		for (i=0; i<7; i++) {
			for (j=0; j<=i; j++) {
				if (flipColor[i][j])
					count ++;
			}
		}
		if (count == 28)
			complete = true;			
	}
}