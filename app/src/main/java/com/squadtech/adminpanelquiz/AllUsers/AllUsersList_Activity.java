package com.squadtech.adminpanelquiz.AllUsers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.service.autofill.Dataset;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squadtech.adminpanelquiz.R;

import java.util.ArrayList;

public class AllUsersList_Activity extends AppCompatActivity
{
    private RecyclerView allusers_rv;
    private ArrayList<Alluserslist_Model> arrayList;
    private AllUsersListAdapter adapter_users;
    DatabaseReference dbReference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users_list_);

        Toolbar toolbar = findViewById(R.id.all_users_list_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ALL USERS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference().child("Users");

//        EditText search_users = findViewById(R.id.search_users);
//        search_users.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                searchUsers(charSequence.toString().toLowerCase());
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

        allusers_rv = findViewById(R.id.all_users_RV);
        allusers_rv.hasFixedSize();
        allusers_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        arrayList = new ArrayList<>();

        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                arrayList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Alluserslist_Model model = ds.getValue(Alluserslist_Model.class);
                    arrayList.add(model);
                }
                adapter_users = new AllUsersListAdapter(getApplicationContext(), arrayList);
                allusers_rv.setAdapter(adapter_users);
                adapter_users.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

//    private void searchUsers(String a)
//    {
//        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        Query query = FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("search").startAt(a).endAt(a+"\uf8ff");
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                arrayList.clear();
//                for(DataSnapshot ds : dataSnapshot.getChildren())
//                {
//                    Alluserslist_Model model = ds.getValue(Alluserslist_Model.class);
//                    assert model != null;
//                    assert firebaseUser != null;
//                    if(!model.getId().equals(firebaseUser.getUid()))
//                    {
//                        arrayList.add(model);
//                    }
//                }
//                adapter_users = new AllUsersListAdapter(getApplicationContext(), arrayList);
//                allusers_rv.setAdapter(adapter_users);
//                adapter_users.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
}
