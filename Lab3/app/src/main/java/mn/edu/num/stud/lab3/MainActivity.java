package mn.edu.num.stud.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String tag = "mainactivity";
    Button btnRelative;
    Button btnLine;
    Button btnTable;
    Button btnFrame;
    RadioButton radMale;
    RadioButton radFemale;
    TextView txtTime;
    TextView txtName;
    TextView txtAge;
    CheckBox cboxJava;
    CheckBox cboxC;
    CheckBox cboxCsharp;
    TextView txtDate;
    TextView txtTimePicker;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.i(tag, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFrame = this.findViewById(R.id.btnFrame);
        btnFrame.setOnClickListener(this);
        txtAge = this.findViewById(R.id.resAge);
        txtName = this.findViewById(R.id.resName);

        btnTable = this.findViewById(R.id.btnTable);
        btnTable.setOnClickListener(this);
        radMale = this.findViewById(R.id.radBtnMale);
        radFemale = this.findViewById(R.id.radBtnFemale);
        txtTime = this.findViewById(R.id.txtTimePicker);

        btnLine = this.findViewById(R.id.btnLine);
        btnLine.setOnClickListener(this);
        cboxJava = this.findViewById(R.id.cboxJava);
        cboxC = this.findViewById(R.id.cboxC);
        cboxCsharp = this.findViewById(R.id.cboxCsharp);
        txtDate = this.findViewById(R.id.txtDate);

        btnRelative = this.findViewById(R.id.btnRelative);
        btnRelative.setOnClickListener(this);
        txtTimePicker = this.findViewById(R.id.txtTime);
        ratingBar = this.findViewById(R.id.ratingBar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            //frame layoutaas irsen medeelel
            if(resultCode == 1){
                String name = data.getExtras().getString("name");
                String age = data.getExtras().getString("age");
                txtName.setText(name);
                txtAge.setText(age);
            } else if(resultCode == 11){
                txtName.setText("");
                txtAge.setText("");
            }
            //linear layoutaas irsen medeelel
            else if(resultCode == 2){
                cboxJava.setChecked(data.getExtras().getBoolean("java"));
                cboxC.setChecked(data.getExtras().getBoolean("c"));
                cboxCsharp.setChecked(data.getExtras().getBoolean("c#"));
                String year = data.getExtras().getString("year");
                String month = data.getExtras().getString("month");
                String day = data.getExtras().getString("day");
                txtDate.setText(String.format("%s:%s:%s", year, month, day));
            }
            else if(resultCode == 21){
                cboxJava.setChecked(false);
                cboxC.setChecked(false);
                cboxCsharp.setChecked(false);
                txtDate.setText("");
            }
//            table layoutaas irsen medeelel
            else if(resultCode == 3){
                String hour = data.getExtras().getString("hour");
                String minute = data.getExtras().getString("minute");
                if(data.getExtras().getBoolean("rad1"))
                    radMale.setChecked(true);
                if(data.getExtras().getBoolean("rad2"))
                    radFemale.setChecked(true);
                txtTime.setText(String.format("%s:%s", hour, minute));
            }
            else if(resultCode == 31){
                radMale.setChecked(false);
                radFemale.setChecked(false);
                txtTime.setText("");
            }
//            relative layoutaas irsen medeelel
            else if(resultCode == 4){
                ratingBar.setRating(data.getExtras().getFloat("ratingBar"));
                String hours = data.getExtras().getString("timeHour");
                String minutes = data.getExtras().getString("timeMinute");
                txtTimePicker.setText(String.format("%s:%s", hours, minutes));
            }
            else if(resultCode == 41){
                ratingBar.setRating(0);
                txtTimePicker.setText("");
            }
        }
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnFrame){
            Intent intent = new Intent(this, frameActivity.class);
            intent.putExtra("name", txtName.getText());
            intent.putExtra("age", txtAge.getText());
            startActivityForResult(intent, 1);
        }else if(view.getId() == R.id.btnLine){
            Intent intent = new Intent(this, linearActivity.class);
            intent.putExtra("java", cboxJava.isChecked());
            intent.putExtra("c", cboxC.isChecked());
            intent.putExtra("c#", cboxCsharp.isChecked());
            startActivityForResult(intent, 1);
        }else if(view.getId() == R.id.btnTable){
            Intent intent = new Intent(this, tableActivity.class);
            intent.putExtra("male", radMale.isChecked());
            intent.putExtra("female", radFemale.isChecked());
            startActivityForResult(intent, 1);
        }else if(view.getId() == R.id.btnRelative){
            Intent intent = new Intent(this, relativeActivity.class);
            intent.putExtra("rating", ratingBar.getRating());
            intent.putExtra("ratingHour", txtTimePicker.getText().toString().split(":"));
            startActivityForResult(intent, 1);
        }
    }
}