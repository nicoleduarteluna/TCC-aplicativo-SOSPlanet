package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.ComplaintActivity;
import com.example.app.Conexao;
import com.example.app.EvidenceActivity;
import com.example.app.ListDenuIDAnonimoActivity;
import com.example.app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AnonimoActivity extends AppCompatActivity {

    private EditText editTitle, editdescr;
    private Button btnInsertAnonimo,btnDados,btnEvidencia,btnLocalizacao, btnViewAnonimo;

    EditText editText;
    TextView textView1;

    private String id;
    private String UID;

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth auth;
    private FirebaseFirestore fstore;
    private FirebaseUser user;
    String userID;
    private static final String TAG = "DocSnippets";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anonimo);

        //inicialização do firebase
        fstore        = FirebaseFirestore.getInstance();
        mAuth         = FirebaseAuth.getInstance();

        editTitle = (EditText) findViewById(R.id.editTitle);
        editTitle.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        editdescr = (EditText) findViewById(R.id.editdescr);
        btnInsertAnonimo = (Button)  findViewById(R.id.btnInsert);
        btnViewAnonimo = findViewById(R.id.btnView);
        editText = findViewById(R.id.edit_text);
        textView1 = findViewById(R.id.text_view1);
        user = Conexao.getFirebaseuser();
        auth = FirebaseAuth.getInstance();

        btnInsertAnonimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterDenuncia();
                Intent minhasdenuncias = new Intent(getApplicationContext(), ListDenuIDAnonimoActivity.class);
                startActivity(minhasdenuncias);
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user== null) {
            auth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser user = auth.getCurrentUser();
                        updateUI(user);
                    } else {
                        updateUI(null);
                    }
                }
            });
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDados:
                Intent dados = new Intent(getApplicationContext(), ComplaintActivity.class);
                startActivity(dados);
                break;
            case R.id.btnEvidencia:
                Intent Evidencia = new Intent(getApplicationContext(), EvidenceActivity.class);
                startActivity(Evidencia);
                break;
            case R.id.btnView:
                Intent novo = new Intent(getApplicationContext(), ListDenuIDAnonimoActivity.class);
                startActivity(novo);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_anonimo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.MinhasDenuncias:
                Intent minhasdenuncias = new Intent(getApplicationContext(), ListDenuIDAnonimoActivity.class);
                startActivity(minhasdenuncias);
                break;

            case R.id.MenuList:
                Intent todasdenuncias = new Intent(getApplicationContext(), ListDenuActivity.class);
                startActivity(todasdenuncias);
                break;

            case R.id.fazerdenuncia:
                Intent fazerdenuncias = new Intent(getApplicationContext(), AnonimoActivity.class);
                startActivity(fazerdenuncias);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void RegisterDenuncia() {
        String titulo = editTitle.getText().toString();
        String descricao = editdescr.getText().toString();
        String localizacao = editText.getText().toString();
        if (titulo.isEmpty()|| descricao.isEmpty() || localizacao.isEmpty()) {
            Toast.makeText(AnonimoActivity.this, "Todos os campos devem ser preenchidos para efetuar a denúncia. TENTE NOVAMENTE", Toast.LENGTH_LONG).show();
        } else {
            userID = mAuth.getCurrentUser().getUid();
            id = UUID.randomUUID().toString();
            UID = editTitle.getText().toString();
            //    String Processo = "Em aberto";
            DocumentReference documentReference = fstore.collection("denuncias").document(UID);
            Map<String, Object> Dnc = new HashMap<>();
            Dnc.put("Id", id);
            //   Dnc.put("Processo", Processo);
            Dnc.put("titulo", editTitle.getText().toString());
            Dnc.put("descricao", editdescr.getText().toString());
            Dnc.put("localizacao", editText.getText().toString());
            documentReference.set(Dnc).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void Void) {
                    //  userID = mAuth.getCurrentUser().getUid();
                    //   userID = user.getUid();
                    userID = mAuth.getCurrentUser().getUid();
                    id = UUID.randomUUID().toString();
                    UID = editTitle.getText().toString();
                    //   String Processo = "Em aberto";

                    DocumentReference documentReference = fstore.collection("User").document(userID)
                            .collection("denuncias").document(UID);
                    Map<String, Object> DncUID = new HashMap<>();
                    DncUID.put("Id", id);
                    //     DncUID.put("Processo", Processo);
                    DncUID.put("titulo", editTitle.getText().toString());
                    DncUID.put("descricao", editdescr.getText().toString());
                    DncUID.put("localizacao", editText.getText().toString());
                    documentReference.set(DncUID).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void Void) {
                            //      String Processo = "Em aberto";
                            //    userID = mAuth.getCurrentUser().getUid();
                            //    userID = user.getUid();
                            userID = mAuth.getCurrentUser().getUid();
                            id = UUID.randomUUID().toString();
                            UID = editTitle.getText().toString();
                            DocumentReference documentReference = fstore.collection("Finalizadas").document(UID);
                            Map<String, Object> Finished = new HashMap<>();
                            Finished.put("Id", id);
                            //        Finished.put("Processo", Processo);
                            Finished.put("titulo", editTitle.getText().toString());
                            Finished.put("descricao", editdescr.getText().toString());
                            Finished.put("localizacao", editText.getText().toString());
                            documentReference.set(Finished).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void Void) {
                                    Toast.makeText(AnonimoActivity.this, "A denúncia foi realizada com sucesso. Clique para visualizar sua denúncia.", Toast.LENGTH_LONG).show();
                                    //   Intent novo = new Intent(getApplicationContext(), ListDenuIDAnonimoActivity.class);
                                    //   startActivity(novo);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AnonimoActivity.this, "Ocorreu um erro. Tente novamente", Toast.LENGTH_LONG).show();
                                    Intent Error = new Intent(getApplicationContext(), AnonimoActivity.class);
                                    startActivity(Error);
                                }
                            });

                        }

                    });
                }
            });
        }
    }
}
