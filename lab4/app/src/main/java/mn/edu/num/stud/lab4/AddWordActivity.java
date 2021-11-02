package mn.edu.num.stud.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import model.Database;
import model.Word;

public class AddWordActivity extends AppCompatActivity{

    EditText txtSource;
    EditText txtTarget;
    Button btnCancel;
    Button btnSave;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        btnSave = this.findViewById(R.id.btnSave);
        btnCancel = this.findViewById(R.id.btnCancel);
        txtSource = this.findViewById(R.id.sourceTxt);
        txtTarget = this.findViewById(R.id.targetTxt);
        db = new Database(this);
        btnCancel.setOnClickListener(view ->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        Word editObject = (Word) getIntent().getSerializableExtra("editObject");
        if(editObject != null){
            txtSource.setText(editObject.getSourceWord());
            txtTarget.setText(editObject.getTargetWord());

            btnSave.setOnClickListener(view ->{
                if(txtTarget.getText().toString().isEmpty() && txtSource.getText().toString().isEmpty())
                    Toast.makeText(this, "Та бүрэн мэдээлэл оруулна уу", Toast.LENGTH_SHORT).show();
                editObject.setSourceWord(txtSource.getText().toString());
                editObject.setTargetWord(txtTarget.getText().toString());
                db.updateData(editObject);
                Toast.makeText(this, "Үг амжилттай нэмлээ", Toast.LENGTH_SHORT).show();
                txtSource.setText("");
                txtTarget.setText("");
            });
        }else{
            btnSave.setOnClickListener(view ->{
                if(txtTarget.getText().toString().isEmpty() && txtSource.getText().toString().isEmpty())
                    Toast.makeText(this, "Та бүрэн мэдээлэл оруулна уу", Toast.LENGTH_SHORT).show();
                Word word = new Word(txtSource.getText().toString(), txtTarget.getText().toString());
                db.insertData(word);
                Toast.makeText(this, "Үг амжилттай нэмлээ", Toast.LENGTH_SHORT).show();
                txtSource.setText("");
                txtTarget.setText("");
            });
        }
    }
}