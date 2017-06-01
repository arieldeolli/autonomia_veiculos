package com.example.ariel.autonomiaveiculos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ariel.autonomiaveiculos.Adapter.AbastecimentoAdapter;
import com.example.ariel.autonomiaveiculos.DAO.RegistroDAO;
import com.example.ariel.autonomiaveiculos.Modelo.Abastecimento;

import java.util.ArrayList;

public class ViewAbastecimento extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_abastecimento);

        ArrayList<Abastecimento> lista = RegistroDAO.obterLista();

        RecyclerView rvListaAbastecimento = (RecyclerView) findViewById(R.id.rvListaAbastecimento);
        RecyclerView.LayoutManager formaApresentacao = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvListaAbastecimento.setLayoutManager(formaApresentacao);
        AbastecimentoAdapter adaptador = new AbastecimentoAdapter(this.getApplicationContext(), lista);
        rvListaAbastecimento.setAdapter(adaptador);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onBackPressed() {
        Intent abridor = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(abridor);
        super.onBackPressed();
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
