package com.example.app;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Denuncia  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);

        new AlertDialog.Builder(this)
                .setTitle("Sua denúncia foi realizada com sucesso!")
                .setMessage("Ela será encaminhada para Órgãos responsáveis para a solução das mesmas. " +
                        "Clique em continuar para ver o status da sua e de outras denúncias.")
                .setPositiveButton("continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    } }).setNegativeButton("voltar", null) .show();
    }
}
