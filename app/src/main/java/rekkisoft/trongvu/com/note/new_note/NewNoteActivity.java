package rekkisoft.trongvu.com.note.new_note;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import rekkisoft.trongvu.com.note.R;
import rekkisoft.trongvu.com.note.home.HomeActivity;

public class NewNoteActivity extends AppCompatActivity implements View.OnClickListener,NewNoteViewImp {

    private ImageView iv_Iconback;
    private NewNotePresenter newNotePresenter;
    private Dialog dialogColor, dialogCamera;
    private ScrollView scrollView;
    private Button btn_colorwhite,btn_colorblue,btn_colorpink,btn_colormandarin;
    private Button btn_ColorBackground, btn_Camera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newnote_item);
        init();
    }

    private void init(){
        iv_Iconback = findViewById(R.id.ivicon);
        btn_ColorBackground = findViewById(R.id.btnColorBackground);
        scrollView = findViewById(R.id.scrollView);
        btn_Camera = findViewById(R.id.btnCamera);

        iv_Iconback.setOnClickListener(this);
        btn_ColorBackground.setOnClickListener(this);
        btn_Camera.setOnClickListener(this);
        newNotePresenter = new NewNotePresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivicon:
                newNotePresenter.backHome();
                break;
            case R.id.btncolorwhite:
                scrollView.setBackgroundColor(Color.WHITE);
                dialogColor.dismiss();
                break;
            case R.id.btncolorblue:
                scrollView.setBackgroundColor(Color.BLUE);
                dialogColor.dismiss();
                break;
            case R.id.btncolorpink:
                scrollView.setBackgroundColor(Color.parseColor("#f188bc"));
                dialogColor.dismiss();
                break;
            case R.id.btncolormandarin:
                scrollView.setBackgroundColor(Color.parseColor("#FF68CF0E"));
                dialogColor.dismiss();
                break;
            case R.id.btnColorBackground:
                newNotePresenter.showDialogBackground();
                break;
            case R.id.btnCamera:
                newNotePresenter.showDialogCamera();
            default:
                break;
        }

    }

    @Override
    public void backHome() {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void showDialogBackground() {
        dialogColor = new Dialog(this);
        dialogColor.setCancelable(true);
        dialogColor.setContentView(R.layout.layout_dialog_colornote);
        dialogColor.show();

        btn_colorwhite = dialogColor.findViewById(R.id.btncolorwhite);
        btn_colorblue = dialogColor.findViewById(R.id.btncolorblue);
        btn_colorpink = dialogColor.findViewById(R.id.btncolorpink);
        btn_colormandarin = dialogColor.findViewById(R.id.btncolormandarin);

        btn_colorwhite.setOnClickListener(this);
        btn_colorblue.setOnClickListener(this);
        btn_colorpink.setOnClickListener(this);
        btn_colormandarin.setOnClickListener(this);
    }

    @Override
    public void showDialogCamera() {
        dialogCamera = new Dialog(this);
        dialogCamera.setCancelable(true);
        dialogCamera.setContentView(R.layout.layout_dialog_insertpicture);
        dialogCamera.show();
    }
}
