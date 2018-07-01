package viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import Util.Util;

@SuppressLint("StaticFieldLeak")
public class LoginViewModel extends ViewModel {

    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    private String firebaseErrorMessage;
    private MutableLiveData<Boolean> isRegistrationSuccessful = new MutableLiveData<>();
    private FirebaseAuth mFirebaseAuth;


    public LoginViewModel(FirebaseAuth firebaseAuth) {

        mFirebaseAuth = firebaseAuth;
        userName.set("");
        password.set("");

    }

    public String getFirebaseErrorMessage() {
        return firebaseErrorMessage;
    }

    public void setFirebaseErrorMessage(String firebaseErrorMessage) {
        this.firebaseErrorMessage = firebaseErrorMessage;
    }

    public MutableLiveData<Boolean> getIsRegistrationSuccessful() {
        return isRegistrationSuccessful;
    }

    public void setIsRegistrationSuccessful(MutableLiveData<Boolean> isRegistrationSuccessful) {
        this.isRegistrationSuccessful = isRegistrationSuccessful;
    }

    public void onRegister(View view) {

        if (Util.isValidEmail(userName.get())) {


            mFirebaseAuth.createUserWithEmailAndPassword(userName.get(), password.get()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {
                        setFirebaseErrorMessage(task.getException().getMessage());
                        isRegistrationSuccessful.setValue(false);

                    } else {
                        isRegistrationSuccessful.setValue(true);
                    }

                }
            });
        } else {
            setFirebaseErrorMessage("Invalid Email");
            isRegistrationSuccessful.setValue(false);

        }
    }
}
