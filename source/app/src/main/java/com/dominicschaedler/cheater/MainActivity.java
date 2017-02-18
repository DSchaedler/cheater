package com.dominicschaedler.cheater;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import java.util.*;
import java.text.*;


public class MainActivity extends Activity {

	private LinearLayout linear4;
	private TextView howbadtextview;
	private Button badchoicebutton;
	private Button overatebutton;
	private Button sugarysnackbutton;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private TextView badchoicelabel;
	private TextView badchoicesoutput;
	private TextView overatelabel;
	private TextView overateoutput;
	private TextView sugarysnacklabel;
	private TextView sugarysnackoutput;

	private int badChoiceCount = 0;
	private int overeateCount = 0;
	private int sugarySnackCount = 0;


	private SharedPreferences dataStore;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initialize();
		initializeLogic();
	}

	private void  initialize() {
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		howbadtextview = (TextView) findViewById(R.id.howbadtextview);
		badchoicebutton = (Button) findViewById(R.id.badchoicebutton);
		overatebutton = (Button) findViewById(R.id.overatebutton);
		sugarysnackbutton = (Button) findViewById(R.id.sugarysnackbutton);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		badchoicelabel = (TextView) findViewById(R.id.badchoicelabel);
		badchoicesoutput = (TextView) findViewById(R.id.badchoicesoutput);
		overatelabel = (TextView) findViewById(R.id.overatelabel);
		overateoutput = (TextView) findViewById(R.id.overateoutput);
		sugarysnacklabel = (TextView) findViewById(R.id.sugarysnacklabel);
		sugarysnackoutput = (TextView) findViewById(R.id.sugarysnackoutput);

		dataStore = getSharedPreferences("dataStore", Activity.MODE_PRIVATE);

		badchoicebutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				badChoiceCount++;
				dataStore.edit().putString("0", String.valueOf(badChoiceCount)).commit();
				badchoicesoutput.setText(String.valueOf(badChoiceCount));
			}
		});
		overatebutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				overeateCount++;
				dataStore.edit().putString("1", String.valueOf(overeateCount)).commit();
				overateoutput.setText(String.valueOf(overeateCount));
			}
		});
		sugarysnackbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				sugarySnackCount++;
				dataStore.edit().putString("2", String.valueOf(sugarySnackCount)).commit();
				sugarysnackoutput.setText(String.valueOf(sugarySnackCount));
			}
		});

	}

	private void  initializeLogic() {
		if (dataStore.getString("0", "").equals("")) {
			dataStore.edit().putString("0", "0").commit();
		}
		if (dataStore.getString("1", "").equals("")) {
			dataStore.edit().putString("1", "0").commit();
		}
		if (dataStore.getString("2", "").equals("")) {
			dataStore.edit().putString("2", "0").commit();
		}
		badChoiceCount = Integer.valueOf(dataStore.getString("0", ""));
		overeateCount = Integer.valueOf(dataStore.getString("1", ""));
		sugarySnackCount = Integer.valueOf(dataStore.getString("2", ""));
		badchoicesoutput.setText(String.valueOf(badChoiceCount));
		overateoutput.setText(String.valueOf(overeateCount));
		sugarysnackoutput.setText(String.valueOf(sugarySnackCount));
	}



	// created automatically
	private void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}

	private int getRandom(int _minValue ,int _maxValue){
		Random random = new Random();
		return random.nextInt(_maxValue - _minValue + 1) + _minValue;
	}

	public ArrayList<Integer> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Integer> _result = new ArrayList<Integer>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
				_result.add(_arr.keyAt(_iIdx));
		}
		return _result;
	}

}
