package yazdaniscodelab.ondemandserviceapp_p1.Registration_and_SignInActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import java.text.DateFormat;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import yazdaniscodelab.ondemandserviceapp_p1.HomeActivity;
import yazdaniscodelab.ondemandserviceapp_p1.Model.Users;
import yazdaniscodelab.ondemandserviceapp_p1.R;

public class RegistrationActivity extends AppCompatActivity {

    private Toolbar toolbar;

    //All Input Field..

    private EditText name;
    private EditText email;
    private EditText pass;
    private EditText phone;

    private Button btnRegister;
    private TextView txtSignIn;

    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mUserDatabase;

    //Progress dialog..
    private ProgressDialog mDialog;

    private String uId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //Toolbar
        toolbar=findViewById(R.id.toolbar_reg);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDialog=new ProgressDialog(this);

        //Firebase..

        mAuth=FirebaseAuth.getInstance();
        FirebaseUser mUser=mAuth.getCurrentUser();
        String uId=mUser.getUid();
        mUserDatabase= FirebaseDatabase.getInstance().getReference().child("UserList").child(uId);

        registrationFunction();


    }

    private void registrationFunction(){

        name=findViewById(R.id.name_reg);
        email=findViewById(R.id.email_reg);
        pass=findViewById(R.id.password_reg);
        phone=findViewById(R.id.phone_reg);

        btnRegister=findViewById(R.id.register_btn);
        txtSignIn=findViewById(R.id.sign_txt);


        //Registration Button..

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String mName=name.getText().toString().trim();
                final String mEmail=email.getText().toString().trim();
                final String mPassword=pass.getText().toString().trim();
                final String mPhone=phone.getText().toString().trim();

                if (TextUtils.isEmpty(mName)){
                    name.setError("Required Field..");
                }
                if (TextUtils.isEmpty(mEmail)){
                    email.setError("Required Field..");
                }
                if (TextUtils.isEmpty(mPassword)){
                    pass.setError("Required Field..");
                }
                if (TextUtils.isEmpty(mPhone)){
                    phone.setError("Required Field..");
                }

                mDialog.setMessage("Processing..");
                mDialog.show();

                mAuth.createUserWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            String mDate= DateFormat.getDateTimeInstance().format(new Date());

                            String id=mUserDatabase.push().getKey();

                            Users users=new Users(mName,mEmail,mPassword,mPhone,id,mDate);
                            mUserDatabase.child(id).setValue(users);
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            mDialog.dismiss();
                        }else {
                            Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }
                    }
                });




            }
        });


        //Go to sign in Activity..
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignInActivity.class));
            }
        });



    }


}
