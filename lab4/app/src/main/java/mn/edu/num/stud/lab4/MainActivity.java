package mn.edu.num.stud.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import model.Database;
import model.Word;

public class MainActivity extends AppCompatActivity  {

    Button btnPrev;
    Button btnDelete;
    Button btnNext;
    Button btnAdd;
    Button btnEdit;
    Button btnSettings;
    Button btnAddFromFile;
    TextView txtSource;
    TextView txtTarget;

    SharedPreferences sharedPreferences;
    Database db;
    Integer index = 0;
    ArrayList<Word> words = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSource = this.findViewById(R.id.sourceTxt);
        txtTarget = this.findViewById(R.id.targetTxt);

        btnAdd = this.findViewById(R.id.btnAdd);
        btnSettings = this.findViewById(R.id.btnSettings);
        btnPrev = this.findViewById(R.id.btnPrev);
        btnDelete = this.findViewById(R.id.btnDelete);
        btnNext = this.findViewById(R.id.btnNext);
        btnEdit = this.findViewById(R.id.btnEdit);

        showData();

        txtSource.setOnLongClickListener(view -> {
            Intent intent = new Intent(this, AddWordActivity.class);
            intent.putExtra("editObject", words.get(index));
            startActivity(intent);
            return true;
        });

        txtTarget.setOnLongClickListener(view -> {
            Intent intent = new Intent(this, AddWordActivity.class);
            intent.putExtra("editObject", words.get(index));
            startActivity(intent);
            return true;
        });

        btnAdd.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddWordActivity.class);
            startActivity(intent);
        });
        btnSettings.setOnClickListener(view -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
        btnNext.setOnClickListener(view -> {
            index++;
            showData();
        });
        btnPrev.setOnClickListener(view -> {
            index--;
            showData();
        });
        btnDelete.setOnClickListener(view -> {
            AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);
            myAlertBuilder.setTitle("Анхааруулга");
            myAlertBuilder.setMessage("Та устгахдаа итгэлтэй байна уу?");
            myAlertBuilder.setPositiveButton("Устгах", (dialog, which) -> {
                db.deleteData(words.get(index));
                Toast.makeText(getApplicationContext(), "Амжилттай устав", Toast.LENGTH_SHORT).show();
                txtSource.setText("");
                txtTarget.setText("");
                showData();
            });
            myAlertBuilder.setNegativeButton("Цуцлах", (dialog, which) -> Toast.makeText(getApplicationContext(), "Цуцлагдлаа", Toast.LENGTH_SHORT).show());
            myAlertBuilder.show();
        });
        btnEdit.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddWordActivity.class);
            intent.putExtra("editObject", words.get(index));
            startActivity(intent);
        });
    }

    public void showData(){
        db = new Database(this);
        words = db.getWords();
        checkSize(words);
        sharedPreferences = getSharedPreferences(SettingsActivity.SHARED_PREFS, MODE_PRIVATE);
        if((words.size() - 1) >= index && index >= 0) {
            if (sharedPreferences.getBoolean(SettingsActivity.ONLY_F, false)) {
                txtSource.setText(words.get(index).getSourceWord());
            } else if (sharedPreferences.getBoolean(SettingsActivity.ONLY_M, false)) {
                txtTarget.setText(words.get(index).getTargetWord());
            } else if (sharedPreferences.getBoolean(SettingsActivity.ONLY_B, true)) {
                txtSource.setText(words.get(index).getSourceWord());
                txtTarget.setText(words.get(index).getTargetWord());
            }
        }else{
            Toast.makeText(this, "Үг байхгүй байна", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkSize(ArrayList<Word> words){
        if(words.size() == 0){
            btnEdit.setEnabled(false);
            btnPrev.setEnabled(false);
            btnDelete.setEnabled(false);
            btnNext.setEnabled(false);
        }
    }

}

