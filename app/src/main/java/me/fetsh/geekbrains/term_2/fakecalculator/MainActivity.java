package me.fetsh.geekbrains.term_2.fakecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static String calcEXP = "calcEXP";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText text = findViewById(R.id.editText);
        Button runEcho = findViewById(R.id.button);
        runEcho.setOnClickListener(v -> {
            if (text.getText().toString().matches("[0-9+-/* ]+")) {
                Uri uri = Uri.parse("calc://expression");
                Intent runCalcIntent = new Intent(Intent.ACTION_VIEW, uri);
                runCalcIntent.putExtra(calcEXP, text.getText().toString());
                if (runCalcIntent.resolveActivityInfo(getPackageManager(), runCalcIntent.getFlags()) != null) {
                    startActivity(runCalcIntent);
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_suitable_activity), Toast.LENGTH_SHORT).show();
                }
            } else {
                text.setError(getResources().getString(R.string.illegal_expression));
            }
        });

    }
}