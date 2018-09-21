package com.mahlabs.happyhourslicer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    private EditText edt_bebados, edt_famintos, edt_total, edt_bebidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_bebados = (EditText) findViewById(R.id.edt_bebados);
        edt_famintos = (EditText) findViewById(R.id.edt_famintos);
        edt_total = (EditText) findViewById(R.id.edt_total);
        edt_bebidas = (EditText) findViewById(R.id.edt_bebidas);

        //A intro se auto finalizará
        startActivity(new Intent(getApplicationContext(), SplashActivity.class));
    }

    public void onBtnSlicer(View v){
        if(v.getId() != R.id.btn_slicer){ return; }
        String ls_bebados, ls_famintos, ls_total, ls_bebidas, ls_msg = "";
        boolean lb_erro = false;
        int li_bebados, li_famintos;
        double ld_total, ld_bebidas;

        ls_bebados = edt_bebados.getText().toString();
        ls_famintos = edt_famintos.getText().toString();
        ls_total = edt_total.getText().toString();
        ls_bebidas = edt_bebidas.getText().toString();

        try {
            li_bebados = Integer.parseInt(ls_bebados);
        }catch(Exception e){
            li_bebados = 0;
        }
        try{
            li_famintos = Integer.parseInt(ls_famintos);
        }catch(Exception e){
            li_famintos = 0;
        }
        try{
            ld_total = Double.parseDouble(ls_total);
        }catch(Exception e){
            ld_total = 0.00;
        }
        try{
            ld_bebidas = Double.parseDouble(ls_bebidas);
        }catch(Exception e){
            ld_bebidas = 0.00;
        }

        if(ls_bebados.isEmpty()){
            //edt_bebados.setError("Informe o número de bêbados.");
            ls_msg = "Informe o número de bêbados.";
            edt_bebados.requestFocus();
            lb_erro = true;
        }
        if(!lb_erro && ls_famintos.isEmpty()){
            //edt_famintos.setError("Informe o número de famintos.");
            ls_msg = "Informe o número de famintos.";
            edt_famintos.requestFocus();
            lb_erro = true;
        }
        if(!lb_erro && (ls_total.isEmpty() || Double.isNaN(ld_total))){
            //edt_total.setError("Informe o valor total da conta.");
            ls_msg = "Informe o valor total da conta.";
            edt_total.requestFocus();
            lb_erro = true;
        }
        if(!lb_erro && (ls_bebidas.isEmpty() || Double.isNaN(ld_bebidas))){
            //edt_bebidas.setError("Informe o valor gasto em bebidas.");
            ls_msg = "Informe o valor gasto em bebidas.";
            edt_bebidas.requestFocus();
            lb_erro = true;
        }

        if(!lb_erro && (li_bebados > 0 && ld_bebidas == 0)){
            //O valor pago pelos bêbados é referente ao valor gasto em bebidas
            //edt_bebidas.setError("Se há bêbados, informe o valor gasto em bebidas.");
            ls_msg = "Se há bêbados, informe o valor gasto em bebidas.";
            edt_bebidas.requestFocus();
            lb_erro = true;
        }
        if(!lb_erro && (li_famintos > 0 && ld_total == 0)){
            //O valor pago pelos famintos é referente ao total
            //edt_total.setError("Se há famintos, informe o valor total da conta.");
            ls_msg = "Se há famintos, informe o valor total da conta.";
            edt_total.requestFocus();
            lb_erro = true;
        }
        if(!lb_erro && (li_bebados > 0 && li_famintos > 0)){
            //Se há bêbados e famintos, o valor total não deve ser menor que o valor gasto em bebidas
            if (ld_total <= ld_bebidas) {
                //edt_total.setError("Se há bêbados e famintos, o valor total deve ser superior ao valor gasto em bebidas.");
                ls_msg = "Se há bêbados e famintos, o valor total deve ser superior ao valor gasto em bebidas.";
                edt_total.requestFocus();
                lb_erro = true;
            }
        }
        if(!lb_erro && li_bebados == 0 && li_famintos == 0){
            ls_msg = "Se não há bêbados e não há famintos, por que calcular?";
            edt_bebados.requestFocus();
            lb_erro = true;
        }

        if(!lb_erro) {
            Slicer slicer = new Slicer(li_bebados, li_famintos, ld_total, ld_bebidas);

            slicer.slice();

            Intent results = new Intent(MainActivity.this, ResultsActivity.class);
            results.putExtra("result_bebados", slicer.getValorBebados());
            results.putExtra("result_famintos", slicer.getValorFamintos());
            results.putExtra("result_ambos", slicer.getValorAmbos());

            MainActivity.this.startActivity(results);
        } else {
            Toast.makeText(MainActivity.this, ls_msg, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/
        return true;
    }
}
