package com.example.ariel.autonomiaveiculos;

import android.app.Activity;
import android.app.FragmentTransaction;import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ariel.autonomiaveiculos.DAO.RegistroDAO;
import com.example.ariel.autonomiaveiculos.Modelo.Abastecimento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RegistroQuilometragem extends Activity implements AdapterView.OnItemSelectedListener{

    private Spinner spPostos;
    private EditText etKmAtual;
    private EditText etLtsAbastecidos;
    private EditText etDia;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_quilometragem);


        spPostos = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.postos_array, android.R.layout.simple_spinner_item);
        spPostos.setAdapter(adapter);

        etKmAtual = (EditText) findViewById(R.id.etKmAtual);
        etLtsAbastecidos = (EditText) findViewById(R.id.etLtsAbastecidos);
        etDia = (EditText) findViewById(R.id.etDia);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }

    public void onStart(){
        super.onStart();
        etDia.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    DateDialog dialog = new DateDialog(view);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft,"DatePicker");
                }
            }
        });
    }

    private boolean registroValido() {
        ArrayList<Abastecimento> lista = RegistroDAO.obterLista();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (etKmAtual.getText().toString().trim().isEmpty()) {
            return false;
        }
        if (etLtsAbastecidos.getText().toString().trim().isEmpty()) {
            return false;
        }
        if (etDia.getText().toString().trim().isEmpty()) {
            return false;
        } else {
            try {
                Date novo = dateFormat.parse(etDia.getText().toString());
                if (novo.after(new Date())) {
                    return false;
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (Float.parseFloat(etLtsAbastecidos.getText().toString()) <= 0) {
            return false;
        }

        if (!lista.isEmpty()) {
            if (Double.parseDouble(etKmAtual.getText().toString()) <= lista.get(0).getKm_atual() || etKmAtual.getText().toString().trim().isEmpty()) {
                return false;
            }

            try {
                Date novo = dateFormat.parse(etDia.getText().toString());
                Date antigo = dateFormat.parse(lista.get(0).getDia_abastecimento());
                if (novo.before(antigo)) {
                    return false;
                } else {
                    if (novo.after(new Date())) {
                        return false;
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public void clicouNoBotao(View origemDoClique){
        if(registroValido() == false){
            Toast.makeText(this.getApplicationContext(), "Erro no registro", Toast.LENGTH_SHORT).show();
        }else{
            Abastecimento a = new Abastecimento();
            a.setKm_atual(Float.parseFloat(etKmAtual.getText().toString()));
            a.setLts_abastecidos(Float.parseFloat(etLtsAbastecidos.getText().toString()));
            a.setDia_abastecimento(etDia.getText().toString());
            a.setPosto(spPostos.getSelectedItem().toString());
            RegistroDAO.salvar(a);
            Toast.makeText(this.getApplicationContext(), "Abastecimento registrado", Toast.LENGTH_SHORT).show();

            Intent abridor = new Intent(this.getApplicationContext(), MainActivity.class);
            startActivity(abridor);
        }
    }

    public void onBackPressed() {
        Intent abridor = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(abridor);
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro_quilometragem, menu);
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
