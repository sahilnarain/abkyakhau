package com.icecub3.recofood;

import java.text.SimpleDateFormat;

import com.icecub3.recofood.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItem extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_dish);
		
		final Button addToList = (Button) findViewById(R.id.bAddToList);
		final EditText etDishName = (EditText) findViewById(R.id.etFoodName);
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		final DBHelper dbh = new DBHelper(this);

		addToList.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (etDishName.getText().toString().trim() != "") {
					Items item = new Items();
					item.setItem(etDishName.getText().toString());
					//dish.setLastDate("Never");
					dbh.addItem(item);

					Toast t;
					t = Toast.makeText(getApplicationContext(),
							"Dish added to list", Toast.LENGTH_SHORT);
					t.show();
				}
				etDishName.setText("");
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_dish, menu);
		return true;
	}

}
