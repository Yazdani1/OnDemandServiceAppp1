package yazdaniscodelab.ondemandserviceapp_p1;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar=findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("OnDemand Service App");

        bottomNavigationView=findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.alljob:

                        bottomNavigationView.setItemBackgroundResource(R.color.job);
                        return true;

                    case R.id.services:
                        bottomNavigationView.setItemBackgroundResource(R.color.services);
                        return true;

                    case R.id.account:
                        bottomNavigationView.setItemBackgroundResource(R.color.account);
                        return true;

                        default:
                            return false;
                }

            }
        });


    }
}
