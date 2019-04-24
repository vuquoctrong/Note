package rekkisoft.trongvu.com.note.new_note;

public class NewNotePresenter implements NewNotePresenterImp {
    private NewNoteViewImp newNoteViewImp;

    public NewNotePresenter(NewNoteViewImp newNoteViewImp) {
        this.newNoteViewImp = newNoteViewImp;
    }

    @Override
    public void backHome() {
        newNoteViewImp.backHome();
    }

    @Override
    public void showDialogBackground() {
        newNoteViewImp.showDialogBackground();
    }

    @Override
    public void showDialogCamera() {
        newNoteViewImp.showDialogCamera();
    }
}
