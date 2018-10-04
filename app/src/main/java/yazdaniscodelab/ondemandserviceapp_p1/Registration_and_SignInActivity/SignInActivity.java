package yazdaniscodelab.ondemandserviceapp_p1.Registration_and_SignInActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import yazdaniscodelab.ondemandserviceapp_p1.HomeActivity;
import yazdaniscodelab.ondemandserviceapp_p1.R;

public class SignInActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private EditText email;
    private EditText password;

    private Button btnSignIn;
    private TextView mregister_txt;

    //Firebase..

    private FirebaseAuth mAuth;

    //Dialog..

    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        toolbar=findViewById(R.id.toolbar_sign);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign In");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Firebase auh;

        mAuth=FirebaseAuth.getInstance();
        signInFunction();

        mDialog=new ProgressDialog(this);

    }

    private void signInFunction(){

        email=findViewById(R.id.email_signIn);
        password=findViewById(R.id.password_signIn);

        btnSignIn=findViewById(R.id.signIn_btn);
        mregister_txt=findViewById(R.id.register_txt);

        //Sign in button..

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mEmail=email.getText().toString().trim();
                String mPass=password.getText().toString().trim();

                if (TextUtils.isEmpty(mEmail)){
                    email.setError("Required Field..");
                    return;
                }

                if (TextUtils.isEmpty(mPass)){
                    password.setError("Required Field..");
                    return;
                }

                mDialog.setMessage("Processing...");
                mDialog.show();

                mAuth.signInWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            mDialog.dismiss();

                        }else {
                            Toast.makeText(getApplicationContext(),"Problem..",Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }

                    }
                });



            }
        });


        //Registration activity..
        mregister_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
            }
        });

    }


}
