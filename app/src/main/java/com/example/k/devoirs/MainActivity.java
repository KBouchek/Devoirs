package com.example.k.devoirs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ListView mListView;
    ImageButton imageButton;
    Cursor c;
    DevoirCursorAdapter devoircursoradapter;
    DevoirDb db;
    long idtodelete;
    static final int CHILD_ACTIVITY_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */


        mListView = (ListView) findViewById(R.id.listx);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //final String item = (String) parent.getItemAtPosition(position);
                //Snackbar.make(view, "position: " + Integer.toString(position), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //final String item = (String) parent.getItemAtPosition(position);
                idtodelete = id;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Suppression ?");

                builder.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int idx) {
                        //db.deleteDevoir(id);
                        //refreshlist();
                        deletewithconfirm(idtodelete);
                    }
                });
                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int idx) {
                        // User cancelled the dialog
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                /*c = db.getAll();
                DevoirCursorAdapter adapt =  new DevoirCursorAdapter(MainActivity.this, c);
                mListView.setAdapter(adapt);*/

            }

        });

        db = new DevoirDb(this);
        Cursor todoCursor = db.getAll();
        devoircursoradapter = new DevoirCursorAdapter(this, todoCursor);
        mListView.setAdapter(devoircursoradapter);
       /* List<Devoir> contacts = db.getAllDevoirs();
        //String[] values;
        List<String> values = new ArrayList<String>();
        for (Devoir cn : contacts) {
            //String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            values.add(cn.toString());
            //Log.d("Name: ", log);
        }*/
        //Devoir d = new Devoir("T","tata","tata",new Date(),new Date(),true);
       // ArrayList<Devoir> AR =new ArrayList<Devoir>();
        /*String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };*/
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);*/


        // Assign adapter to ListView
        // mListView.setAdapter(adapter);

        imageButton = (ImageButton) findViewById(R.id.addButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent i = new Intent(getBaseContext(), FicheDevoir.class);
                i.putExtra("PersonID", "tutu");
                //startActivity(i);
                startActivityForResult(i, CHILD_ACTIVITY_CODE);
            }
        });




        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.projectSpinner);

        // Spinner click listener
        //spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("");
        categories.add("T ES");
        categories.add("T ES spé");
        categories.add("T STL");
        categories.add("1ère ES");
        categories.add("2nd");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
         // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        }

    public void deletewithconfirm(long iddel) {
        db.deleteDevoir(iddel);
        refreshlist();
    }
 public void refreshlist() {
     c = db.getAll();
     DevoirCursorAdapter adapt =  new DevoirCursorAdapter(MainActivity.this, c);
     mListView.setAdapter(adapt);
 }
    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        //Cursor todoCursor = db.getAll();
        //devoircursoradapter = new DevoirCursorAdapter(this, todoCursor);
        //devoircursoradapter.notifyDataSetChanged();
        //this.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == CHILD_ACTIVITY_CODE) {
            // Make sure the request was successful
            //if (resultCode == RESULT_OK) {
            Cursor todoCursor = db.getAll();
            devoircursoradapter = new DevoirCursorAdapter(this, todoCursor);
            devoircursoradapter.notifyDataSetChanged();
            mListView.setAdapter(devoircursoradapter);
            //}
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        if(item != "") {
            Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                    Toast.LENGTH_SHORT).show();
            //Devoir d = new Devoir("T","tata","tata",new Date(),new Date(),true);
            //Toast.makeText(getApplicationContext(), "Selected: " + item + "" , Toast.LENGTH_LONG).show();
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
