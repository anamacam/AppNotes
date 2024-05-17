package com.example.appnotes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnotes.Interface.NotesCheckListener;
import com.example.appnotes.Model.Notes;
import com.example.appnotes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoteListAdapter extends RecyclerView.Adapter<NotesViewHolder> {

    Context context;
    List<Notes> notesList;
    NotesCheckListener notesCheckListener;

    public NoteListAdapter(Context context, List<Notes> notesList, NotesCheckListener notesCheckListener) {
        this.context = context;
        this.notesList = notesList;
        this.notesCheckListener = notesCheckListener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.note_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.titleTxt.setText(notesList.get(position).getTitle());
        holder.noteTxt.setText(notesList.get(position).getNotes());
        holder.dateTxt.setText(notesList.get(position).getDate());
        holder.dateTxt.setSelected(true);

        // Configuración de la imagen del pin
        if (notesList.get(position).getPinned()) {
            holder.imageView.setImageResource(R.drawable.pin_icon);
        } else {
            holder.imageView.setImageResource(0);
        }

        // Configuración del color de fondo
        int colorCode = getRandomColor();
        holder.cardView.setCardBackgroundColor(holder.itemView.getResources().getColor(colorCode, null));

        // Configuración de la acción de clic
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notesCheckListener.onClick(notesList.get(holder.getAdapterPosition()));
            }
        });

        // Configuración de la acción de clic prolongado
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                notesCheckListener.onLongPress(notesList.get(holder.getAdapterPosition()), holder.cardView);
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
        colorCode.add(R.color.color6);
        colorCode.add(R.color.color7);
        colorCode.add(R.color.color8);
        colorCode.add(R.color.color9);

        Random random = new Random();
        int randomColor = random.nextInt(colorCode.size());
        return colorCode.get(randomColor);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}

class NotesViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    CardView cardView;
    TextView noteTxt, titleTxt, dateTxt;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.note_container);
        noteTxt = itemView.findViewById(R.id.notesTxt);
        titleTxt = itemView.findViewById(R.id.titleTxt);
        dateTxt = itemView.findViewById(R.id.dateTxt);
    }
}
