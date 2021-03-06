package rekkisoft.trongvu.com.note.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rekkisoft.trongvu.com.note.R;
import rekkisoft.trongvu.com.note.data.model.Note;
import rekkisoft.trongvu.com.note.utils.Utility;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> notes;
    private NoteOnclickListener noteOnclickListener;

    public NoteAdapter() {
        this.notes = new ArrayList<>();

    }

    public void setNoteOnclickListener(NoteOnclickListener noteOnclickListener){
        this.noteOnclickListener = noteOnclickListener;

    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view =  inflater.inflate(R.layout.itemnote_activity,viewGroup,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder viewHolder, int i) {
        final Note note = notes.get(i);
        final int position = i;
        viewHolder.tvTitle.setText(note.getTitle());
        viewHolder.tvContent.setText(note.getContent());
        viewHolder.tvDate.setText(Utility.partDateToString(note.getCreateDate()));
        viewHolder.rlColorItem.setBackgroundColor(note.getColor());
        viewHolder.cvNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteOnclickListener.onClickItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvContent;
        TextView tvDate;
        ImageView ivAlarms;
        RelativeLayout rlColorItem;
        CardView cvNote;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvDate = itemView.findViewById(R.id.tvDate);
            ivAlarms = itemView.findViewById(R.id.ivAlarms);
            rlColorItem = itemView.findViewById(R.id.rlColorItem);
            cvNote = itemView.findViewById(R.id.cvNote);
        }

    }

    public interface NoteOnclickListener{
        void onClickItem(int noteId);
    }
}
