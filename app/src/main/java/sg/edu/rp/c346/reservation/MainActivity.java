package sg.edu.rp.c346.reservation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etNum;
    EditText etPax;
    EditText etDate;
    EditText etTime;
    RadioButton rbSmoke;
    RadioButton rbNonSmoke;
    Button btnSubmit;
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.editTextName);
        etNum = findViewById(R.id.editTextMobile);
        etPax = findViewById(R.id.editTextPax);
        etDate = findViewById(R.id.editTextDate);
        etTime = findViewById(R.id.editTextTime);
        rbSmoke = findViewById(R.id.radioButtonSmoke);
        rbNonSmoke = findViewById(R.id.radioButtonNonSmoke);
        btnSubmit = findViewById(R.id.buttonSubmit);
        btnReset = findViewById(R.id.buttonReset);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar now = Calendar.getInstance();
                String display = "name: " + etName.getText() +
                        "\nPhone number: " + etNum.getText() +
                        "\nPax: " + etPax.getText() + "\nDate: " + etDate.getText().toString() + "\nTime: " + etTime.getText().toString();

                if(etName.getText().toString().length() == 0 || etNum.getText().toString().length() == 0 || etPax.getText().toString().length() == 0){
                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                    myBuilder.setTitle("Input");
                    myBuilder.setMessage("Please enter your input");
                    myBuilder.setCancelable(false);
                    myBuilder.setNeutralButton("Cancel",null);
                    myBuilder.setPositiveButton("OK",null);
                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();
                }
                else{
                    if(rbSmoke.isChecked()){
                        AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                        myBuilder.setTitle("Booking");
                        myBuilder.setMessage(display +"\nsmoking table");
                        myBuilder.setCancelable(false);
                        myBuilder.setNeutralButton("Cancel",null);
                        myBuilder.setPositiveButton("OK",null);
                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    }
                    else{
                        AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                        myBuilder.setTitle("Booking");
                        myBuilder.setMessage(display + "\nNon-smoking table");
                        myBuilder.setCancelable(false);
                        myBuilder.setNeutralButton("Cancel",null);
                        myBuilder.setPositiveButton("OK",null);
                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    }
                }

            }
        });
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create the Listener to set the date
                final Calendar now = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String datetime = (dayOfMonth + "/" + (monthOfYear+1) + "/" + year);

                        etDate.setText(datetime);
                    }
                };

                //Create the Date Picker Dialog
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, myDateListener, 2014, 11, 31);
                myDateDialog.show();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar now = Calendar.getInstance();
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker View, int hourofDay, int minute) {
                        etTime.setText(hourofDay + ":" + minute);
                    }
                };

                //create the time picker dialog
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,myTimeListener,now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),true);
                myTimeDialog.show();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etName.setText(null);
                etNum.setText(null);
                etPax.setText(null);
                etDate.setText(null);
                etTime.setText(null);
                if (rbSmoke.isChecked()) {
                    rbSmoke.setChecked(false);
                }
                if (rbNonSmoke.isChecked()) {
                    rbNonSmoke.setChecked(false);
                }
            }
        });
    }
}