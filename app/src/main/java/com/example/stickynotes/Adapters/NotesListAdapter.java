package com.example.stickynotes.Adapters;

import android.content.Context;
import android.media.tv.TvTrackInfo;
import android.os.Build;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stickynotes.Models.Notes;
import com.example.stickynotes.NotesClickListener;
import com.example.stickynotes.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder> {

    Context context;
    List<Notes> notes;
    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Notes> notes, NotesClickListener listener) {
        this.context = context;
        this.notes = notes;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_item, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull @NotNull NotesViewHolder holder, int position) {
        holder.TVTitle.setText(notes.get(position).getTitle());
        holder.TVTitle.setSelected(true);

        holder.TVNotes.setText(notes.get(position).getNotes());
        holder.TVDate.setText(notes.get(position).getDate());
        holder.TVDate.setSelected(true);

        if(notes.get(position).isPinned()) {
            holder.IVPin.setImageResource(R.drawable.ic_pin);
        }
        else {
            holder.IVPin.setImageResource(0);
        }

        int colorCode = getRandomColor();
        holder.CVNotes.setCardBackgroundColor(holder.itemView.getResources().getColor(colorCode, null));

        holder.CVNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(notes.get(holder.getAdapterPosition()));
            }
        });

        holder.CVNotes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(notes.get(holder.getAdapterPosition()), holder.CVNotes);
                return true;
            }
        });
    }

    private int getRandomColor() {
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);
        colorCode.add(R.color.color5);

        Random random = new Random();
        int randomCol = random.nextInt(colorCode.size());
        return colorCode.get(randomCol);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}

class NotesViewHolder extends RecyclerView.ViewHolder {

    CardView CVNotes;
    TextView TVTitle, TVNotes, TVDate;
    ImageView IVPin;


    public NotesViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
        super(itemView);

        CVNotes = itemView.findViewById(R.id.CVNotes);
        TVTitle = itemView.findViewById(R.id.TVTitle);
        TVNotes = itemView.findViewById(R.id.TVNotes);
        TVDate = itemView.findViewById(R.id.TVDate);
        IVPin = itemView.findViewById(R.id.IVPin);
    }
}
