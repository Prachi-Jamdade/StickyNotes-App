package com.example.stickynotes;

import androidx.cardview.widget.CardView;

import com.example.stickynotes.Models.Notes;

public interface NotesClickListener {
    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);

}
