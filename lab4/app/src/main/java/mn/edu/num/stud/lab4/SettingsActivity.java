package mn.edu.num.stud.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPref";
    public static final String ONLY_F = "only_f";
    public static final String ONLY_M = "only_m";
    public static final String ONLY_B = "only_b";

    RadioButton radOnlyF, radOnlyM, radOnlyB;
    Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnSave = this.findViewById(R.id.btnSave);
        btnCancel = this.findViewById(R.id.btnCancel);
        radOnlyF = this.findViewById(R.id.radOnlyF);
        radOnlyM = this.findViewById(R.id.radOnlyM);
        radOnlyB = this.findViewById(R.id.radOnlyB);

        loadData();

        btnSave.setOnClickListener(view -> {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();


                editor.putBoolean(ONLY_F, radOnlyF.isChecked());
                editor.putBoolean(ONLY_M, radOnlyM.isChecked());
                editor.putBoolean(ONLY_B, radOnlyB.isChecked());

                editor.apply();
                Toast.makeText(this, "Амжилттай", Toast.LENGTH_SHORT).show();
        });

        btnCancel.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        if (sharedPreferences.getBoolean(SettingsActivity.ONLY_F, false)) {
            radOnlyF.setChecked(true);
        } else if (sharedPreferences.getBoolean(SettingsActivity.ONLY_M, false)) {
            radOnlyM.setChecked(true);
        } else if (sharedPreferences.getBoolean(SettingsActivity.ONLY_B, true)) {
            radOnlyB.setChecked(true);
        }
    }
}