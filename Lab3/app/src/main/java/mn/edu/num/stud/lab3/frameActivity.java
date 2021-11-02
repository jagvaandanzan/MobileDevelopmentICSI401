package mn.edu.num.stud.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class frameActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCancel;
    Button btnOk;
    EditText edtName;
    EditText edtAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        edtAge = this.findViewById(R.id.edtAge);
        edtAge.setOnClickListener(this);
        edtName = this.findViewById(R.id.edtName);
        edtName.setOnClickListener(this);
        btnCancel = this.findViewById(R.id.btnFrameCancel);
        btnCancel.setOnClickListener(this);
        btnOk = this.findViewById(R.id.btnFrameOk);
        btnOk.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String name = intent.getExtras() != null ? intent.getExtras().getString("name") : "";
        String age = intent.getExtras() != null ?  intent.getExtras().getString("age") : "";
        edtAge.setText(age);
        edtName.setText(name);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnFrameCancel){
            Intent intent = new Intent();
            intent.setClassName("mn.edu.num.stud.lab3", MainActivity.class.getName());
            setResult(11, intent);
            finish();
        }else if(view.getId() == R.id.btnFrameOk){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("name", String.valueOf(edtName.getText()));
            intent.putExtra("age", String.valueOf(edtAge.getText()));
            setResult(1, intent);
            finish();
        }
    }
}