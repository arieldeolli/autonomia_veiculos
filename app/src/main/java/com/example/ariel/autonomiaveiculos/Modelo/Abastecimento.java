package com.example.ariel.autonomiaveiculos.Modelo;


import android.os.Parcel;
import android.os.Parcelable;

public class Abastecimento implements Parcelable {
    private double km_atual;
    private double lts_abastecidos;
    private String dia_abastecimento;
    private String posto;


    public Abastecimento() {

    }

    protected Abastecimento(Parcel in) {
        setKm_atual(in.readFloat());
        setLts_abastecidos(in.readFloat());
        setDia_abastecimento(in.readString());
        setPosto(in.readString());
    }


    public static final Creator<Abastecimento> CREATOR = new Creator<Abastecimento>() {
        @Override
        public Abastecimento createFromParcel(Parcel in) {
            return new Abastecimento(in);
        }

        @Override
        public Abastecimento[] newArray(int size) {
            return new Abastecimento[size];
        }
    };

    public double getKm_atual() {
        return km_atual;
    }

    public void setKm_atual(Float km_atual) {
        this.km_atual = km_atual;
    }

    public double getLts_abastecidos() {
        return lts_abastecidos;
    }

    public void setLts_abastecidos(Float lts_abastecidos) {
        this.lts_abastecidos = lts_abastecidos;
    }

    public String getDia_abastecimento() {
        return dia_abastecimento;
    }

    public void setDia_abastecimento(String dia_abastecimento) {
        this.dia_abastecimento = dia_abastecimento;
    }


    public String getPosto() {
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(km_atual);
        dest.writeDouble(lts_abastecidos);
        dest.writeString(dia_abastecimento);
        dest.writeString(posto);
    }


}