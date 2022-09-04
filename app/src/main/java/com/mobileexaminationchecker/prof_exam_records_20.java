package com.mobileexaminationchecker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
public class prof_exam_records_20 extends AppCompatActivity {

    RecyclerView recyclerView1;
    ArrayList<User1> user1ArrayList;
    MyAdapter1 myAdapter1;
    FirebaseFirestore db;
    EditText search_edittxt;

    //firebase
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    StorageReference storageReference;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prof_exam_records_20);

        //firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = fAuth.getCurrentUser();

        search_edittxt = findViewById(R.id.search_edittxt);
        recyclerView1 = findViewById(R.id.recyclerview_exam_records_20);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        db= FirebaseFirestore.getInstance();
        user1ArrayList = new ArrayList<User1>();
        myAdapter1 = new MyAdapter1(prof_exam_records_20.this, user1ArrayList);

        recyclerView1.setAdapter(myAdapter1);

        EventChangeListener();

        search_edittxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());

            }
        });



    }


    private void filter(String text){
        ArrayList<User1> filteredList = new ArrayList<>();

        for(User1 item: user1ArrayList){

            if(item.getStudent_name().toLowerCase().contains(text.toLowerCase())){

                filteredList.add(item);

            }

        }

        myAdapter1.filterList(filteredList);





    }

    private void EventChangeListener() {

        DocumentReference documentReference = fStore.collection("exams_20").document(user.getUid());
        documentReference.addSnapshotListener(this,(documentSnapshot,e)->{


            String exam_code = documentSnapshot.getString("exam_code");

            if(documentSnapshot.exists()){

                db.collection(exam_code).orderBy("student_name", Query.Direction.ASCENDING)
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                                if(error != null){
                                    Log.e("Firestore error", error.getMessage());
                                    return;

                                }

                                for(DocumentChange dc : value.getDocumentChanges()){

                                    if(dc.getType() == DocumentChange.Type.ADDED){

                                        user1ArrayList.add(dc.getDocument().toObject(User1.class));
                                    }

                                    myAdapter1.notifyDataSetChanged();

                                }



                            }
                        });

            }
            else {

                Log.d("tag","Document do not exist");
            }
        });


    }
}