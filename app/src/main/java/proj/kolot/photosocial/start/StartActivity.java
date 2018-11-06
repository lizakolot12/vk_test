package proj.kolot.photosocial.start;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import proj.kolot.photosocial.R;

import static proj.kolot.photosocial.R.id.fragment;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StartFragment startFragment =
                (StartFragment) getSupportFragmentManager().findFragmentById(fragment);
        if (startFragment == null) {
            // Create the fragment
            startFragment = new StartFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment, startFragment);
            transaction.commit();
        }

        // Create the presenter
        StartPresenter startPresenter = new StartPresenter(startFragment);
    }
}
