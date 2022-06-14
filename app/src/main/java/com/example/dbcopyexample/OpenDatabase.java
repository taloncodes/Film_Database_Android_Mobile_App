package com.example.dbcopyexample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class OpenDatabase extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "music.db";

    //  TOGGLE THIS NUMBER FOR UPDATING TABLES AND DATABASE
    private static final int DATABASE_VERSION = 1;

    OpenDatabase(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }   //OpenDatabase(Context context)

    @Override
    public void onCreate(SQLiteDatabase db)
    {

    }   //public void  onCreate(SQLiteDatabase db)

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }   //  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)

    public void displayRecords(SQLiteDatabase sqdb)
    {
        Cursor c = sqdb.rawQuery("SELECT * FROM songtable", null);

        if (c != null)
        {
            if  (c.moveToFirst())
            {
                do {
                    String id = c.getString(0);
                    String songtitle = c.getString(1);
                    String year = c.getString(2);
                    String artist = c.getString(3);
                    String album = c.getString(4);
                    Log.w("SONG_TABLE", "ID = " + id + " SONGTITLE = " + songtitle);



                } while (c.moveToNext());
            }
        }
        c.close();
    }   //  public void displayRecords()



    public String allRecordsInSongTable(SQLiteDatabase sqdb)
    {
        String result = "";
        Cursor c = sqdb.rawQuery("SELECT * FROM songtable", null);

        if (c != null)
        {
            if  (c.moveToFirst())
            {
                do {
                    String id = c.getString(0);
                    result = result + id + ",";
                    String songtitle = c.getString(1);
                    result = result + songtitle + ",";
                    String year = c.getString(2);
                    result = result + year +  ",";
                    String artist = c.getString(3);
                    result = result + artist + ",";
                    String album = c.getString(4);
                    result = result + album + "\n";


                    Log.w("SONG_TABLE", "ID = " + id + " SONGTITLE = " + songtitle);

                } while (c.moveToNext());
            }
        }
        c.close();

        return  result;

    }   //  public String allRecordsInSongTable(SQLiteDatabase sqdb)

    public String searchYearInSongTable(SQLiteDatabase sqdb, String searchYear)
    {
        String result = "";
        Cursor c = sqdb.rawQuery("SELECT * FROM songtable WHERE year = '" + searchYear + "'; ", null);
                                        //SELECT * FROM songtable WHERE year = '2020';
        if (c != null)
        {
            if  (c.moveToFirst())
            {
                do {
                    String id = c.getString(0);
                    result = result + id + ",";
                    String songtitle = c.getString(1);
                    result = result + songtitle + ",";
                    String year = c.getString(2);
                    result = result + year +  ",";
                    String artist = c.getString(3);
                    result = result + artist + ",";
                    String album = c.getString(4);
                    result = result + album + "\n";





                    Log.w("SONG_TABLE", "ID = " + id + " SONGTITLE = " + songtitle);



                } while (c.moveToNext());
            }
            else
            {
                result = "No Records Found for the Search Year = " + searchYear;
            }
        }
        c.close();

        return  result;

    }   //  public String searchYearInSongTable(SQLiteDatabase sqdb, searchYear)


    public int totalNumberOfRecordsInSongTable(SQLiteDatabase sqdb)
    {
        int count = 0;
        Cursor c = sqdb.rawQuery("SELECT count (*) FROM songtable", null);
        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    String id = c.getString(0);
                    count = Integer.parseInt(id);

                }
                while (c.moveToNext());
            }
        }
        c.close();
        return count;
    }

    public int dynamicNumberOfRecordsInSongTable(SQLiteDatabase sqdb, String searchYear)
    {
        int count = 0;
        Cursor c = sqdb.rawQuery("SELECT count (*) FROM songtable WHERE year = '" + searchYear + "'; ", null);
        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    String id = c.getString(0);
                    count = Integer.parseInt(id);

                }
                while (c.moveToNext());
            }
        }
        c.close();
        return count;
    }

    public ArrayList<String> allRecordsReturnArrayList(SQLiteDatabase sqdb)
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        String result = "";
        Cursor c = sqdb.rawQuery("SELECT * FROM songtable", null);

        if (c != null)
        {
            if  (c.moveToFirst())
            {
                do
                    {
                        result = "";
                        String id = c.getString(0);
                        result = result + id + ",";

                        String songtitle = c.getString(1);
                        result = result + songtitle + ",";

                        String year = c.getString(2);
                        result = result + year +  ",";

                        String artist = c.getString(3);
                        result = result + artist + ",";

                        String album = c.getString(4);
                        result = result + album;

                        arrayList.add(result);


                        Log.w("SONG_TABLE", "ID = " + id + " SONGTITLE = " + songtitle);

                } while (c.moveToNext());
            }
        }
        c.close();

        return arrayList;

    }   //  public ArrayList<String> allRecordsReturnArrayList(SQLiteDatabase sqdb)


    public void insertRecordIntoSongTable(SQLiteDatabase sqdb, String songTitle, String year, String artist, String album)
    {
        //  Insert SQL statement format //
        //  Insert INTO Customers (CustomerName, City, Country)
        //  VALUES ('Cardinal', 'Stavanger', 'Norway'); ///////////

        String insertQuery = "INSERT INTO songtable(songTitle, year, artist, album) ";
        insertQuery = insertQuery + " VALUES ('" + songTitle + "',";
        insertQuery = insertQuery + " '" + year + " ',";
        insertQuery = insertQuery + " '" + artist + " ',";
        insertQuery = insertQuery + " '" + album + " ');";

        sqdb.execSQL(insertQuery);

    }

    public void modifyRecordsInSongTable (SQLiteDatabase sqdb, String id, String songTitle, String year, String artist, String album)
    {
        //We will use this example as a template to help you create the SQL statement:
        //Update Customers
        //Set ContactName = 'Alfred Schmidt', City = 'Frankfurt'
        //where CustomerID = 1;

        String updateQuery = "UPDATE songtable ";
        updateQuery = updateQuery + "SET songTitle = '" + songTitle + "',";
        updateQuery = updateQuery + "year = '" + year + "',";
        updateQuery = updateQuery + "artist = '" + artist + "',";
        updateQuery = updateQuery + "album = '" + album + "'";
        updateQuery = updateQuery + " WHERE ID = " + id + ";";

        sqdb.execSQL(updateQuery);
    }

    public void deleteRecordFromSongTable(SQLiteDatabase sqdb, String id)
    {
        //SQL example ...
        //DELETE FROM Customers WHERE CustomerName='Alfreds Futterkiste';

        String deleteQuery = "DELETE FROM songtable WHERE id = " + id + ";"; //id is  not considered a string within sql

        sqdb.execSQL(deleteQuery);

    }   //public void deleteRecordFromSongTable(SQLiteDatabase sqdb, String id)


}   //  public class OpenDatabase extends SQLiteOpenHelper
