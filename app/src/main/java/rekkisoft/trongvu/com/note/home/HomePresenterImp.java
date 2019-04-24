package rekkisoft.trongvu.com.note.home;

import java.util.List;

import rekkisoft.trongvu.com.note.model.Note;

public interface HomePresenterImp {
    void goToHome();
    void apdateRecyclerView();
    void openDetailOnHome(int position);
    void insertNote(Note note);
    List<Note> getAllNote();
}
