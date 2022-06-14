package com.example.dbcopyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainMenuActivity extends Activity
{
    Button listAndSearchButton;
    Button insertActivityButton;
    Button modifyRecordButton;
    Button deleteRecordButton;

    private static String DATABASE_PATH_AND_NAME;
    private static String CHECK_DATABASES_FOLDER;

    //private static final String DATABASE_NAME = "music.db";
    private  static final String LOG_TAG = "MUSIC_DB";

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        setupDatabaseStrings();
        setupDatabase();

        setupControls();
    }   //  protected void onCreate(Bundle savedInstanceState)


    protected void setupControls()
    {
       listAndSearchButton = findViewById(R.id.listAndSearchButton);
       listAndSearchButton.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {
               Intent intent = new Intent(getBaseContext(), MainActivity.class);
               startActivity(intent);

           }
       });

       insertActivityButton = findViewById(R.id.insertActivityButton);
       insertActivityButton.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {
               Intent intent = new Intent(getBaseContext(), InsertRecordActivity.class);
               startActivity(intent);

           }
       });

       modifyRecordButton = findViewById(R.id.modifyRecordButton);
       modifyRecordButton.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {
            Intent intent = new Intent(getBaseContext(), ModifyRecord.class);
            startActivity(intent);

           }
       });

       deleteRecordButton = findViewById(R.id.deleteRecordButton);
       deleteRecordButton.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {
               Intent intent = new Intent(getBaseContext(), DeleteActivity.class);
               startActivity(intent);

           }
       });

    }

    protected void setupDatabaseStrings()
    {

        //FULL PATH TO WHERE WE WILL COPY MUSIC.DB TO ON THE  EMULATOR!
        DATABASE_PATH_AND_NAME = "/data/data/" + getApplicationContext().getPackageName() +
                "/databases/" + OpenDatabase.DATABASE_NAME;

        //USED TO CHECK IF THE "DATABASES" FOLDER EXISTS
        CHECK_DATABASES_FOLDER = "/data/data/" + getApplicationContext().getPackageName() +
                "/databases";

        //DEBUG INFO
        Log.i("DATABASE_PATH_AND_NAME", "DATABASE_PATH_AND_NAME = " + DATABASE_PATH_AND_NAME);
        Log.i("CHECK_DATABASES_FOLDER", "CHECK_DATABASES_FOLDER = " + CHECK_DATABASES_FOLDER);

        //  /data/data/com.example.dbcopyexample/databases/music.db
        //  /data/data/com.example.dbcopyexample/databases

    }

    protected void setupDatabase()
    {
        ctx = this.getBaseContext();
        try {
            CopyDataBaseFromAsset();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }   //protected void setupDatabase()

    protected void CopyDataBaseFromAsset() throws IOException

    {

        Log.w(  LOG_TAG , "Starting copying...");
        String outputFileName = DATABASE_PATH_AND_NAME;

        File databaseFolder = new File( CHECK_DATABASES_FOLDER  );

        //database folder exists? No - Create it and copy !!!
        if (    !databaseFolder.exists()    )
        {
            databaseFolder.mkdir();

            //  Open the sqlite database "music.db" found in the assets folder
            InputStream in = ctx.getAssets().open(OpenDatabase.DATABASE_NAME);

            OutputStream out = new FileOutputStream(outputFileName);
            byte[] buffer = new byte[1024];
            int length;

            while ( (length = in.read(buffer)) > 0 )
            {
                out.write(buffer, 0, length);

            }   //  while ( (length = in.read(buffer)) > 0 )

            out.flush();
            out.close();
            in.close();

            Log.w(LOG_TAG, "Completed.");
        }   //  if (    !databaseFolder.exists()    )

    }   //  protected void CopyDataBaseFromAsset()

}   //  public class MainMenuActivity extends AppCompatActivity