package viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainViewModel extends ViewModel {

    public ObservableField<String> userId = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> displayName = new ObservableField<>();
    private MutableLiveData<Boolean> isSignedOut = new MutableLiveData<>();
    private FirebaseAuth mFirebaseAuth;


    public MutableLiveData<Boolean> getIsSignedOut() {
        return isSignedOut;
    }

    public void setIsSignedOut(MutableLiveData<Boolean> isSignedOut) {
        this.isSignedOut = isSignedOut;
    }

    public MainViewModel(FirebaseAuth firebaseAuth) {

        mFirebaseAuth = firebaseAuth;
        setUserDetails();
    }

    private void setUserDetails() {
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        userId.set(user.getUid());
        email.set(user.getEmail());
        displayName.set(user.getDisplayName());
    }

    public void logOut(View view) {

        mFirebaseAuth.signOut();
        isSignedOut.setValue(true);
    }
}
