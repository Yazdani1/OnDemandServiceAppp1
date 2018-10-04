package yazdaniscodelab.ondemandserviceapp_p1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import yazdaniscodelab.ondemandserviceapp_p1.Registration_and_SignInActivity.RegistrationActivity;
import yazdaniscodelab.ondemandserviceapp_p1.Registration_and_SignInActivity.SignInActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnRegister;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister=findViewById(R.id.register);
        btnSignIn=findViewById(R.id.sign_in);

        //Register button...
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            }
        });

        //Sign in button.

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));

            }
        });


    }
}
