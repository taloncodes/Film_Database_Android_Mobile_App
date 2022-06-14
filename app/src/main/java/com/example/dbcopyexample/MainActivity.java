package com.example.dbcopyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Output;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends Activity

{

    OpenDatabase sqh;
    SQLiteDatabase sqdb;

    //widget controls
    EditText searchByYearEditText;
    Button searchByYearButton;
    TextView numberRecordsLabel;
    TextView numberRecordsDisplay;
    Button displayAllRecordsButton;
    TextView resultsTextView;

    //used for list view
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatabase(); //open the music.db for reading and writing

        setupControls(); //setup controls after opening the database for r/w

        //  displayRecords();

        //sqh.displayRecords(sqdb);

    }   //  protected void onCreate(Bundle savedInstanceState)

    private void setupControls()
    {
        searchByYearEditText = findViewById(R.id.searchByYearEditText);

        searchByYearButton = findViewById(R.id.searchByYearButton);
        searchByYearButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resultsTextView.setText(sqh.searchYearInSongTable(sqdb, searchByYearEditText.getText().toString()));
                numberRecordsDisplay.setText(Integer.toString(sqh.dynamicNumberOfRecordsInSongTable(sqdb, searchByYearEditText.getText().toString())));

            }
        });

        numberRecordsLabel = findViewById(R.id.numberRecordsLabel);

        numberRecordsDisplay = findViewById(R.id.numberRecordsDisplay);

        displayAllRecordsButton = findViewById(R.id.displayAllRecordsButton);

        displayAllRecordsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resultsTextView = findViewById(R.id.resultsTextView);
                //resultsTextView.setText(sqh.allRecordsInSongTable(sqdb));

                listView = findViewById(R.id.listView);

                list = new ArrayList<String>();
                list.addAll(sqh.allRecordsReturnArrayList(sqdb));

                //Construct an ArrayAdapter object
                adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                //link listView to the adapter
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        //Get the chosen item store it in a string
                        String item = (String) parent.getItemAtPosition(position);

                        //Use the toast app to display the chosen item.
                        Toast.makeText(getApplicationContext(), "Item = " + item, Toast.LENGTH_SHORT).show();

                    }
                });


                //show number
                numberRecordsDisplay.setText(Integer.toString(sqh.totalNumberOfRecordsInSongTable(sqdb)));

            }
        });

        //resultsTextView = findViewById(R.id.resultsTextView);



    }

    public  void initDatabase()
    {
        //init the SQLite Helper Class
        sqh = new OpenDatabase(this);

        //retrieve a readable and writeable database
        sqdb = sqh.getWritableDatabase();
    }



}   //  public class MainActivity extends AppCompatActivity