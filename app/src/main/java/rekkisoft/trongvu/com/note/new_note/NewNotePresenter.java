package rekkisoft.trongvu.com.note.new_note;

import rekkisoft.trongvu.com.note.data.model.Note;
import rekkisoft.trongvu.com.note.data.repository.NoteRepository;

public class NewNotePresenter implements NewNotePresenterImp {
    private NewNoteViewImp mView;
    private NoteRepository noteRepository;

    public NewNotePresenter(NewNoteViewImp newNoteViewImp) {
        this.mView = newNoteViewImp;
        noteRepository = new NoteRepository();
    }

    @Override
    public void showDialogBackground() {
        mView.showDialogBackground();
    }

    @Override
    public void showDialogCamera() {
        mView.showDialogCamera();
    }

    @Override
    public void insertNote(Note note) {
        if (note != null) {
            noteRepository.insertNote(note);
            mView.backHome();
        }
    }
}
