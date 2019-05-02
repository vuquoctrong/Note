package rekkisoft.trongvu.com.note.home;

import java.util.List;

import rekkisoft.trongvu.com.note.data.model.Note;
import rekkisoft.trongvu.com.note.data.repository.NoteRepository;

public class HomePresenter implements HomePresenterImp {
    private HomeViewImp view;
    private NoteRepository noteRepository;

    public HomePresenter(HomeViewImp homeViewImp) {
        view = homeViewImp;
        noteRepository = new NoteRepository();
    }

    @Override
    public List<Note> getAllNote() {
        List<Note> notes = noteRepository.getNotes();
        return notes;
    }

}
