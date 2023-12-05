package edu.purdue.springcalc;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SpringCalculatorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spring spring = new Spring();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);

        // set end type drop down menu
        Spinner endTypeSpinner = findViewById(R.id.end_type_input);
        ArrayAdapter<CharSequence> endTypeAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.end_type_choices,
                android.R.layout.simple_spinner_item
        );
        endTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endTypeSpinner.setAdapter(endTypeAdapter);
        endTypeSpinner.setOnItemSelectedListener(this);

        // set material drop down menu
        Spinner materialSpinner = findViewById(R.id.material_input);
        ArrayAdapter<CharSequence> materialAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.material_choices,
                android.R.layout.simple_spinner_item
        );
        materialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        materialSpinner.setAdapter(materialAdapter);
        materialSpinner.setOnItemSelectedListener(this);

        final Button calcButton = findViewById(R.id.calc_button);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateResult();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.material_input) {
            spring.material = adapterView.getCount();
        } else if (adapterView.getId() == R.id.end_type_input) {
            spring.endType = adapterView.getCount();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void updateResult() {

        try {
            TextView pitchResult = findViewById(R.id.pitch_result);
            TextView totalCoilResult = findViewById(R.id.total_coil_result);
            TextView activeCoilResult = findViewById(R.id.active_coil_result);
            TextView springRateResult = findViewById(R.id.spring_rate_result);
            TextView forceResult = findViewById(R.id.force_result);
            TextView fosResult = findViewById(R.id.FoS_result);

            String pitchResultStr = String.format(Locale.US,
                    "%.2f mm", spring.calcPitch());
            String totalCoilResultStr = String.format(Locale.US,
                    "%d coil(s)", spring.calcTotalCoil());
            String activeCoilResultStr = String.format(Locale.US,
                    "%d coil(s)", spring.calcActiveCoil());
            String springRateResultStr = String.format(Locale.US,
                    "%.2f", spring.calcSpringRate());
            String forceResultStr = String.format(Locale.US,
                    "%.2f N", spring.calcForce());
            String fosResultStr =  String.format(Locale.US,
                    "%.1f", spring.calcFoS());

            pitchResult.setText(pitchResultStr);
            totalCoilResult.setText(totalCoilResultStr);
            activeCoilResult.setText(activeCoilResultStr);
            springRateResult.setText(springRateResultStr);
            forceResult.setText(forceResultStr);
            fosResult.setText(fosResultStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}