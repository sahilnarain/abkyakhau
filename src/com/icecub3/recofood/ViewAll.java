package com.icecub3.recofood;

import java.util.ArrayList;
import java.util.List;

import com.icecub3.recofood.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewAll extends Activity {

	// ArrayAdapter adapter;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_all);
		
		final ListView viewAll = (ListView) findViewById(R.id.lvAlLDishes);
		
		generateListView(viewAll);
		Toast hint;
		hint = Toast.makeText(getApplicationContext(), "Long press to delete",
				Toast.LENGTH_SHORT);
		hint.show();

		viewAll.setOnItemLongClickListener(new OnItemLongClickListener() {
			DBHelper dbh = new DBHelper(getBaseContext());

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				String dishName = (String) viewAll.getItemAtPosition(position);
				removeItemFromList(dishName);
				return true;
			}

			public void removeItemFromList(final String itemName) {

				AlertDialog.Builder alert = new AlertDialog.Builder(
						ViewAll.this);
				alert.setTitle("Delete");
				alert.setMessage("Do you want to delete this item?");
				alert.setPositiveButton("OK", new OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						Items item = new Items();
						item = dbh.getItemObjByName(itemName);
						dbh.deleteItem(item);

						
						generateListView(viewAll);
						
						// adapter.notifyDataSetInvalidated();
						//adapter.notifyDataSetChanged();						
					}
				});

				alert.setNegativeButton("Cancel", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});

				alert.show();
			}

		});
	}
	
	private void generateListView(ListView viewAll) {
		// registerForContextMenu(viewAll);

		DBHelper dbh = new DBHelper(this);

		List<String> itemNames = new ArrayList<String>();
		itemNames = dbh.getAllItemNames();
		ArrayAdapter adapter = new ArrayAdapter(this,
		R.layout.listview_layout, itemNames);
		//adapter.setNotifyOnChange(true);

		viewAll.setAdapter(adapter);

	}

}
