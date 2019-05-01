package rekkisoft.trongvu.com.note.home;

import java.util.List;

import rekkisoft.trongvu.com.note.data.model.Note;

//FIXME tên interface không cần Imp
public interface HomePresenterImp {
    void goToHome();

    void openDetailOnHome(int position);

    void insertNote(Note note);

    List<Note> getAllNote();
}
