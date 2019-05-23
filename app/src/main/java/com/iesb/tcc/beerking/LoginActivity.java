package com.iesb.tcc.beerking;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private String loginEmail, loginSenha;
    private EditText edtEmail, edtSenha;
    private Button btnLogin;

    private TextView txtCadastrar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializaComponentes();

        mAuth = FirebaseAuth.getInstance();





        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(LoginActivity.this, "Login: " + loginEmail + "\n Senha: " + loginSenha, Toast.LENGTH_LONG).show();
                loginEmail = edtEmail.getText().toString().trim();
                loginSenha = edtSenha.getText().toString().trim();

                Log.d("Login", "Login: " + loginEmail);
                Log.d("Login", "Senha: " + loginSenha);



                mAuth.signInWithEmailAndPassword(loginEmail, loginSenha)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Login", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                    Toast.makeText(LoginActivity.this, "Autenticação com Sucesso.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent main = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(main);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Login", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Autenticação Falhou",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });

        txtCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastrar2 = new Intent(LoginActivity.this, CadastrarActivity.class);
                startActivity(cadastrar2);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    private void inicializaComponentes(){
        edtEmail = (EditText) findViewById(R.id.login_email);
        edtSenha = (EditText) findViewById(R.id.login_senha);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        txtCadastrar = (TextView) findViewById(R.id.txtCadastrar);
    }



}
