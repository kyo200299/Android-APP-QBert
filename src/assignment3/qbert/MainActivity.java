package assignment3.qbert;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		QBertView qv= new QBertView(getBaseContext());
		setContentView(qv);
		QBertThread qt= new QBertThread(qv);
		qt.start();
		System.out.println("Q*Bert Thread Starts!");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
