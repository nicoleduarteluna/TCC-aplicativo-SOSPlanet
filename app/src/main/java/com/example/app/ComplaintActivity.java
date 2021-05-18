package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ComplaintActivity extends AppCompatActivity {

    private EditText editTitle, editdescr;
    private Button btnInsert,btnDados,btnEvidencia,btnLocalizacao, btnVer;

    EditText editText;
    TextView textView1;

    Spinner tipo;
    private String id;
    private String UID;

    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;
    String userID;
    private static final String TAG = "DocSnippets";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        fstore        = FirebaseFirestore.getInstance();
        mAuth         = FirebaseAuth.getInstance();

        editTitle = (EditText) findViewById(R.id.editTitle);
        editTitle.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        editdescr = (EditText) findViewById(R.id.editdescr);
        btnInsert = (Button)   findViewById(R.id.btnInsert);
        tipo = findViewById(R.id.spinner1);
        btnVer = (Button) findViewById(R.id.btnView);
        editText = findViewById(R.id.edit_text);
        textView1 = findViewById(R.id.text_view1);


    }

    public void onClick(View view)
    {
        switch (view.getId()){
            case R.id.btnInsert:
                RegisterDenuncia();
                break;
            case  R.id.btnDados:
                Intent dados = new Intent(getApplicationContext(), ComplaintActivity.class);
                startActivity(dados);
                break;
            case R.id.btnEvidencia:
                Intent Evidencia = new Intent(getApplicationContext(), EvidenceActivity.class);
                startActivity(Evidencia);
                break;
            case  R.id.btnLocalizacao:
                Intent loc = new Intent(getApplicationContext(), LocationActivity.class);
                startActivity(loc);
                break;
            case R.id.btnView:
                Intent novo = new Intent(getApplicationContext(), ListDenuIDActivity.class);
                startActivity(novo);
                break;
        }
    }

    private void RegisterDenuncia() {
        String titulo = editTitle.getText().toString();
        String descricao = editdescr.getText().toString();
        String localizacao = editText.getText().toString();
        if (titulo.isEmpty()|| descricao.isEmpty() || localizacao.isEmpty()) {
            Toast.makeText(ComplaintActivity.this, "Todos os campos devem ser preenchidos para efetuar a denúncia. TENTE NOVAMENTE", Toast.LENGTH_LONG).show();
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
                                    Toast.makeText(ComplaintActivity.this, "A denúncia foi realizada com sucesso. Clique para visualizar sua denúncia.", Toast.LENGTH_LONG).show();
                                    //   Intent novo = new Intent(getApplicationContext(), ListDenuIDAnonimoActivity.class);
                                    //   startActivity(novo);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ComplaintActivity.this, "Ocorreu um erro. Tente novamente", Toast.LENGTH_LONG).show();
                                    Intent Error = new Intent(getApplicationContext(), ComplaintActivity.class);
                                    startActivity(Error);
                                }
                            });

                        }

                    });
                }
            });
        }
    }

    //create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.MenuConfig:
                Intent config = new Intent(getApplicationContext(), Perfil.class);
                startActivity(config);
                break;
            case R.id.MenuList:
                Intent List = new Intent(getApplicationContext(), ListDenuActivity.class);
                startActivity(List);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
