package com.example.micha.tippler;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Created by micha on 11/1/2016.
 */
public class UserOptions extends Activity implements View.OnClickListener {

    private RadioGroup radioGroup;
    private RadioButton male, female;
    private RadioGroup radioGroup2;
    private RadioButton yes, no;
    private double genderConstant = 0.68;
    private double weight;
    private double beersDrank;
    private double wineDrank;
    private double shootsDrank;
    private double mixedDrank;
    private double totalDrank;
    private boolean eaten;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_options);

        radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);
        radioGroup2 = (RadioGroup) findViewById(R.id.myRadioGroup2);
        editText1 =(EditText)findViewById(R.id.editText1);
        editText2 =(EditText)findViewById(R.id.editText2);
        editText3 =(EditText)findViewById(R.id.editText3);
        editText4 =(EditText)findViewById(R.id.editText4);
        editText5 =(EditText)findViewById(R.id.editText5);

        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        no = (RadioButton) findViewById(R.id.no);
        yes = (RadioButton) findViewById(R.id.yes);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.male) {
                    genderConstant = 0.68;
                    Toast toast = Toast.makeText(getApplicationContext(), "choice: Male",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                } else {
                    genderConstant = 0.55;
                    Toast toast = Toast.makeText(getApplicationContext(), "choice: Female",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.no) {
                    eaten = false;
                    Toast toast= Toast.makeText(getApplicationContext(),
                            "Must kill drunchies!!!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                } else {
                    eaten = true;
                }
            }
        });



    }


    @Override
    public void onClick(View v) {

        if (!editText1.getText().toString().matches("")) {
            weight = Double.parseDouble(editText1.getText().toString().trim());
        } else {
            weight = 0.0;
        }
        if (!editText2.getText().toString().matches("")) {
           beersDrank = Double.parseDouble(editText2.getText().toString().trim());
        } else {
            beersDrank = 0.0;
        }
        if (!editText3.getText().toString().matches("")) {
            wineDrank = Double.parseDouble(editText3.getText().toString().trim());
        } else {
            wineDrank = 0.0;
        }
        if (!editText4.getText().toString().matches("")) {
            shootsDrank = Double.parseDouble(editText4.getText().toString().trim());
        } else {
            shootsDrank = 0.0;
        }
        if (!editText5.getText().toString().matches("")) {
            mixedDrank = Double.parseDouble(editText5.getText().toString().trim());
        } else {
            mixedDrank = 0.0;
        }
        if (weight != 0.0) {
            totalDrank = beersDrank + wineDrank + shootsDrank + (mixedDrank * 2);
            double value = totalDrank * 14;
            double rawNumber = weight * genderConstant * 453.592;
            rawNumber = value / rawNumber;
            double time = 0;
            double bacAsPercentage = rawNumber * 100.0;
            if (eaten) {
                bacAsPercentage = bacAsPercentage - 0.02;
            }
            DecimalFormat df = new DecimalFormat("#.#####");
            for (double x = bacAsPercentage; x > 0.08; x = x - 0.00025) {
                time++;
            }
            if (bacAsPercentage < 0.08) {
                Toast toast = Toast.makeText(getApplicationContext(), "Your BAC is approximately: " + df.format(bacAsPercentage) + " that's under the legal amount to drive" +
                                " so you are good to go!",
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            } else if (bacAsPercentage > 1) {
                Toast toast = Toast.makeText(getApplicationContext(), "RIP",
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            } else {
                if (time < 60) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Your BAC is approximately: " + df.format(bacAsPercentage) + " you need to wait " + time
                                    + " minutes before you can drive safely again! Have fun and be careful out there!",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Your BAC is approximately: " + df.format(bacAsPercentage) + " you need to wait " + (int) time / 60
                                    + "h" + (int) time % 60 + "m, before you can drive safely again! Have fun and be careful out there!",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }

            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Must input weight.",
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }
    }
}
