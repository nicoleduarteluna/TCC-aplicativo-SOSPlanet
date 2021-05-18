package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

  private FirebaseUser currentUser;
  private FirebaseAuth mAuth;
  private Button btn, btnAnonimo;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mAuth = FirebaseAuth.getInstance();
    currentUser = mAuth.getCurrentUser();
    btn = findViewById(R.id.btnModoCadastro);
    btnAnonimo = findViewById(R.id.btnAnonimo);



    btnAnonimo.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        if (currentUser == null){
          FirebaseAuth.getInstance().signOut();
          mAuth.signInAnonymously().
                  addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                      if (task.isSuccessful())
                      {
                        Intent anonimo = new Intent(getApplicationContext(), AnonimoActivity.class);
                        startActivity(anonimo);
                      }
                    }
                  })
                  .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                      Log.e("TAG",e.getMessage());
                    }
                  });
        }
        else {
          FirebaseAuth.getInstance().signOut();
          mAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if (task.isSuccessful()) {
                Intent anonimo = new Intent(getApplicationContext(), AnonimoActivity.class);
                startActivity(anonimo);
              }
            }
          })
                  .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                      Log.e("TAG", e.getMessage());
                    }
                  });
        }
      }
    });

  }



  public void  proximaTela(View view) {
   // Intent intent = new Intent (this, Login.class);
    Intent intent = new Intent (this, Login.class);
    startActivity (intent);
  }

  public void  anonimo(View view) {
    Intent intent = new Intent (this, AnonimoActivity.class);
    //   Intent intent = new Intent (this, ListagemDenuncias.class);
    startActivity (intent);
  }

  public void sair(View view) {
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Conexao.logOut();
        Intent logout = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(logout);
      }
    });
  }


}