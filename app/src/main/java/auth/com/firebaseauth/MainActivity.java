package auth.com.firebaseauth;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import auth.com.firebaseauth.databinding.ActivityMainBinding;
import viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mActivityMainBinding;
    MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainViewModel = new MainViewModel(FirebaseAuth.getInstance());
        mActivityMainBinding.setMainModel(mMainViewModel);
        setSignOutListener();

    }

    private void setSignOutListener() {

        mMainViewModel.getIsSignedOut().observe(this, this::isSignOut);
    }

    private void isSignOut(boolean isSignOut) {

        if (isSignOut) {

            Toast.makeText(this, "Sign Out Successfully", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Sign Out failed", Toast.LENGTH_LONG).show();
        }
    }
}
