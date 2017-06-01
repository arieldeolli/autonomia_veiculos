package com.example.ariel.autonomiaveiculos.DAO;

import com.example.ariel.autonomiaveiculos.Modelo.Abastecimento;

import java.util.ArrayList;

public class RegistroDAO {

    private static ArrayList <Abastecimento> listaAbastecimentos;

    private static void inicializarLista(){
        if (RegistroDAO.listaAbastecimentos == null){
            RegistroDAO.listaAbastecimentos = new ArrayList<>();
        }
    }

    public static void salvar (Abastecimento abastecimento){
        inicializarLista();
        listaAbastecimentos.add(0, abastecimento);
    }

    public static ArrayList<Abastecimento> obterLista(){
        inicializarLista();
        return listaAbastecimentos;
    }

}
