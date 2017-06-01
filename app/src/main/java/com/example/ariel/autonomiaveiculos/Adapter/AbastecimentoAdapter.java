package com.example.ariel.autonomiaveiculos.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ariel.autonomiaveiculos.Modelo.Abastecimento;
import com.example.ariel.autonomiaveiculos.R;
//import com.example.ariel.autonomiaveiculos.R;

import java.util.ArrayList;



public class AbastecimentoAdapter extends RecyclerView.Adapter {

    private ArrayList<Abastecimento> listaAbastecimento;
    private Context context;

    public AbastecimentoAdapter(Context c, ArrayList<Abastecimento> a) {
        this.listaAbastecimento = a;
        this.context = c;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_abastecimento, parent, false);
        AbastecimentoViewHolder retorno = new AbastecimentoViewHolder(view);
        return retorno;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AbastecimentoViewHolder caixinha = (AbastecimentoViewHolder) holder;
        Abastecimento p = listaAbastecimento.get(position);
        caixinha.atualizarCard(p);
    }

    @Override
    public int getItemCount() {
        return this.listaAbastecimento.size();
    }

    public class AbastecimentoViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDia;

        private TextView tvQuilometros;
        private TextView tvLitros;
        private ImageView ivPostos;

        public AbastecimentoViewHolder(View itemView) {
            super(itemView);

            this.tvDia = (TextView) itemView.findViewById(R.id.tvDia);

            this.tvQuilometros = (TextView) itemView.findViewById(R.id.tvQuilometros);
            this.tvLitros = (TextView) itemView.findViewById(R.id.tvLitros);
            this.ivPostos = (ImageView) itemView.findViewById(R.id.ivPostos);

        }

        public void atualizarCard(Abastecimento cardMostrar) {
            tvDia.setText(String.valueOf(cardMostrar.getDia_abastecimento()));

            tvQuilometros.setText(Double.toString(cardMostrar.getKm_atual()));
            tvLitros.setText(Double.toString(cardMostrar.getLts_abastecidos()));

            switch (cardMostrar.getPosto()) {
                case "Texaco":
                    ivPostos.setImageResource(R.drawable.texaco);
                    break;
                case "Shell":
                    ivPostos.setImageResource(R.drawable.shell);
                    break;
                case "Petrobras":
                    ivPostos.setImageResource(R.drawable.petrobras);
                    break;
                case "Ipiranga":
                    ivPostos.setImageResource(R.drawable.ipiranga);
                    break;
                case "Outros":
                    ivPostos.setImageResource(R.drawable.outros);
                    break;
            }
        }



    }
}
