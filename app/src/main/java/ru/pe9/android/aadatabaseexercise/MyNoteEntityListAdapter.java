package ru.pe9.android.aadatabaseexercise;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class MyNoteEntityListAdapter extends RecyclerView.Adapter<MyNoteEntityListAdapter.MyNoteListItemHolder> {

    private DataBaseController dataBaseController = null;

    private View.OnClickListener internalOnClickListener = null;

    private RecyclerView myLastRecyclerView = null;

    @NonNull
    @Override
    public MyNoteListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.note_list_item_layout, parent, false);
        return new MyNoteListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyNoteListItemHolder holder, int position) {
        holder.bind(notes.get(position), internalOnClickListener);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    /////////////

    static class MyNoteListItemHolder extends  RecyclerView.ViewHolder{

        private TextView noteTitleView;
        private TextView noteBodyView;

        public MyNoteListItemHolder(View itemView) {
            super(itemView);

            noteTitleView = itemView.findViewById(R.id.noteCaptionTextView);
            noteBodyView = itemView.findViewById(R.id.noteBodyTextView);
        }

        public void bind(MyNoteEntity entity, View.OnClickListener onClickListener) {
            noteTitleView.setText(entity.getTitle());
            noteBodyView.setText(entity.getNoteBody());

            itemView.setTag(entity);
            itemView.setOnClickListener(onClickListener);
        }
    }

    private List<MyNoteEntity> notes;

    MyNoteEntityListAdapter(List<MyNoteEntity> notes, final DataBaseController dataBaseController) {
        this.notes = notes;
        this.dataBaseController = dataBaseController;

        internalOnClickListener  = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyNoteEntity entity = (MyNoteEntity) v.getTag();
                DeleteItem(entity);
            }
        };
    } // Constructior //

    private void DeleteItem(MyNoteEntity entity) {
        int position = notes.indexOf(entity);
        dataBaseController.Delete(entity);
        notes.remove(position);
        notifyItemRemoved(position);
    }

    public void AddItem(MyNoteEntity entity) {
        dataBaseController.Add(entity);
        int id = dataBaseController.getId(entity);
        entity.setUid(id);

//        notes.add(entity);
//        notifyItemInserted(notes.size()-1);
        notes.add(0, entity);
//        notifyDataSetChanged();
        notifyItemInserted(0);
        if (myLastRecyclerView != null) {
            myLastRecyclerView.scrollToPosition(0);
        }

    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        myLastRecyclerView = recyclerView;
    }


} // end of class //
