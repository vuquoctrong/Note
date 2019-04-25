package rekkisoft.trongvu.com.note.new_note;

import rekkisoft.trongvu.com.note.data.model.Note;

public interface NewNotePresenterImp {
    void showDialogBackground();

    void showDialogCamera();

    void insertNote(Note note);
}
