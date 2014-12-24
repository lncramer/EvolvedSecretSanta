package com.lncramer.secretsanta.services;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 12/22/2014.
 */
public class NameRetriever implements IRetrieveNames {
    private ArrayList<String> _names;

    public NameRetriever() {
        _names = new ArrayList<>();
    }
    @Override
    public void setupNameListener() {
        // Get a reference to names
        Firebase ref = new Firebase("https://brilliant-torch-7054.firebaseio.com/names");

        // Attach a listener to read the data at reference
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                String newName = snapshot.getValue().toString();
                _names.add(newName);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public ArrayList<String> getNames() {
        return _names;
    }
}
