package com.example.stickynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.stickynotes.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakingActivity extends AppCompatActivity {

    EditText etTitle, etNotes;
    ImageView ivSave;
    Notes notes;
    boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taking);

        ivSave = findViewById(R.id.IVSave);
        etTitle = findViewById(R.id.ETTitle);
        etNotes = findViewById(R.id.ETNotes);

        notes = new Notes();
        try {
            notes = (Notes) getIntent().getSerializableExtra("old_note");
            etTitle.setText(notes.getTitle());
            etNotes.setText(notes.getNotes());
            isOldNote = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        ivSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String desc = etNotes.getText().toString();

                if(desc.isEmpty()) {
                    Toast.makeText(NotesTakingActivity.this, "Please Add Some Notes", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM  yyyy HH:mm a");
                Date date = new Date();

                if(!isOldNote) {
                    notes = new Notes();
                }

                notes.setTitle(title);
                notes.setNotes(desc);
                notes.setDate(formatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("note", notes);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}