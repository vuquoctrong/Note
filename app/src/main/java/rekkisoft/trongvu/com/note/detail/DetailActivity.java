package rekkisoft.trongvu.com.note.detail;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
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
import rekkisoft.trongvu.com.note.service.SchedulingService;
import rekkisoft.trongvu.com.note.utils.DateUtils;
import rekkisoft.trongvu.com.note.utils.Define;
import rekkisoft.trongvu.com.note.utils.Utility;

public class DetailActivity extends AppCompatActivity
        implements DetailViewImp, View.OnClickListener, ImageAdapter.ImageOnClickListener, AdapterView.OnItemSelectedListener {

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
    private ImageView ivImageGallery;
    private ImageView ivCameraImage;
    private ImageButton ibDeleteNote;
    private ImageButton ibPreviouNote;
    private ImageButton ibNextNote;
    private ImageButton ibShare;
    private Dialog dialogColor;
    private Dialog dialogCamera;
    private ScrollView scrollView;
    private DetailPresenter detailPresenter;
    private int position;
    private int colorNoteUpdate;
    private List<Note> notes;
    private Date currentTime;
    private int currentPosition;
    private ImageAdapter imageAdapter;
    private RecyclerView recyclerView;
    private List<String> mURLImage;

    private ImageView ivAlarm;
    private TextView tvAlarm;
    private LinearLayout llSpinner;
    private Spinner spnHour;
    private Spinner spnDay;
    private Button btnSaveAlarm;
    private ArrayAdapter<String> mDayAdapter;
    private ArrayAdapter<String> mHourAdapter;

    private String hourAlarm;
    private String dayAlarm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        position = getIntent().getIntExtra(Define.NavigationKey.NOTE_ID, 0);
        init();
        setOnChangedTitle();

    }

    private void init() {


        imageAdapter = new ImageAdapter();
        recyclerView = findViewById(R.id.recyclerImage);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(imageAdapter);
        imageAdapter.setImageOnclickListener(this);

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
        ibShare = findViewById(R.id.ibtnShare);

        btnBackground.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        ivBackHome.setOnClickListener(this);
        btnSaveNoteUpdate.setOnClickListener(this);
        ibDeleteNote.setOnClickListener(this);
        ibPreviouNote.setOnClickListener(this);
        ibNextNote.setOnClickListener(this);
        ibShare.setOnClickListener(this);


        ivAlarm = findViewById(R.id.ivAlarm);
        tvAlarm = findViewById(R.id.tvAlarm);
        llSpinner = findViewById(R.id.llSpinner);
        spnDay = findViewById(R.id.spnDateday);
        spnHour = findViewById(R.id.spnHour);
        btnSaveAlarm = findViewById(R.id.btnSaveAlarm);
        ivAlarm.setOnClickListener(this);
        btnSaveAlarm.setOnClickListener(this);


        notes = new ArrayList<>();
        mURLImage = new ArrayList<>();
        notes = detailPresenter.getAllNote();
        currentPosition = position;
        getNoteUpdate(currentPosition);
        setUpForEditNote();

        mDayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Define.NavigationKey.days);
        mDayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDay.setAdapter(mDayAdapter);

        mHourAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Define.NavigationKey.hours);
        mHourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnHour.setAdapter(mHourAdapter);

        spnDay.setOnItemSelectedListener(this);
        spnHour.setOnItemSelectedListener(this);
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

    private void backHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void setUpForEditNote() {
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
                showDialogBackground();
                break;
            case R.id.btnCamera:
                showDialogCamera();
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
            case R.id.ivImageGallery:
                getImageFromAlbum();
                dialogCamera.dismiss();
                break;
            case R.id.ivCameraImage:
                addImageToCamera();
                dialogCamera.dismiss();
                break;
            case R.id.ibPreviouitem:
                updateActionBottom(Define.NavigationKey.PREVIOUS_NOTE);
                break;
            case R.id.ibtnShare:
                share();
                break;
            case R.id.ivAlarm:
                tvAlarm.setVisibility(View.GONE);
                llSpinner.setVisibility(View.VISIBLE);
                break;
            case R.id.btnSaveAlarm:
                showAlarmDialog();
                break;
            default:
                break;
        }
    }

    private void getImageFromAlbum() {
        try {
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            i.setType(Define.NavigationKey.TYPE_IMAGE);
            startActivityForResult(i, Define.NavigationKey.RESULT_LOAD_IMAGE);
        } catch (Exception exp) {
            Log.i("Error", exp.toString());
        }
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (reqCode == Define.NavigationKey.RESULT_LOAD_IMAGE) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    mURLImage.add(imageUri.toString());
                    imageAdapter.setImages(mURLImage);
                    imageAdapter.notifyDataSetChanged();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            } else if (reqCode == Define.NavigationKey.CAMERA_PIC_REQUEST) {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                mURLImage.add(DateUtils.getImageUri(this, image).toString());
                imageAdapter.setImages(mURLImage);
                imageAdapter.notifyDataSetChanged();
            }
        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }

    }

    private void getNoteUpdate(int currentPosition) {
        final Note note = notes.get(currentPosition);
        String strDate = DateUtils.getTimeByPattern("dd/MM/YYYY hh:mm");
        currentTime = Calendar.getInstance().getTime();
        tvDateUpdateNote.setText(strDate);
        etTitleUpdate.setText(note.getTitle());
        etContentUpdate.setText(note.getContent());
        scrollView.setBackgroundColor(note.getColor());
        colorNoteUpdate = note.getColor();
        tvTitle.setText(note.getTitle());
        mURLImage.addAll(note.getUrls());
        imageAdapter.setImages(note.getUrls());

    }

    private void updateNote() {
        detailPresenter.addImageNote(notes.get(currentPosition), mURLImage);
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
        ibNextNote.setAlpha(isEnable ? 255 : 60);
        ibNextNote.setEnabled(isEnable);
    }

    private void updateActionBottom(String action) {

        if (Define.NavigationKey.NEXT_NOTE.equals(action) && currentPosition < notes.size() - 1) {
            currentPosition = currentPosition + 1;
        } else if (Define.NavigationKey.PREVIOUS_NOTE.equals(action) && currentPosition > 0) {
            currentPosition = currentPosition - 1;
        }
        setUpForEditNote();
        getNoteUpdate(currentPosition);
    }

    private void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, etContentUpdate.getText().toString());
        sendIntent.setType(Define.NavigationKey.TYPE_SHARE);
        startActivity(Intent.createChooser(sendIntent, getString(R.string.text_share)));
    }

    private void setOnChangedTitle() {
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


    @Override
    public void onClickItem(String url) {
        previewImage(url);

    }

    @Override
    public void onRemove(int position) {
        mURLImage.remove(position);
        imageAdapter.setImages(mURLImage);
        imageAdapter.notifyDataSetChanged();

    }

    private void previewImage(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.parse(url), Define.NavigationKey.TYPE_IMAGE);
        startActivity(intent);
    }

    private void addImageToCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, Define.NavigationKey.CAMERA_PIC_REQUEST);

    }

    private void setAlarmNote() {
        long timesInMillis = Utility.parseDateToMilisecond(dayAlarm + " " + hourAlarm);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, SchedulingService.class);
        intent.putExtra(Define.NavigationKey.KEY_TYPE, notes.get(currentPosition).getTitle());
        intent.putExtra(Define.NavigationKey.KEY_ID,notes.get(currentPosition).getId());
        PendingIntent pendingIntent = PendingIntent.getService(
                this, notes.get(currentPosition).getId(), intent, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager
                    .setExact(AlarmManager.RTC_WAKEUP, timesInMillis, pendingIntent);
        } else {
            alarmManager
                    .set(AlarmManager.RTC_WAKEUP, timesInMillis, pendingIntent);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spnDateday) {
            switch (position) {
                case 1:
                    dayAlarm = Utility.addDate(0);
                    Define.NavigationKey.days[0] = dayAlarm;
                    mDayAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Date Alarm: " + dayAlarm, Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    dayAlarm = Utility.addDate(1);
                    Define.NavigationKey.days[0] = dayAlarm;
                    mDayAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Date Alarm: " + dayAlarm, Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    dayAlarm = Utility.addDate(2);
                    Define.NavigationKey.days[0] = dayAlarm;
                    mDayAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Date Alarm: " + dayAlarm, Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    showDataPickerDiaLog();
                    break;
                default:
                    break;
            }
        } else if (parent.getId() == R.id.spnHour) {
            switch (position) {
                case 1:
                    hourAlarm = Define.NavigationKey.hours[1];
                    Define.NavigationKey.hours[0] = hourAlarm;
                    mHourAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Hour Alarm: " + hourAlarm, Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    hourAlarm = Define.NavigationKey.hours[2];
                    Define.NavigationKey.hours[0] = hourAlarm;
                    mHourAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Hour Alarm: " + hourAlarm, Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    hourAlarm = Define.NavigationKey.hours[3];
                    Define.NavigationKey.hours[0] = hourAlarm;
                    mHourAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Hour Alarm: " + hourAlarm, Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    hourAlarm = Define.NavigationKey.hours[4];
                    Define.NavigationKey.hours[0] = hourAlarm;
                    mHourAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Hour Alarm: " + hourAlarm, Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    showTimePickerDialog();
                    break;
                default:
                    break;
            }
        }
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hourAlarm = hourOfDay + ":" + minute;
                Define.NavigationKey.hours[0] = hourAlarm;
                mHourAdapter.notifyDataSetChanged();
                spnHour.setSelection(0);

            }
        }, hour, minute, true);

        timePickerDialog.show();
    }

    private void showDataPickerDiaLog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dayAlarm = dayOfMonth + "/" + monthOfYear + "/" + year;
                Define.NavigationKey.days[0] = dayAlarm;
                mDayAdapter.notifyDataSetChanged();

            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void showAlarmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alarm Note");
        builder.setMessage("Alarm Date: " + dayAlarm + " " + hourAlarm);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setAlarmNote();
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


    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}
