package com.bridgelabz.googlekeepdemo.note.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgelabz.googlekeepdemo.R;
import com.bridgelabz.googlekeepdemo.addNote.AddNote;
import com.bridgelabz.googlekeepdemo.base.BaseFragment;
import com.bridgelabz.googlekeepdemo.note.adapter.noteAdapter;
import com.bridgelabz.googlekeepdemo.note.model.noteData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NoteFragment extends BaseFragment implements View.OnClickListener{

    View v;
    RecyclerView mNoteRecycler;

    ArrayList<noteData> noteDataList;
    noteData noteData;
    noteAdapter noteAdapter;

    CollectionReference myDoc;

    TextView writeNote;
    ImageView choiceNote, paintNote, voiceNote, capturenote;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_note, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v = view;

        String userID = FirebaseAuth.getInstance().getUid();

        myDoc = FirebaseFirestore.getInstance().collection("Notes").document(userID).collection("Note");

        initView();
        clickListning();

        getAllDataToDisplay();
    }

    private void getAllDataToDisplay() {

        myDoc.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                noteDataList.clear();

                if (documentSnapshots != null) {

                    for (DocumentSnapshot documents : documentSnapshots.getDocuments()) {

                        noteData = documents.toObject(noteData.class);

                        if (noteData.getLocation().equals("note")) {

                            noteDataList.add(noteData);

                        }
                        noteAdapter = new noteAdapter(noteDataList);
                        mNoteRecycler.setAdapter(noteAdapter);
                        noteAdapter.notifyDataSetChanged();
                    }
                }
                mNoteRecycler.invalidate();
            }
        });
    }

    @Override
    public void initView() {

        mNoteRecycler = (RecyclerView) v.findViewById(R.id.pinNotes);
        mNoteRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNoteRecycler.setHasFixedSize(true);

        noteDataList = new ArrayList<>();

        writeNote = (TextView) v.findViewById(R.id.writeNote);
        choiceNote = (ImageView) v.findViewById(R.id.writeChoiceNote);
        paintNote = (ImageView) v.findViewById(R.id.drawNote);
        voiceNote = (ImageView) v.findViewById(R.id.voiceNote);
        capturenote = (ImageView) v.findViewById(R.id.captureNote);
    }

    @Override
    public void clickListning() {

        writeNote.setOnClickListener(this);
        choiceNote.setOnClickListener(this);
        paintNote.setOnClickListener(this);
        voiceNote.setOnClickListener(this);
        capturenote.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.writeNote :
                Intent intent = new Intent(view.getContext(), AddNote.class);
                startActivity(intent);
                break;

            case R.id.writeChoiceNote :
                Toast.makeText(getActivity(), "writeChoiceNote", Toast.LENGTH_SHORT).show();
                break;

            case R.id.drawNote :
                Toast.makeText(getActivity(), "drawNote", Toast.LENGTH_SHORT).show();
                break;

            case R.id.voiceNote :
                Toast.makeText(getActivity(), "voiceNote", Toast.LENGTH_SHORT).show();
                break;

            case R.id.captureNote :
                Toast.makeText(getActivity(), "captureNote", Toast.LENGTH_SHORT).show();
                break;


        }

    }
}
