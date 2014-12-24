package com.lncramer.secretsanta.services;

import com.firebase.client.Firebase;
import com.lncramer.secretsanta.services.IAddName;

/**
 * Created by Lucas on 12/22/2014.
 */
public class NameAdder implements IAddName {
    @Override
    public void addName(String name) {
        Firebase ref = new Firebase("https://brilliant-torch-7054.firebaseio.com/");
        Firebase namesRef = ref.child("names");
        namesRef.push().setValue(name);
    }
}
