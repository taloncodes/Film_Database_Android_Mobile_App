package com.example.dbcopyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertRecordActivity extends Activity
{

    EditText editTextSongTitle;
    EditText editTextYear;
    EditText editTextArtist;
    EditText editTextAlbum;

    Button insertRecordButton;
    Button cancelInsertButton;

    OpenDatabase sqh;
    SQLiteDatabase sqdb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_record);

        initDataBase();

        setupControls();


    }   //  protected void onCreate(Bundle savedInstanceState)

    protected void setupControls()
    {
        editTextSongTitle = findViewById(R.id.editTextSongTitle);
        editTextYear = findViewById(R.id.editTextYear);
        editTextArtist = findViewById(R.id.editTextArtist);
        editTextAlbum = findViewById(R.id.editTextAlbum);

        insertRecordButton = findViewById(R.id.insertRecordButton);
        insertRecordButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sqh.insertRecordIntoSongTable(sqdb, editTextSongTitle.getText().toString(),
                        editTextYear.getText().toString(),
                        editTextArtist.getText().toString(),
                        editTextAlbum.getText().toString());

                finish();

            }
        });

        cancelInsertButton = findViewById(R.id.cancelInsertButton);
        cancelInsertButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                finish();

            }
        });

    }

    public void initDataBase()

    {
        //  init the SQLiteHelper Class
        sqh = new OpenDatabase(this);

        //retrieve readable and writeable database
        sqdb = sqh.getWritableDatabase();

    }
}   //  public class InsertRecordActivity extends Activity