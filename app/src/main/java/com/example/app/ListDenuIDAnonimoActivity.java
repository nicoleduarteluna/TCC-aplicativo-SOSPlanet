package com.example.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListDenuIDAnonimoActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    private FirebaseAuth mAuth;
    private RecyclerView mRecycleView;
    private FirebaseFirestore db;
    private TextView textEmail;
    private FirebaseUser user;
    private CardView cardView;

    private static final String TAG = "DocSnippets";
    private int selectedItem;
    String userID;
    CustomAdapterID adapter;
    ProgressDialog pd;

    List<ModelID> modelList = new ArrayList<>();
    // layout kmanger for recycleview
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_id_denu);

        mRecycleView = findViewById(R.id.recycler_view);
        textEmail = findViewById(R.id.textEmail);
        //set recycler view properties
        mRecycleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        //show data in recyclerview
        showData();
    }

    private void showData() {
        //set title of progress dialog
        userID = mAuth.getCurrentUser().getUid();
        db.collection("User").document(userID).collection("denuncias")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        for (DocumentSnapshot doc : task.getResult())
                        {
                            ModelID model = new ModelID(doc.getString("id"),
                                    doc.getString("titulo"),
                                    doc.getString("localizacao"),
                                    doc.getString("descricao"));

                            modelList.add(model);
                        }
                        adapter = new CustomAdapterID(modelList);
                        mRecycleView.setAdapter(adapter);
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseuser();
        verificaUser();
    }



    private void verificaUser() {
        if (user == null) {
            finish();
        } else {
            textEmail.setText("Usuário: " + user.getUid());
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
    {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fazerdenuncia, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent config = new Intent(getApplicationContext(), AnonimoActivity.class);
                startActivity(config);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 121:
                Intent finalizar = new Intent(getApplicationContext(), FinishAnonimoActivity.class);
                startActivity(finalizar);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
