package rekkisoft.trongvu.com.note.detail;

public class DetailPresenter implements DetailPresenterImp {
    private DetailViewImp detailViewImp;

    public DetailPresenter(DetailViewImp detailViewImp) {
        this.detailViewImp = detailViewImp;
    }

    @Override
    public void showDialogBackground() {
        detailViewImp.showDialogBackground();
    }

    @Override
    public void showDialogCamera() {
        detailViewImp.showDialogCamera();
    }

    @Override
    public void backHome() {
        detailViewImp.backHome();
    }
}
