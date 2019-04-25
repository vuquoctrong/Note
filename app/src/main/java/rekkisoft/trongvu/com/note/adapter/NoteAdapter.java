package rekkisoft.trongvu.com.note.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rekkisoft.trongvu.com.note.R;
import rekkisoft.trongvu.com.note.data.model.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private Context context;
    private List<Note> notes;
    private NoteOnclickListener noteOnclickListener;

    public NoteAdapter(Context contex) {
        this.context = context;
        this.notes = new ArrayList<>();

    }

    public void setNoteOnclickListener(NoteOnclickListener noteOnclickListener){
        this.noteOnclickListener = noteOnclickListener;
        notifyDataSetChanged();

    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view =  inflater.inflate(R.layout.itemnote_activity,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view,noteOnclickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder viewHolder, int i) {
        final Note note = notes.get(i);
        viewHolder.tv_title.setText(note.getTitle());
        viewHolder.tv_content.setText(note.getContent());
        viewHolder.tv_Date.setText((CharSequence) note.getCreateDate());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //FIXME: bỏ dấu _ ở tên, để là tvTitle
        TextView tv_title,tv_one,tv_content,tv_Date;
        ImageView iv_alarms;
        NoteOnclickListener noteOnclickListener;


        public ViewHolder(@NonNull View itemView, NoteOnclickListener noteOnclickListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_title = itemView.findViewById(R.id.tvTitle);
            tv_content = itemView.findViewById(R.id.tvContent);
            tv_Date = itemView.findViewById(R.id.tvDate);
            iv_alarms = itemView.findViewById(R.id.ivAlarms);
            this.noteOnclickListener =noteOnclickListener;


        }

        @Override
        public void onClick(View v) {
            noteOnclickListener.onClickItem(getAdapterPosition());
        }
    }
    public interface NoteOnclickListener{
        void onClickItem(int position);
    }
}
