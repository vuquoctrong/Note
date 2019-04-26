package rekkisoft.trongvu.com.note.new_note;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import rekkisoft.trongvu.com.note.R;
import rekkisoft.trongvu.com.note.data.model.Note;
import rekkisoft.trongvu.com.note.home.HomeActivity;
import rekkisoft.trongvu.com.note.utils.Define;
import rekkisoft.trongvu.com.note.utils.Utility;

public class NewNoteActivity extends AppCompatActivity implements View.OnClickListener, NewNoteViewImp {

    private Dialog dialogColor;
    private Dialog dialogCamera;
    private TextView tvData;
    private TextView tvTitleNote;
    private Button btnColorwhite;
    private Button btnColorblue;
    private Button btnColorpink;
    private Button btnColormandarin;
    private Button btnColordacbiet;
    private Button btnColorBackground;
    private Button btnCamera;
    private Button btnSaveNewNote;
    private EditText etTitle;
    private EditText etContent;
    private ScrollView scrollView;
    private ImageView ivIconback;
    private int colorNote;
    private NewNotePresenter newNotePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newnote_item);
        init();
        setOnchangedTitle();

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
        tvTitleNote = findViewById(R.id.tvTitle);

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
                colorNote = Color.WHITE;
                dialogColor.dismiss();
                break;
            case R.id.btnColorblue:
                scrollView.setBackgroundColor(Color.parseColor(Define.NavigationKey.COLOR1));
                colorNote = Color.parseColor(Define.NavigationKey.COLOR1);
                dialogColor.dismiss();
                break;
            case R.id.btnColordacbiete:
                scrollView.setBackgroundColor(Color.parseColor(Define.NavigationKey.COLOR3));
                colorNote = Color.parseColor(Define.NavigationKey.COLOR3);
                dialogColor.dismiss();
                break;
            case R.id.btnColorpink:
                scrollView.setBackgroundColor(Color.parseColor(Define.NavigationKey.COLOR2));
                colorNote = Color.parseColor(Define.NavigationKey.COLOR2);
                dialogColor.dismiss();
                break;
            case R.id.btnColormandarin:
                scrollView.setBackgroundColor(Color.parseColor(Define.NavigationKey.COLOR4));
                colorNote = Color.parseColor(Define.NavigationKey.COLOR4);
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
        btnColordacbiet = dialogColor.findViewById(R.id.btnColordacbiete);

        btnColorwhite.setOnClickListener(this);
        btnColorblue.setOnClickListener(this);
        btnColorpink.setOnClickListener(this);
        btnColormandarin.setOnClickListener(this);
        btnColordacbiet.setOnClickListener(this);
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
        Date currentTime = Calendar.getInstance().getTime();
        Note note = new Note(title, content);
        note.setColor(colorNote);
        note.setCreateDate(currentTime);
        newNotePresenter.insertNote(note);
        tvTitleNote.setText(note.getTitle());
    }

    private void updateTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm");
        String strDate = sdf.format(c.getTime());
        tvData.setText(strDate);

    }

    private void setOnchangedTitle() {
        etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvTitleNote.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
