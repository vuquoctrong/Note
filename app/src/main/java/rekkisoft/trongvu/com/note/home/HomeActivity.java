package rekkisoft.trongvu.com.note.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

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
        recyclerView = findViewById(R.id.rcNote);

        homePresenter = new HomePresenter(this);

        noteAdapter = new NoteAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(noteAdapter);

        notes = new ArrayList<>();
        notes = homePresenter.getAllNote();
        noteAdapter.setNotes(notes);

        noteAdapter.setNoteOnclickListener(this);
        btnNewNote.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNewNote:
                goToNewNote();
                break;
            default:
                break;
        }
    }

    private void goToNewNote() {
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
    public void onClickItem(int position) {
        openDetailOnHome(position);
    }

}
