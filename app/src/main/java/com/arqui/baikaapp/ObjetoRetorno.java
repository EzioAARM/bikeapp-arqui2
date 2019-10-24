package com.arqui.baikaapp;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class ObjetoRetorno {

    @SerializedName("vueltas")
    public int cantidadVueltas;

    @SerializedName("timeDiff")
    public double diferenciaTiempo;

    @SerializedName("info")
    public String info;

    public ObjetoRetorno(int vueltas, int tiempo, String dato) {
        cantidadVueltas = vueltas;
        diferenciaTiempo = tiempo;
        info = dato;
    }

    public int getCantidadVueltas() {
        return cantidadVueltas;
    }

    public double getDiferenciaTiempo() {
        return diferenciaTiempo;
    }

    public String getInfo() {
        return info;
    }

    public void setCantidadVueltas(int cantidadVueltas) {
        this.cantidadVueltas = cantidadVueltas;
    }

    public void setDiferenciaTiempo(double diferenciaTiempo) {
        this.diferenciaTiempo = diferenciaTiempo;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
