package rekkisoft.trongvu.com.note.model;

import android.graphics.Color;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Note extends RealmObject {
    @PrimaryKey
    private int id;
    private String title;
    private String content;
    private boolean isAlarm;
    private String day;
    private String hour;
    private int color;
    private Date createDate;
    private RealmList<String> urls = new RealmList<>();

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Note() {
        isAlarm = false;
        color = Color.WHITE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isAlarm() {
        return isAlarm;
    }

    public void setAlarm(boolean alarm) {
        isAlarm = alarm;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public RealmList<String> getUrls() {
        return urls;
    }

    public void setUrls(RealmList<String> urls) {
        this.urls = urls;
    }
}
