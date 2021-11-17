package com.knowtest.limovievoxwatch.helper;

import com.google.firebase.auth.FirebaseAuth;

public class FireBaseConfiguration {


    private static FirebaseAuth firebaseAuth;

    public static FirebaseAuth getFirebaseAuth() {
        if (firebaseAuth==null){
            firebaseAuth=FirebaseAuth.getInstance();
        }

        return firebaseAuth;
    }


}
