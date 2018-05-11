package ru.pe9.android.aadatabaseexercise;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class MyNoteEntityListAdapter extends RecyclerView.Adapter<MyNoteEntityListAdapter.MyNoteListItemHolder> {

    @NonNull
    @Override
    public MyNoteListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.note_list_item_layout, parent, false);
        return new MyNoteListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyNoteListItemHolder holder, int position) {
        MyNoteEntity note = notes.get(position);
        holder.noteTitleView.setText(note.getTitle());
        holder.noteBodyView.setText(note.getNoteBody());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    /////////////

    static class MyNoteListItemHolder extends  RecyclerView.ViewHolder{

        public TextView noteTitleView;
        public TextView noteBodyView;

        public MyNoteListItemHolder(View itemView) {
            super(itemView);

            noteTitleView = itemView.findViewById(R.id.noteCaptionTextView);
            noteBodyView = itemView.findViewById(R.id.noteBodyTextView);
        }
    }

    private final List<MyNoteEntity> notes;

    MyNoteEntityListAdapter(List<MyNoteEntity> notes) {
        this.notes = notes;
    }

}
