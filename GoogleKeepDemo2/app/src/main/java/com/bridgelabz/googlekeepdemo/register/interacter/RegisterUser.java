package com.bridgelabz.googlekeepdemo.register.interacter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bridgelabz.googlekeepdemo.register.presenter.RegisterUserData;
import com.bridgelabz.googlekeepdemo.register.presenter.RegisterUserDataInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser implements RegisterUserInterface {

    Context context;
    RegisterUserDataInterface registerUserData;

    FirebaseFirestore mFirebaseFirestore;
    CollectionReference mRef;
    private FirebaseAuth mAuth;

    public RegisterUser(Context context, RegisterUserData registerUserData) {
        this.context = context;
        this.registerUserData = registerUserData;

    }

    /*
     *  In the Registration if the user is not presente in the database
     *  then the user is Redistricted otherwise the user entered in the database with the given info
     */

    @Override
    public void registerUser(final String first, final String last, String email, final String phone, String pass) {
        registerUserData.showProgressDialog("Signing In");

        mAuth = FirebaseAuth.getInstance();

        mFirebaseFirestore = FirebaseFirestore.getInstance();

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            String userId = mAuth.getCurrentUser().getUid();

                            mRef = mFirebaseFirestore.collection("User").document(userId).collection("UserInfo");

                            Map<String, Object> userInfo = new HashMap<>();

                            userInfo.put("First", first);
                            userInfo.put("Last", last);
                            userInfo.put("Phone", phone);

                            mRef.document(userId).set(userInfo);

                            registerUserData.registerSucces("Login Success");
                            registerUserData.hideProgressDialog();

                        } else {
                            registerUserData.registerUnsucces("Register Unsuccess");
                            registerUserData.hideProgressDialog();

                        }
                    }
                });
    }
}
