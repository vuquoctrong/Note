package rekkisoft.trongvu.com.note.new_note;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import rekkisoft.trongvu.com.note.R;
import rekkisoft.trongvu.com.note.data.model.Note;
import rekkisoft.trongvu.com.note.home.HomeActivity;

public class NewNoteActivity extends AppCompatActivity implements View.OnClickListener, NewNoteViewImp {

    private ImageView ivIconback;
    private NewNotePresenter newNotePresenter;
    private Dialog dialogColor, dialogCamera;
    private ScrollView scrollView;
    private Button btnColorwhite, btnColorblue, btnColorpink, btnColormandarin;
    private Button btnColorBackground, btnCamera;
    private Button btnSaveNewNote;
    private EditText etTitle, etContent;
    private TextView tvData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newnote_item);
        init();
    }

    private void init() {
        ivIconback = findViewById(R.id.ivIcon);
        btnColorBackground = findViewById(R.id.btnColorBackground);
        scrollView = findViewById(R.id.scrollView);
        btnCamera = findViewById(R.id.btnCamera);
        btnSaveNewNote = findViewById(R.id.btnSaveNote);
        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        tvData = findViewById(R.id.tvDate);

        ivIconback.setOnClickListener(this);
        btnColorBackground.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        newNotePresenter = new NewNotePresenter(this);
        btnSaveNewNote.setOnClickListener(this);
        updateTime();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivIcon:
                backHome();
                break;
            case R.id.btnColorwhite:
                scrollView.setBackgroundColor(Color.WHITE);
                dialogColor.dismiss();
                break;
            case R.id.btnColorblue:
                scrollView.setBackgroundColor(Color.BLUE);
                dialogColor.dismiss();
                break;
            case R.id.btnColorpink:
                scrollView.setBackgroundColor(Color.parseColor("#f188bc"));
                dialogColor.dismiss();
                break;
            case R.id.btnColormandarin:
                scrollView.setBackgroundColor(Color.parseColor("#FF68CF0E"));
                dialogColor.dismiss();
                break;
            case R.id.btnColorBackground:
                newNotePresenter.showDialogBackground();
                break;
            case R.id.btnCamera:
                newNotePresenter.showDialogCamera();
                break;
            case R.id.btnSaveNote:
                if (etTitle.getText().toString().equals("") || etContent.getText().toString().equals("")) {
                    Toast.makeText(this, "Trước khi Lưu hãy nhập thông tin nhé", Toast.LENGTH_SHORT).show();
                } else {
                    insertNote();
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void backHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
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

    private void insertNote() {
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();
        Note note = new Note(title, content);
        newNotePresenter.insertNote(note);
    }

    private void updateTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd,MMMM,YYYY hh,mm,a");
        String strDate = sdf.format(c.getTime());
        tvData.setText(strDate);
    }
}
