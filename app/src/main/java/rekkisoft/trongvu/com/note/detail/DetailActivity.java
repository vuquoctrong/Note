package rekkisoft.trongvu.com.note.detail;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rekkisoft.trongvu.com.note.R;
import rekkisoft.trongvu.com.note.data.model.Note;
import rekkisoft.trongvu.com.note.home.HomeActivity;
import rekkisoft.trongvu.com.note.utils.Define;

public class DetailActivity extends AppCompatActivity implements DetailViewImp, View.OnClickListener {

    private TextView tvDateUpdateNote;
    private EditText etTitleUpdate;
    private EditText etContentUpdate;
    private Button btnBackground, btnCamera;
    private Button btnColorwhite, btnColorblue, btnColorpink, btnColormandarin;
    private Button btnSaveNoteUpdate;
    private ImageView ivBackHome;
    private Dialog dialogColor, dialogCamera;
    private ScrollView scrollView;
    private DetailPresenter detailPresenter;
    private int noteID;
    private int colorNoteUpdate;
    private List<Note> notes;
    private Date currentTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        noteID = getIntent().getIntExtra(Define.NavigationKey.NOTE_ID, 0);
        Log.d("ngocanh", "" + noteID);
        init();
        getNoteUpdate();
    }

    private void init() {
        detailPresenter = new DetailPresenter(this);

        tvDateUpdateNote = findViewById(R.id.tvDateUpdate);
        etTitleUpdate = findViewById(R.id.etTitleUpdate);
        etContentUpdate = findViewById(R.id.etContentUpdate);
        btnBackground = findViewById(R.id.btnColorBackground);
        btnCamera = findViewById(R.id.btnCamera);
        btnSaveNoteUpdate = findViewById(R.id.btnSavenote);
        scrollView = findViewById(R.id.scrollView);
        ivBackHome = findViewById(R.id.ivBackHome);

        btnBackground.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        ivBackHome.setOnClickListener(this);
        btnSaveNoteUpdate.setOnClickListener(this);

        notes = new ArrayList<>();
        notes = detailPresenter.getAllNote();
    }

    @Override
    public void showDialogBackground() {

        dialogColor = new Dialog(this);
        dialogColor.setCancelable(true);
        dialogColor.setContentView(R.layout.layout_dialog_colornote);
        dialogColor.show();

        btnColorwhite = dialogColor.findViewById(R.id.btnColorwhite);
        btnColorblue = dialogColor.findViewById(R.id.btnColorblue);
        btnColorpink = dialogColor.findViewById(R.id.btnColorpink);
        btnColormandarin = dialogColor.findViewById(R.id.btnColormandarin);

        btnColorwhite.setOnClickListener(this);
        btnColorblue.setOnClickListener(this);
        btnColorpink.setOnClickListener(this);
        btnColormandarin.setOnClickListener(this);

    }

    @Override
    public void showDialogCamera() {
        dialogCamera = new Dialog(this);
        dialogCamera.setCancelable(true);
        dialogCamera.setContentView(R.layout.layout_dialog_insertpicture);
        dialogCamera.show();
    }

    @Override
    public void backHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnColorBackground:
                detailPresenter.showDialogBackground();
                break;
            case R.id.btnCamera:
                detailPresenter.showDialogCamera();
                break;
            case R.id.btnColorwhite:
                scrollView.setBackgroundColor(Color.WHITE);
                colorNoteUpdate = Color.WHITE;
                dialogColor.dismiss();
                break;
            case R.id.btnColorblue:
                scrollView.setBackgroundColor(Color.BLUE);
                colorNoteUpdate = Color.BLUE;
                dialogColor.dismiss();
                break;
            case R.id.btnColorpink:
                scrollView.setBackgroundColor(Color.parseColor("#f188bc"));
                colorNoteUpdate = Color.parseColor("#f188bc");
                dialogColor.dismiss();
                break;
            case R.id.btnColormandarin:
                scrollView.setBackgroundColor(Color.parseColor("#FF68CF0E"));
                colorNoteUpdate = Color.parseColor("#FF68CF0E");
                dialogColor.dismiss();
                break;
            case R.id.ivBackHome:
                backHome();
                break;
            case R.id.btnSavenote:
                updateNote();
                break;
            default:
                break;
        }
    }

    private void getNoteUpdate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm");
        String strDate = sdf.format(c.getTime());
        currentTime = Calendar.getInstance().getTime();
        tvDateUpdateNote.setText(strDate);
        etTitleUpdate.setText(notes.get(noteID).getTitle());
        etContentUpdate.setText(notes.get(noteID).getContent());
        scrollView.setBackgroundColor(notes.get(noteID).getColor());
        colorNoteUpdate = notes.get(noteID).getColor();
    }

    private void updateNote() {
        detailPresenter.updateNote(notes.get(noteID), etTitleUpdate.getText().toString()
                , etContentUpdate.getText().toString(), currentTime, false, colorNoteUpdate);
        backHome();

    }
}
