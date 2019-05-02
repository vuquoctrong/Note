package rekkisoft.trongvu.com.note.detail;

import java.util.Date;
import java.util.List;

import rekkisoft.trongvu.com.note.data.model.Note;
import rekkisoft.trongvu.com.note.data.repository.NoteRepository;

public class DetailPresenter implements DetailPresenterImp {
    private DetailViewImp detailViewImp;
    private NoteRepository noteRepository;

    public DetailPresenter(DetailViewImp detailViewImp) {
        this.detailViewImp = detailViewImp;
        noteRepository = new NoteRepository();

    }

    @Override
    public List<Note> getAllNote() {
        List<Note> notes = noteRepository.getNotes();
        return notes;
    }

    @Override
    public void updateNote(Note note, String title, String content, Date createDate, boolean isAlarm, int color) {
        noteRepository.updateNote(note, title, content, createDate, isAlarm, color);

    }

    @Override
    public void deleteNote(int noteId) {
        noteRepository.deleteNote(noteId);
    }

    @Override
    public void addImageNote(Note note, List<String> uRlImage) {
        noteRepository.addImageNote(note,uRlImage);
    }

    @Override
    public void removeImageNote(Note note, int positon) {
        noteRepository.removeImageNote(note,positon);
    }


}
