package rekkisoft.trongvu.com.note.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rekkisoft.trongvu.com.note.R;
import rekkisoft.trongvu.com.note.adapter.NoteAdapter;
import rekkisoft.trongvu.com.note.data.model.Note;
import rekkisoft.trongvu.com.note.detail.DetailActivity;
import rekkisoft.trongvu.com.note.new_note.NewNoteActivity;
import rekkisoft.trongvu.com.note.utils.Define;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener
        , HomeViewImp, NoteAdapter.NoteOnclickListener {

    public static final String EXTRA_POSITION = "EXTRA_POSITION";
    private Button btnNewNote;
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private List<Note> notes;
    private HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init() {
        btnNewNote = findViewById(R.id.btnNewNote);
        btnNewNote.setOnClickListener(this);
        homePresenter = new HomePresenter(this);
        recyclerView = findViewById(R.id.rcNote);
        noteAdapter = new NoteAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(noteAdapter);

        notes = new ArrayList<>();
        notes = homePresenter.getAllNote();
        noteAdapter.setNotes(notes);
        noteAdapter.setNoteOnclickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNewNote:
                homePresenter.goToHome();
                break;
            default:
                break;
        }
    }

    @Override
    public void goToNewNote() {
        Intent intent = new Intent(this, NewNoteActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_back, R.anim.anim_exit);
    }

    @Override
    public void openDetailOnHome(int noteId) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Define.NavigationKey.NOTE_ID, noteId);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClickItem(int noteId) {
        openDetailOnHome(noteId);
    }

}
