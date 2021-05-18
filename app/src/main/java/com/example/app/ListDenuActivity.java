package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListDenuActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    // Firebase
    private FirebaseAuth mAuth;
    private RecyclerView mRecycleView;
    private FirebaseFirestore db;

    private CardView cardView;

    private static final String TAG = "DocSnippets";
    private int selectedItem;
    String userID;
    CustomAdapter adapter;
    ProgressDialog pd;

    List<Model> modelList = new ArrayList<>();
    // layout kmanger for recycleview
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_denu);

        mRecycleView = findViewById(R.id.recycler_view);

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
        db.collection("denuncias")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot doc : task.getResult())
                {
                    Model model = new Model(doc.getString("id"),
                            doc.getString("titulo"),
                            doc.getString("localizacao"),
                            doc.getString("descricao"),
                            doc.getString("observacao"),
                            doc.getString("situacao"));

                        modelList.add(model);
                }
                adapter = new CustomAdapter(modelList);
                mRecycleView.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
    {
        return false;
    }



}
