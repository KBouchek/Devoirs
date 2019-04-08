package com.example.k.devoirs;

import android.app.AlertDialog;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Locale;

public class FicheDevoir extends AppCompatActivity implements View.OnClickListener {
    private Devoir devoir;
    private DatePicker datePicker;
    private Calendar calendar;
    private DatePickerDialog fromDatePickerDialog;
    private int year, month, day;
    private TextView pour,dux,travail;
    SimpleDateFormat dateFormatter;
    ImageButton keyboard;
    private final int SELECT_PHOTO = 1;
    private ImageView imageView;
    public RadioGroup radioGroup;
    public DevoirDb dvdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichedevoir);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dvdb = new DevoirDb(this);

        devoir = new Devoir();

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.fab);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
                launch();
                // Or: FragmentManager fragmentManager = getSupportFragmentManager()
            }
            });
        dux = (TextView) findViewById(R.id.dux);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
        travail= (TextView) findViewById(R.id.travail);

        pour = (TextView) findViewById(R.id.pour);
        pour.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);

                }
            }
        });

        dateFormatter = new SimpleDateFormat("EEEE dd MMM", Locale.FRANCE);
        keyboard= (ImageButton) findViewById(R.id.keyboard);
        pour.setOnClickListener(this);
        setDateTimeField();

        keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // AlphaAnimation animation;
                //animation = new AlphaAnimation(1f, 0f);
                //animation.setDuration(400);
                //animation.setAnimationListener(this);
                //view.startAnimation(animation);
               hideKeyboard(view);
            }
        });
        SimpleDateFormat df = new SimpleDateFormat("EEEE d MMM  -  HH'h'mm", Locale.FRANCE);
        String date = df.format(Calendar.getInstance().getTime());
        dux.setText("donné le " + date);
        devoir.SetDu(date);

        //imageView = (ImageView)findViewById(R.id.imageView);
        //Button pickImage = (Button) findViewById(R.id.btn_pick);
        //pickImage.setOnClickListener(new View.OnClickListener() {

          //  @Override
          //  public void onClick(View view) {
           //     Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
           //     photoPickerIntent.setType("image/*");
            //    startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            //}
        //});

        //dateView = (TextView) findViewById(R.id.textView3);
/*//

        pour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialogFragment = new DatePickerDialog(, this, year, month, day);
                dialogFragment.show(fm, "Date Picker");

                //DatePickerFragment

            }
        });*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

       /* switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imageView.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }*/
    }

    private String now() {
        SimpleDateFormat df = new SimpleDateFormat("EEEE d MMM  -  HH'h'mm", Locale.FRANCE);
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }
 public void launch() {

     String classe = "Karim";
     if(radioGroup.getCheckedRadioButtonId() != -1) {
         //return;
         int id= radioGroup.getCheckedRadioButtonId();
         View radioButton = radioGroup.findViewById(id);
         int radioId = radioGroup.indexOfChild(radioButton);
         RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);
         String selection = "";
         if(btn != null) {selection = String.valueOf(btn.getText());}

         classe = selection;
     }

        /* int id= radioGroup.getCheckedRadioButtonId();
         View radioButton = radioGroup.findViewById(id);
         int radioId = radioGroup.indexOfChild(radioButton);
         RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);
         String selection = "";
         if(btn != null) {selection = String.valueOf(btn.getText());}

         classe = selection;*/
         devoir.SetClasse(classe);
         //String datepour = pour.getText();




     AlertDialog.Builder builder = new AlertDialog.Builder(this);
     builder.setTitle("Devoir pour -> "+classe);

     LayoutInflater inflater = this.getLayoutInflater();
     View header = (View)getLayoutInflater().inflate(R.layout.fragment_devoir, null);
     TextView classeValue = (TextView) header.findViewById(R.id.classe);
     TextView dateValue = (TextView) header.findViewById(R.id.date);

     TextView travailValue = (TextView) header.findViewById(R.id.travaildonne);
     classeValue.setText(classe);

     //String pourx = (String)pour.getText();
     dateValue.setText(pour.getText());
     StringBuilder sb = new StringBuilder(pour.getText().length());
     sb.append(pour.getText());

     devoir.SetPour(sb.toString());

     //String travailx = (String)travail.getText();
     travailValue.setText(travail.getText());
     //// devoir.SetTravail(travailx);


     //SimpleDateFormat dfx = new SimpleDateFormat("EEEE d MMM  -  HH'h'mm", Locale.FRANCE);
     //String datex = dfx.format(Calendar.getInstance().getTime());

     // devoir.SetDu(datex);
     devoir.SetOk("0");

     StringBuilder sbx = new StringBuilder(travail.getText().length());
     sbx.append(travail.getText());
     devoir.SetTravail(sbx.toString());
     devoir.SetDu(now());
     //travail.getText()
    // headerValue.setText( this.getString(R.string.headerSupervise) );
     //TextView t = (View)getLayoutInflater().inflate(R.layout.textView4x, null);
     builder.setView(header);
             // builder.setView(inflater.inflate(R.layout.fragment_devoir, null));


             builder.setPositiveButton("Enregistrer", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int id) {
                     // User clicked OK button
                 String mess =  devoir.toString();
                     //Toast.makeText(getApplicationContext(),mess,
                     //        Toast.LENGTH_SHORT).show();

                     long ins  = dvdb.insert(devoir);
                             //
                     Toast.makeText(getApplicationContext(),"Object inserted",
                             Toast.LENGTH_SHORT).show();
                     finish();
                 }
             });
     builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int id) {
             // User cancelled the dialog
         }
     });

// Create the AlertDialog
     AlertDialog dialog = builder.create();
     dialog.show();
 }

    private void hideKeyboard(View v) {

        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(FicheDevoir.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
    @Override
    public void onClick(View view) {
        if(view == pour) {
            fromDatePickerDialog.show();
        }
    }
    private void setDateTimeField() {


        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                pour.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


    }

}
