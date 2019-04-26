package rekkisoft.trongvu.com.note.detail;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.DuplicateFormatFlagsException;
import java.util.List;

import rekkisoft.trongvu.com.note.R;
import rekkisoft.trongvu.com.note.data.model.Note;
import rekkisoft.trongvu.com.note.home.HomeActivity;
import rekkisoft.trongvu.com.note.utils.Define;

public class DetailActivity extends AppCompatActivity implements DetailViewImp, View.OnClickListener {

    private TextView tvDateUpdateNote;
    private TextView tvTitle;
    private EditText etTitleUpdate;
    private EditText etContentUpdate;
    private Button btnBackground;
    private Button btnCamera;
    private Button btnColorwhite;
    private Button btnColorblue;
    private Button btnColorpink;
    private Button btnColormandarin;
    private Button btnColordacbiet;
    private Button btnSaveNoteUpdate;
    private ImageView ivBackHome;
    private ImageButton ibDeleteNote;
    private ImageButton ibPreviouNote;
    private ImageButton ibNextNote;
    private Dialog dialogColor;
    private Dialog dialogCamera;
    private ScrollView scrollView;
    private DetailPresenter detailPresenter;
    private int position;
    private int colorNoteUpdate;
    private List<Note> notes;
    private Date currentTime;
    private int currentPosition;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        position = getIntent().getIntExtra(Define.NavigationKey.NOTE_ID, 0);
        init();
        setOnchangedTitle();

    }

    private void init() {

        detailPresenter = new DetailPresenter(this);
        tvDateUpdateNote = findViewById(R.id.tvDateUpdate);
        tvTitle = findViewById(R.id.tvTitle);
        etTitleUpdate = findViewById(R.id.etTitleUpdate);
        etContentUpdate = findViewById(R.id.etContentUpdate);
        btnBackground = findViewById(R.id.btnColorBackground);
        btnCamera = findViewById(R.id.btnCamera);
        btnSaveNoteUpdate = findViewById(R.id.btnSavenote);
        scrollView = findViewById(R.id.scrollView);
        ivBackHome = findViewById(R.id.ivBackHome);
        ibDeleteNote = findViewById(R.id.ibDiscard);
        ibPreviouNote = findViewById(R.id.ibPreviouitem);
        ibNextNote = findViewById(R.id.ibNextitem);

        btnBackground.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        ivBackHome.setOnClickListener(this);
        btnSaveNoteUpdate.setOnClickListener(this);
        ibDeleteNote.setOnClickListener(this);
        ibPreviouNote.setOnClickListener(this);
        ibNextNote.setOnClickListener(this);

        notes = new ArrayList<>();
        notes = detailPresenter.getAllNote();
        currentPosition = position;
        getNoteUpdate(currentPosition);
        setUpForEditNote();
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

    @Override
    public void backHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


    @Override
    public void setUpForEditNote() {
        if (notes.size() == 1) {
            setImageButtonNextEnable(false);
            setImageButtonPreviousEnable(false);
        } else if (currentPosition == notes.size() - 1) {
            setImageButtonPreviousEnable(true);
            setImageButtonNextEnable(false);
        } else if (currentPosition == 0) {
            setImageButtonNextEnable(true);
            setImageButtonPreviousEnable(false);
        } else {
            setImageButtonNextEnable(true);
            setImageButtonPreviousEnable(true);
        }
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
                scrollView.setBackgroundColor(Color.parseColor(Define.NavigationKey.COLOR1));
                colorNoteUpdate = Color.parseColor(Define.NavigationKey.COLOR1);
                dialogColor.dismiss();
                break;
            case R.id.btnColorpink:
                scrollView.setBackgroundColor(Color.parseColor(Define.NavigationKey.COLOR2));
                colorNoteUpdate = Color.parseColor(Define.NavigationKey.COLOR2);
                dialogColor.dismiss();
                break;
            case R.id.btnColordacbiete:
                scrollView.setBackgroundColor(Color.parseColor(Define.NavigationKey.COLOR3));
                colorNoteUpdate = Color.parseColor(Define.NavigationKey.COLOR3);
                dialogColor.dismiss();
                break;
            case R.id.btnColormandarin:
                scrollView.setBackgroundColor(Color.parseColor(Define.NavigationKey.COLOR4));
                colorNoteUpdate = Color.parseColor(Define.NavigationKey.COLOR4);
                dialogColor.dismiss();
                break;
            case R.id.ivBackHome:
                backHome();
                break;
            case R.id.btnSavenote:
                updateNote();
                break;
            case R.id.ibDiscard:
                showDeleteNoteDialog();
                break;
            case R.id.ibNextitem:
                updateActionBottom(Define.NavigationKey.NEXT_NOTE);
                break;
            case R.id.ibPreviouitem:
                updateActionBottom(Define.NavigationKey.PREVIOUS_NOTE);
                break;
            default:
                break;
        }
    }

    private void getNoteUpdate(int currentPosition) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm");
        String strDate = sdf.format(c.getTime());
        currentTime = Calendar.getInstance().getTime();
        tvDateUpdateNote.setText(strDate);
        etTitleUpdate.setText(notes.get(currentPosition).getTitle());
        etContentUpdate.setText(notes.get(currentPosition).getContent());
        scrollView.setBackgroundColor(notes.get(currentPosition).getColor());
        colorNoteUpdate = notes.get(currentPosition).getColor();
        tvTitle.setText(notes.get(currentPosition).getTitle());

    }

    private void updateNote() {
        detailPresenter.updateNote(notes.get(currentPosition), etTitleUpdate.getText().toString()
                , etContentUpdate.getText().toString(), currentTime, false, colorNoteUpdate);
        backHome();

    }

    public void showDeleteNoteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Note");
        builder.setMessage("Are you sure you want to delete?");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                detailPresenter.deleteNote(notes.get(currentPosition).getId());
                backHome();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void setImageButtonPreviousEnable(boolean isEnable) {
        if (isEnable) {
            ibPreviouNote.setAlpha(255);
            ibPreviouNote.setEnabled(true);
        } else {
            ibPreviouNote.setAlpha(60);
            ibPreviouNote.setEnabled(false);
        }
    }

    public void setImageButtonNextEnable(boolean isEnable) {
        if (isEnable) {
            ibNextNote.setAlpha(255);
            ibNextNote.setEnabled(true);
        } else {
            ibNextNote.setAlpha(60);
            ibNextNote.setEnabled(false);
        }
    }

    private void updateActionBottom(String action) {
        if (action.equals(Define.NavigationKey.NEXT_NOTE) && currentPosition < notes.size() - 1) {
            currentPosition = currentPosition + 1;
        } else if (action.equals(Define.NavigationKey.PREVIOUS_NOTE) && currentPosition > 0) {
            currentPosition = currentPosition - 1;
        }
        setUpForEditNote();
        getNoteUpdate(currentPosition);
    }

        private void setOnchangedTitle() {
        etTitleUpdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvTitle.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
