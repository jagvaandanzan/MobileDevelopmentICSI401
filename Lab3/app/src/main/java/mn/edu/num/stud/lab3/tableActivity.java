package mn.edu.num.stud.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TimePicker;

public class tableActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnOk;
    Button btnCancel;
    TimePicker timePicker;
    RadioButton radioButton1;
    RadioButton radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        btnOk = this.findViewById(R.id.btnTableOk);
        btnOk.setOnClickListener(this);
        btnCancel = this.findViewById(R.id.btnTableCancel);
        btnCancel.setOnClickListener(this);
        timePicker = this.findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        radioButton1 = this.findViewById(R.id.radBtnMale);
        radioButton2 = this.findViewById(R.id.radBtnFemale);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        radioButton1.setChecked(intent.getExtras().getBoolean("male"));
        radioButton2.setChecked(intent.getExtras().getBoolean("female"));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnTableCancel){
            Intent intent = new Intent(this, MainActivity.class);
            setResult(31, intent);
            finish();
        }else if(view.getId() == R.id.btnTableOk){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("rad1", radioButton1.isChecked());
            intent.putExtra("rad2", radioButton2.isChecked());
            intent.putExtra("hour", String.valueOf(timePicker.getCurrentHour()));
            intent.putExtra("minute", String.valueOf(timePicker.getCurrentMinute()));
            setResult(3, intent);
            finish();
        }
    }
}