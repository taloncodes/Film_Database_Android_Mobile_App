package com.example.dbcopyexample;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ModifyRecord extends Activity
{
    OpenDatabase sqh;
    SQLiteDatabase sqdb;

    ListView modifyListView;
    Button backModifyButton;

    ArrayList<String> list;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_record);

        initDatabase();

        setupControls();

    }

    @Override
    protected void onRestart()
    {
        super.onRestart();

        setupListView();
    }   //protected void onRestart()

    protected void setupControls ()
    {

        backModifyButton = findViewById(R.id.backModifyButton);
        backModifyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        setupListView();

    }

    public void setupListView()
    {
        modifyListView = findViewById(R.id.modifyListView);
        list = new ArrayList<String>();
        list.addAll(sqh.allRecordsReturnArrayList(sqdb));

        //Construct an ArrayAdapter object
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        //link listView to the adapter
        modifyListView.setAdapter(adapter);

        modifyListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                //Get the chosen item store it in a string (to pass over to the new activity)
                String item = (String) parent.getItemAtPosition(position);

                //Use the toast app to display the chosen item.
                //Toast.makeText(getApplicationContext(), "Item = " + item, Toast.LENGTH_SHORT).show();

                //onItemClick open modifyRecordActivity
                Intent intent = new Intent(getBaseContext(), ModifySelectedRecordActivity.class);
                intent.putExtra("record", item); //passing the value from one activity to another
                startActivity(intent);

            }
        });

    }

    public  void initDatabase() //must open database
    {
        //init the SQLite Helper Class
        sqh = new OpenDatabase(this);

        //retrieve a readable and writeable database
        sqdb = sqh.getWritableDatabase();
    }


}