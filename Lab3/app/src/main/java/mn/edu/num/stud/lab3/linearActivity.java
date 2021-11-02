package mn.edu.num.stud.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;

import java.util.Date;

public class linearActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnOk;
    Button btnCancel;
    CheckBox cJava;
    CheckBox cC;
    CheckBox cCsharp;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        btnCancel = this.findViewById(R.id.btnLineCancel);
        btnCancel.setOnClickListener(this);
        btnOk = this.findViewById(R.id.btnLineOk);
        btnOk.setOnClickListener(this);
        cJava = this.findViewById(R.id.cJava);
        cC = this.findViewById(R.id.cC);
        cCsharp = this.findViewById(R.id.cCsharp);
        datePicker = this.findViewById(R.id.datePicker);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        cJava.setChecked(intent.getExtras().getBoolean("java"));
        cC.setChecked(intent.getExtras().getBoolean("c"));
        cCsharp.setChecked(intent.getExtras().getBoolean("c#"));
        Log.i("tag", String.valueOf(intent.getExtras().getBoolean("java")));
    }



    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLineCancel){
            Intent intent = new Intent(this, MainActivity.class);
            setResult(21, intent);
            finish();
        }else if(view.getId() == R.id.btnLineOk){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("java", cJava.isChecked());
            intent.putExtra("c", cC.isChecked());
            intent.putExtra("c#", cCsharp.isChecked());
            intent.putExtra("year", String.valueOf(datePicker.getYear()));
            intent.putExtra("month", String.valueOf(datePicker.getMonth() + 1));
            intent.putExtra("day", String.valueOf(datePicker.getDayOfMonth()));
            setResult(2, intent);
            finish();
        }
    }
}