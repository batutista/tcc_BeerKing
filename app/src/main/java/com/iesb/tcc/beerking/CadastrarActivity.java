package com.iesb.tcc.beerking;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastrarActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText edtEmail, edtSenha;
    private Button btnCadastrar;

    private String cadEmail, cadSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        inicializarcomponentes();

        mAuth = FirebaseAuth.getInstance();


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadEmail = edtEmail.getText().toString().trim();
                cadSenha = edtSenha.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(cadEmail, cadSenha)
                        .addOnCompleteListener(CadastrarActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Cadastrar", "createUserWithEmail:success");
                                    Toast.makeText(CadastrarActivity.this, "Cadastro Sucedido!",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Cadastrar", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(CadastrarActivity.this, "Cadastro falhou!",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                                // ...
                            }
                        });

            }


        });
    }

    private void inicializarcomponentes() {
        edtEmail = (EditText)findViewById(R.id.edtCadEmail);
        edtSenha = (EditText) findViewById(R.id.edtCadSenha);

        btnCadastrar = (Button) findViewById(R.id.btnCadCadastrar);
    }

    private void updateUI(FirebaseUser user) {
    }


}
