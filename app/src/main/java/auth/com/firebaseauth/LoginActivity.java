package auth.com.firebaseauth;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import auth.com.firebaseauth.databinding.ActivityLoginBinding;
import viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding mLoginBinding;
    LoginViewModel mLoginViewModel;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mLoginViewModel = new LoginViewModel(mFirebaseAuth);
        mLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mLoginBinding.setViewModel(mLoginViewModel);

        setLoginObserver();

    }

    private void setLoginObserver() {

        mLoginViewModel.getIsRegistrationSuccessful().observe(this, this::isRegistrationSuccessful);
    }

    private void isRegistrationSuccessful(boolean isSuccessful) {

        if (isSuccessful) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Authentication Failed: " + mLoginViewModel.getFirebaseErrorMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
