package rekkisoft.trongvu.com.note.detail;

import java.util.Date;
import java.util.List;

import rekkisoft.trongvu.com.note.data.model.Note;

public interface DetailPresenterImp {
    void showDialogBackground();

    void showDialogCamera();

    List<Note> getAllNote();

    void updateNote(Note note, String title, String content, Date createDate, boolean isAlarm, int color);

    void deleteNote(int noteId);
}
