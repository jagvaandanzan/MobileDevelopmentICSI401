package mn.edu.num.stud.lab3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class relativeActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnOk;
    Button btnCancel;
    RatingBar ratingBar;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative);
        btnCancel = this.findViewById(R.id.btnRelativeCancel);
        btnCancel.setOnClickListener(this);
        btnOk = this.findViewById(R.id.btnRelativeOk);
        btnOk.setOnClickListener(this);
        ratingBar = this.findViewById(R.id.ratingBar);
        timePicker = this.findViewById(R.id.timePicker);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        timePicker.setIs24HourView(true);
        ratingBar.setRating(intent.getExtras().getFloat("rating"));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnRelativeCancel){
            Intent intent = new Intent(this, MainActivity.class);
            setResult(41, intent);
            finish();
        }else if(view.getId() == R.id.btnRelativeOk){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("ratingBar", ratingBar.getRating());
            intent.putExtra("timeHour", String.valueOf(timePicker.getCurrentHour()));
            intent.putExtra("timeMinute", String.valueOf(timePicker.getCurrentMinute()));
            setResult(4, intent);
            finish();
        }
    }
}