package com.icecub3.recofood;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.icecub3.recofood.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Suggestions extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_suggestions);
		

		final Button add = (Button) findViewById(R.id.bAdd);
		final Button no = (Button) findViewById(R.id.bNO);
		final Button yes = (Button) findViewById(R.id.bYES);
		final Button viewAll = (Button) findViewById(R.id.bView);
		final TextView suggestion = (TextView) findViewById(R.id.tvSuggestion);
		//final TextView lastDate = (TextView) findViewById(R.id.tvLastCookedOn);
		//final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		DBHelper dbh = new DBHelper(this);
		dbh.clearSessionDisp();
		
		// if (dbh.getMaxId() == null) {
		//
		// dbh.addDish(new Dishes(1, "Nothing"));
		//
		// }

		// if (dbh.getMaxId() != null) {
		// int id = dbh.getRandomId();
		//
		// suggestion.setText(dbh.getDish(id).getDishName().toString());
		// lastDate.setText(dbh.getDish(id).getLastDate().toString());
		// }

		//showSuggestion(dbh, suggestion, lastDate);

		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("android.intent.action.ADDDISH");
				startActivity(intent);
			}
		});

		viewAll.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("android.intent.action.VIEWALL");
				startActivity(intent);
			}
		});

		no.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DBHelper dbh = new DBHelper(getBaseContext());
				
				try {
					Items item = new Items();
					item = dbh
							.getItemObjByName(suggestion.getText().toString());
					dbh.updateItemProb(item, false);
					
					Toast t;
					t = Toast.makeText(getApplicationContext(),
							"Updated Paramters", Toast.LENGTH_SHORT);
					t.show();
				} catch(Exception e) {
				
				}
					
				// if (dbh.getMaxId() != null) {
				// int id = dbh.getRandomId();
				//
				// suggestion
				// .setText(dbh.getDish(id).getDishName().toString());
				// lastDate.setText(dbh.getDish(id).getLastDate().toString());
				// }

				//showSuggestion(dbh, suggestion, lastDate);
				suggestion.setText(dbh.getSuggestedItem().getItem().toString());
			}
		});

		yes.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final TextView suggestion = (TextView) findViewById(R.id.tvSuggestion);
				//final TextView lastDate = (TextView) findViewById(R.id.tvLastCookedOn);

				DBHelper dbh = new DBHelper(getBaseContext());

				try {
					Items item = new Items();
					item = dbh
							.getItemObjByName(suggestion.getText().toString());

					Date now = new Date();

					//dish.setLastDate(sdf.format(now));

					dbh.updateItemName(item);
					dbh.updateItemProb(item, true);
					//lastDate.setText(dish.getLastDate());
					
					Toast t;
					t = Toast.makeText(getApplicationContext(),
							"Updated Parameters", Toast.LENGTH_SHORT);
					t.show();

				} catch (Exception e) {
					/*if(dbh.getMaxId() != null) {
						Toast t;
						t = Toast.makeText(getApplicationContext(),"Error!", Toast.LENGTH_SHORT);
						t.show();
					}*/
				}
				
			}
		});
	}

//	private void showSuggestion(DBHelper dbh, TextView suggestion //,
//			//TextView lastDate
//			) {
//		if (dbh.getMaxId() != null) {
//			int id = dbh.getRandomId();
//
//			suggestion.setText(dbh.getDish(id).getDishName().toString());
//			//lastDate.setText(dbh.getDish(id).getLastDate().toString());
//		} else {
//			suggestion.setText("");
//			lastDate.setText("");
//		}
//	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		final TextView suggestion = (TextView) findViewById(R.id.tvSuggestion);
		//final TextView lastDate = (TextView) findViewById(R.id.tvLastCookedOn);

		DBHelper dbh = new DBHelper(this);
		suggestion.setText(dbh.getSuggestedItem().getItem().toString());
		//showSuggestion(dbh, suggestion, lastDate);
	}

}
