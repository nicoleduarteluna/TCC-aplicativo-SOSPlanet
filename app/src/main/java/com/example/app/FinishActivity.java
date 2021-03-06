package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class FinishActivity extends AppCompatActivity {

    private Button btnFinish;
    private TextView txtID;

    private String userID;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String UUIDS;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_finish_denu);

        btnFinish = findViewById(R.id.btnFinish);
        txtID = findViewById(R.id.txtID);
        txtID.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFinish:
                deleteItem();
                break;

            case R.id.btnVoltar:
                Intent voltar = new Intent(getApplicationContext(), ListDenuIDActivity.class);
                startActivity(voltar);
                break;
        }
    }

    public void deleteItem() {
        UUIDS = txtID.getText().toString();
        userID = mAuth.getCurrentUser().getUid();
        db.collection("User").document(userID)
                .collection("denuncias").document(UUIDS)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        UUIDS = txtID.getText().toString();
                        userID = mAuth.getCurrentUser().getUid();
                        db.collection("denuncias").document(UUIDS)
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(FinishActivity.this, "Finalizado com sucesso!", Toast.LENGTH_SHORT).show();
                                        Intent voltar = new Intent(getApplicationContext(), ListDenuIDActivity.class);
                                        startActivity(voltar);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(FinishActivity.this, "N??o foi poss??vel finalizar a den??ncia! Tente novamente.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }
}
