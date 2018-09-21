package com.mahlabs.happyhourslicer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class ResultsActivity extends Activity {

    private EditText edt_result_bebados, edt_result_nao_bebados, edt_result_ambos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        edt_result_bebados = (EditText)findViewById(R.id.edt_result_bebados);
        edt_result_nao_bebados = (EditText)findViewById(R.id.edt_result_nao_bebados);
        edt_result_ambos = (EditText)findViewById(R.id.edt_result_ambos);

        Bundle bundle = getIntent().getExtras();

        if(bundle.containsKey("result_bebados") &&
                bundle.containsKey("result_famintos") &&
                bundle.containsKey("result_ambos")) {

            edt_result_bebados.setText(bundle.getString("result_bebados"));
            edt_result_nao_bebados.setText(bundle.getString("result_famintos"));
            edt_result_ambos.setText(bundle.getString("result_ambos"));
        }
    }

}
