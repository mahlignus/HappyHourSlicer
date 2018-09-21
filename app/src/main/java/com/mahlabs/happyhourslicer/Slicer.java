package com.mahlabs.happyhourslicer;

import java.text.DecimalFormat;

/**
 * Created by Mahlignus on 29/04/2016.
 */
public class Slicer {
    //Valores de uso interno
    private int ii_bebados, ii_famintos;
    private double idb_total, idb_bebidas;
    //Valores de retorno
    private double idb_div_famintos, idb_div_bebida, idb_vl_bebeu_comeu;
    //Format
    DecimalFormat formatter = new DecimalFormat("0.00");

    public Slicer(int p_bebados, int p_famintos, double p_total, double p_bebidas){
        ii_bebados = p_bebados;
        ii_famintos = p_famintos;
        idb_total = p_total;
        idb_bebidas = p_bebidas;
    }

    private void sliceBebados(){
        idb_div_famintos = 0.00;
        idb_vl_bebeu_comeu = 0.00;
        idb_div_bebida = idb_bebidas / ii_bebados;
    }

    private void sliceFamintos(){
        idb_div_bebida = 0.00;
        idb_vl_bebeu_comeu = 0.00;
        idb_div_famintos = idb_total / ii_famintos;
    }

    private void sliceAll(){
        idb_div_famintos = (idb_total - idb_bebidas) / ii_famintos;
        idb_div_bebida = idb_bebidas / ii_bebados;
        idb_vl_bebeu_comeu = idb_div_famintos + idb_div_bebida;
    }

    public void slice(){
        if(ii_bebados < 1){
            sliceFamintos();
        }else if(ii_famintos < 1){
            sliceBebados();
        }else{
            sliceAll();
        }
    }

    public String getValorBebados(){
        return formatter.format(idb_div_bebida);
    }

    public String getValorFamintos(){
        return formatter.format(idb_div_famintos);
    }

    public String getValorAmbos(){
        return formatter.format(idb_vl_bebeu_comeu);
    }
}
