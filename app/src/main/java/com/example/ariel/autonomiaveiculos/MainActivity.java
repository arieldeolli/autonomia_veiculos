package com.example.ariel.autonomiaveiculos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariel.autonomiaveiculos.DAO.RegistroDAO;
import com.example.ariel.autonomiaveiculos.Modelo.Abastecimento;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity {

    private TextView tvValorQuilometragem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvValorQuilometragem = (TextView) findViewById(R.id.tvValorQuilometragem);

        ArrayList <Abastecimento> lista = RegistroDAO.obterLista();

        if(lista.size() > 1) {
            double autonomia = ((lista.get(0).getKm_atual() - lista.get(1).getKm_atual()) / lista.get(0).getLts_abastecidos());
            autonomia = Double.valueOf(String.format("%.3f", autonomia));
            tvValorQuilometragem.setText(autonomia + "");
        }else{
            tvValorQuilometragem.setText("0");
        }
    }

    public void botaoAddAbastecimento(View origemDoClique){
        Intent abridor = new Intent(this.getApplicationContext(), RegistroQuilometragem.class);
        startActivity(abridor);
    }

    public void botaoViewAbastecimento(View origemDoClique){
        Intent abridor = new Intent(this.getApplicationContext(), ViewAbastecimento.class);
        startActivity(abridor);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
