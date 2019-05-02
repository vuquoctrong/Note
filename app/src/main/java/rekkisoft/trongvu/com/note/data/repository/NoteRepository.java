package rekkisoft.trongvu.com.note.data.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.Sort;
import rekkisoft.trongvu.com.note.data.RealmManager;
import rekkisoft.trongvu.com.note.data.model.Note;

public class NoteRepository {
    private Realm mRealm;

    public NoteRepository() {
        this.mRealm = RealmManager.getInstance().getmRealm();
    }


    public void insertNote(final Note note) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number currentIdNum = realm.where(Note.class).max("id");
                int nextId;
                if (currentIdNum == null) {
                    nextId = 0;
                } else {
                    nextId = currentIdNum.intValue() + 1;
                }
                note.setId(nextId);
                realm.insertOrUpdate(note);
            }
        });
    }

    public void deleteNote(final int position) {
        mRealm.beginTransaction();
        mRealm.where(Note.class)
                .equalTo("id",position)
                .findAll()
                .deleteFirstFromRealm();
        mRealm.commitTransaction();
    }

    public void addImageNote(Note note, List<String> url) {
        mRealm.beginTransaction();
        note.setUrls(url);
        mRealm.commitTransaction();
    }

    public void removeImageNote(Note note, int positon) {
        mRealm.beginTransaction();
        note.getUrls().remove(positon);
        mRealm.commitTransaction();
    }

    public void changeBackgroundColor(Note note, int color) {
        mRealm.beginTransaction();
        note.setColor(color);
        mRealm.commitTransaction();
    }


    public void updateNote(Note note, String title, String content, Date createDate, boolean isAlarm,int color) {
        mRealm.beginTransaction();
        note.setTitle(title);
        note.setContent(content);
        note.setCreateDate(createDate);
        note.setAlarm(isAlarm);
        note.setColor(color);
        mRealm.commitTransaction();
    }


    public void updateNote(Note note, String title, String content, String day, String hour, Date createDate, boolean isAlarm) {
        mRealm.beginTransaction();
        note.setTitle(title);
        note.setContent(content);
        note.setCreateDate(createDate);
        note.setAlarm(isAlarm);
        note.setDay(day);
        note.setHour(hour);
        mRealm.commitTransaction();
    }

    public List<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>(mRealm.where(Note.class).findAll());
        return notes;
    }

}
