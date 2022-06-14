package com.example.dbcopyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifySelectedRecordActivity extends Activity
{
    EditText idEditTextModifyActivity;
    EditText songTitleEditTextModifyActivity;
    EditText yearEditTextModifyActivity;
    EditText artistEditTextModifyActivity;
    EditText albumEditTextModifyActivity;

    Button modifyButtonModifyActivity;
    Button cancelButtonModifyActivity;

    OpenDatabase sqh;
    SQLiteDatabase sqdb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_selected_record);

        //get the extra data from the intent
        String passedRecord = getIntent().getExtras().getString("record");
        Log.w("PASSED_RECORD", "Record = " + passedRecord);

        initDatabase();

        setupControls();

        //populate the editText widgets with the appropriate substrings!!!
        String[] subStrings = passedRecord.split(",");

        idEditTextModifyActivity.setText(subStrings[0]);
        songTitleEditTextModifyActivity.setText(subStrings[1]);
        yearEditTextModifyActivity.setText(subStrings[2]);
        artistEditTextModifyActivity.setText(subStrings[3]);
        albumEditTextModifyActivity.setText(subStrings[4]);


    }   //  protected void onCreate(Bundle savedInstanceState)



    protected void setupControls()
    {
        idEditTextModifyActivity = findViewById(R.id.idEditTextModifyActivity);
        songTitleEditTextModifyActivity = findViewById(R.id.songTitleEditTextModifyActivity);
        yearEditTextModifyActivity = findViewById(R.id.yearEditTextModifyActivity);
        artistEditTextModifyActivity = findViewById(R.id.artistEditTextModifyActivity);
        albumEditTextModifyActivity = findViewById(R.id.albumEditTextModifyActivity);

        modifyButtonModifyActivity = findViewById(R.id.modifyButtonModifyActivity);
        modifyButtonModifyActivity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sqh.modifyRecordsInSongTable(sqdb, idEditTextModifyActivity.getText().toString(),
                        songTitleEditTextModifyActivity.getText().toString(),
                        yearEditTextModifyActivity.getText().toString(),
                        artistEditTextModifyActivity.getText().toString(),
                        albumEditTextModifyActivity.getText().toString());

                finish();

            }
        });

        cancelButtonModifyActivity = findViewById(R.id.cancelButtonModifyActivity);
        cancelButtonModifyActivity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

    }

    public void initDatabase()
    {
        //Init the SQLite Helper Class
        sqh = new OpenDatabase(this);

        //Retrieve DB for R/W
        sqdb = sqh.getWritableDatabase();
    }



}   //     public class ModifySelectedRecordActivity extends Activity