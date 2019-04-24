package rekkisoft.trongvu.com.note.home;

import java.util.List;

import rekkisoft.trongvu.com.note.model.Note;
import rekkisoft.trongvu.com.note.model.RealmManager;

public class HomePresenter implements HomePresenterImp {
    private HomeViewImp view;
    public HomePresenter(HomeViewImp homeViewImp) {
        view = homeViewImp;
    }

    @Override
    public void goToHome() {
        view.goToHome();
    }

    @Override
    public void apdateRecyclerView() {
        view.apdateRecyclerView();
    }

    @Override
    public void openDetailOnHome(int position) {
        view.openDetailOnHome(position);

    }
    @Override
    public void insertNote(Note note){
        RealmManager.getInstance().insertNote(note);
    }

    @Override
    public List<Note> getAllNote() {
       List<Note> notes = RealmManager.getInstance().getNotes();
       return  notes;
    }

}
