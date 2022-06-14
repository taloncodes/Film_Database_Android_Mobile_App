package com.example.dbcopyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DeleteActivity extends Activity
{
    SQLiteDatabase sqdb;
    OpenDatabase sqh;

    Button backDeleteRecordButton;
    ListView deleteRecordListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        initDataBase();

        setupControls();
    }

    protected void setupControls()
    {

        backDeleteRecordButton = findViewById(R.id.backDeleteRecordButton);
        backDeleteRecordButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        setupListView();

    }

    protected void setupListView()
    {

        deleteRecordListView = findViewById(R.id.deleteRecordListView);

        ArrayList<String> list = new ArrayList<String>();
        list.addAll(sqh.allRecordsReturnArrayList(sqdb));

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        deleteRecordListView.setAdapter(adapter);
        deleteRecordListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String item = (String) parent.getItemAtPosition(position);

                //Split the item string into substring so we can get the Id.

                //String[] subString = item.split(",");

                //sqh.deleteRecordFromSongTable(sqdb, subString[0]);

                deleteRecordDialog(item);

                Log.i("Record", "Record = " + item);

                //refreshListView();


            }
        });

    }

    protected void refreshListView()
    {
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(sqh.allRecordsReturnArrayList(sqdb));

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        deleteRecordListView.setAdapter(adapter);
    }

    protected void initDataBase()
    {
        sqh = new OpenDatabase(this);

        sqdb = sqh.getWritableDatabase();

    }

    protected void deleteRecordDialog (String selectedRecord)
    {
        //Split the item string into substring so we can get id
        String[] subString = selectedRecord.split(",");

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);

        TextView recordTextView = dialog.findViewById(R.id.recordTextView);
        recordTextView.setText(selectedRecord);

        Button yesDeleteButton = dialog.findViewById(R.id.yesDeleteButton);
        yesDeleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                sqh.deleteRecordFromSongTable(sqdb, subString[0]);

                dialog.dismiss();

                refreshListView();

            }
        });



        Button noDeleteButton = dialog.findViewById(R.id.noDeleteButton);
        noDeleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
             dialog.dismiss();

            }
        });

        dialog.show();

    }

}