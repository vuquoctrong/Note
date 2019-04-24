package rekkisoft.trongvu.com.note.home;

import android.content.Intent;
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

public class HomeActivity extends AppCompatActivity implements View.OnClickListener
        , HomeViewImp, NoteAdapter.NoteOnclickListener {

    private Button btn_NewNote;
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
        btn_NewNote = findViewById(R.id.btnnewtab);
        btn_NewNote.setOnClickListener(this);
        homePresenter = new HomePresenter(this);
        recyclerView = findViewById(R.id.rcNote);
        noteAdapter = new NoteAdapter(this, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(noteAdapter);

        notes = new ArrayList<>();
        notes = homePresenter.getAllNote();
        notes.add(new Note("trong vu", "àasffaf"));
        notes.add(new Note("trong vu", "àasffaf"));
        notes.add(new Note("trong vu", "àasffaf"));
        notes.add(new Note("trong vu", "àasffaf"));
        notes.add(new Note("trong vu", "àasffaf"));
        notes.add(new Note("trong vu", "àasffaf"));

        noteAdapter.setNotes(notes);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnnewtab:
                homePresenter.goToHome();
                break;
            default:
                break;
        }
    }

    @Override
    public void goToHome() {
        Intent intent = new Intent(this, NewNoteActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_back, R.anim.anim_exit);
    }

    @Override
    public void openDetailOnHome(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("EXTRA_POSITION", position);
        startActivity(intent);
    }

    @Override
    public void onClickItem(int position) {
        homePresenter.openDetailOnHome(position);
    }

}
