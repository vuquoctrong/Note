package rekkisoft.trongvu.com.note.new_note;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rekkisoft.trongvu.com.note.R;
import rekkisoft.trongvu.com.note.adapter.ImageAdapter;
import rekkisoft.trongvu.com.note.data.model.Note;
import rekkisoft.trongvu.com.note.home.HomeActivity;
import rekkisoft.trongvu.com.note.utils.DateUtils;
import rekkisoft.trongvu.com.note.utils.Define;
import rekkisoft.trongvu.com.note.utils.Utility;


public class NewNoteActivity extends AppCompatActivity implements View.OnClickListener, NewNoteViewImp, ImageAdapter.ImageOnClickListener {

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
    private ImageView ivImageGallery;
    private ImageView ivCameraImage;
    private EditText etTitle;
    private EditText etContent;
    private ScrollView scrollView;
    private ImageView ivIconback;
    private int colorNote;
    private List<String> mURLImage;
    private NewNotePresenter newNotePresenter;
    private ImageAdapter imageAdapter;
    private RecyclerView recyclerView;
    private Note note;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newnote_item);
        init();
        setOnChangedTitle();

    }

    private void init() {
        imageAdapter = new ImageAdapter(this);
        recyclerView = findViewById(R.id.recyclerImage);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(imageAdapter);
        mURLImage = new ArrayList<>();
        imageAdapter.setImages(mURLImage);
        imageAdapter.setImageOnclickListener(this);
        note = new Note();

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
                showDialogBackground();
                break;
            case R.id.btnCamera:
                showDialogCamera();
                break;
            case R.id.ivImageGallery:
                getImageFromAlbum();
                dialogCamera.dismiss();
                break;
            case R.id.btnSaveNote:
                if (TextUtils.isEmpty(etTitle.getText()) || TextUtils.isEmpty(etContent.getText())) {
                    Toast.makeText(this, "Trước khi Lưu hãy nhập thông tin nhé", Toast.LENGTH_SHORT).show();
                } else {
                    insertNote();
                }
                break;
            default:
                break;
        }

    }

    private void getImageFromAlbum() {
        try {
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            i.setType("image/*");
            startActivityForResult(i, Define.NavigationKey.RESULT_LOAD_IMAGE);
        } catch (Exception exp) {
            Log.i("Error", exp.toString());
        }
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                mURLImage.add(DateUtils.bitMapToString(selectedImage));
                imageAdapter.setImages(mURLImage);
                newNotePresenter.addImageNote(note, mURLImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void backHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void showDialogBackground() {
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

    private void showDialogCamera() {
        dialogCamera = new Dialog(this);
        dialogCamera.setCancelable(true);
        dialogCamera.setContentView(R.layout.layout_dialog_insertpicture);
        dialogCamera.show();
        ivImageGallery = dialogCamera.findViewById(R.id.ivImageGallery);
        ivCameraImage = dialogCamera.findViewById(R.id.ivCameraImage);

        ivImageGallery.setOnClickListener(this);
        ivCameraImage.setOnClickListener(this);

    }

    private void insertNote() {
        String title = etTitle.getText().toString().trim();
        String content = etContent.getText().toString().trim();
        Date currentTime = Calendar.getInstance().getTime();
        note.setTitle(title);
        note.setContent(content);
        note.setColor(colorNote);
        note.setCreateDate(currentTime);
        newNotePresenter.insertNote(note);
        tvTitleNote.setText(note.getTitle());
    }

    private void updateTime() {

        String strDate = DateUtils.getTimeByPattern("dd/MM/YYYY hh:mm");
        tvData.setText(strDate);

    }

    private void setOnChangedTitle() {
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


    @Override
    public void onClickItem(String url) {

    }

    @Override
    public void onRemove(int position) {

    }
}
