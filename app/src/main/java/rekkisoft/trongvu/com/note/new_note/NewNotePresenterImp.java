package rekkisoft.trongvu.com.note.new_note;

import java.util.List;

import rekkisoft.trongvu.com.note.data.model.Note;

public interface NewNotePresenterImp {
    void insertNote(Note note);

    void addImageNote(Note note, List<String> uRlImage);
}
