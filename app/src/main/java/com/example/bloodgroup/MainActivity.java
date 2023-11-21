package com.example.bloodgroup;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Spinner bloodgroupSpinner;
    private TextView namesTextView;

    private Map<String, ArrayList<String>> bloodGroupToNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bloodgroupSpinner = findViewById(R.id.bloodgroup_spinner);
        namesTextView = findViewById(R.id.names_textview);

        bloodGroupToNames = new HashMap<>();

        ArrayList<String> namesAp = new ArrayList<>();
        namesAp.add("Alice                                   1234567890");
        namesAp.add("Adam                                  9876543210");
        bloodGroupToNames.put("A+", namesAp);

        ArrayList<String> namesBp = new ArrayList<>();
        namesBp.add("Bob");
        namesBp.add("Bella");
        bloodGroupToNames.put("B+", namesBp);

        ArrayList<String> namesOp = new ArrayList<>();
        namesOp.add("Mark");
        namesOp.add("Antony");
        bloodGroupToNames.put("O+", namesOp);

        ArrayList<String> namesABp = new ArrayList<>();
        namesABp.add("Madhan");
        namesABp.add("Kumar");
        bloodGroupToNames.put("AB+", namesABp);

        ArrayList<String> namesABn = new ArrayList<>();
        namesABn.add("Vaseegaran");
        namesABn.add("Bohra");
        bloodGroupToNames.put("AB-", namesABn);

        ArrayAdapter<String> bloodgroupArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(bloodGroupToNames.keySet()));
        bloodgroupArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodgroupSpinner.setAdapter(bloodgroupArrayAdapter);

        bloodgroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedBloodGroup = (String) parent.getItemAtPosition(position);
                ArrayList<String> names = bloodGroupToNames.get(selectedBloodGroup);
                if (names != null) {
                    displayNamesOneByOne(names);
                } else {
                    namesTextView.setText("No names found for this blood group.");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                namesTextView.setText("");
            }
        });
    }

    private void displayNamesOneByOne(ArrayList<String> names) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : names) {
            stringBuilder.append(name).append("\n");
        }
        namesTextView.setText(stringBuilder.toString());
    }
}
