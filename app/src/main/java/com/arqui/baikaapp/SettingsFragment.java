package com.arqui.baikaapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.settings, container, false);
        final EditText campoCir = (EditText) rootView.findViewById(R.id.size_value);
        campoCir.setText(MainActivity.Circunferencia.toString());
        Button btnSave = (Button) rootView.findViewById(R.id.guardar);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (campoCir.getText().toString().trim().length() == 0) {
                    Toast.makeText(getActivity(), "Debe ingresar una circunferencia", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        MainActivity.Circunferencia = Double.parseDouble(campoCir.getText().toString());
                        Toast.makeText(getActivity(), "La circunferencia se actualizó correctamente", Toast.LENGTH_LONG).show();
                    } catch (Exception ex) {
                        Toast.makeText(getActivity(), "Debe ingresar un dato válido", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return rootView;
    }

    public void onBackPressed() {
        getActivity().onBackPressed();

    }
}
