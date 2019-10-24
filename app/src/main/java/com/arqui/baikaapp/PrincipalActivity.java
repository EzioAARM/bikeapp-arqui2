package com.arqui.baikaapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrincipalActivity extends Fragment {

    View rootView;
    IHttpRequest peticiones;
    TextView velocidad;
    TextView vueltas;
    TextView distancia;
    Button recargarDatos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.principal, container, false);
        velocidad = (TextView) rootView.findViewById(R.id.dato_velocidad);
        vueltas = (TextView) rootView.findViewById(R.id.dato_vueltas);
        distancia = (TextView) rootView.findViewById(R.id.dato_distancia);
        obtenerDatosMostrar();
        recargarDatos = (Button) rootView.findViewById(R.id.reload);
        recargarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerDatosMostrar();
            }
        });

        return rootView;
    }

    public void obtenerDatosMostrar() {
        String UrlServer = "https://poywhb3qv6.execute-api.us-east-1.amazonaws.com/testing/";
        Retrofit RetrofitClient = new Retrofit
                .Builder()
                .baseUrl(UrlServer)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Call<ObjetoRetorno> datosObtenidos = null;
        peticiones = RetrofitClient.create(IHttpRequest.class);
        datosObtenidos = peticiones.ObtenerDatos();
        datosObtenidos.enqueue(new Callback<ObjetoRetorno>() {
            @Override
            public void onResponse(Call<ObjetoRetorno> call, Response<ObjetoRetorno> response) {
                double distanciaTotal = (response.body().cantidadVueltas * MainActivity.Circunferencia);
                double totalVueltas = response.body().cantidadVueltas;
                double avgVelocidad = distanciaTotal * response.body().diferenciaTiempo;
                velocidad.setText(String.valueOf(avgVelocidad) + " m/s");
                vueltas.setText(String.valueOf(totalVueltas) + " vueltas");
                distancia.setText(String.valueOf(distanciaTotal) + " m");
            }

            @Override
            public void onFailure(Call<ObjetoRetorno> call, Throwable t) {
                Toast.makeText(getActivity(), "Hubo un error obteniendo datos", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    public static boolean verificarConexion(Context contexto) {
        ConnectivityManager manager = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager == null ? null : manager.getActiveNetworkInfo();
        return networkInfo == null ? false : networkInfo.isConnected();
    }

}
