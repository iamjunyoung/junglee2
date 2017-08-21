package com.bbeaggoo.junglee2.ui.main;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GridItem extends RealmObject {

    @PrimaryKey
    private long id;

    private String title;
    private String url = null;
    private String desc;
    private Drawable img;
    private Uri uri = null;
    private String category;
    private String folder;
    private String date;
    private boolean checked;
    private int viewType = 0; //PARENT_ITEM_VIEW

    public GridItem() {
    }

    public GridItem(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getViewType() { return viewType; }

    public void setViewType(int viewType) { this.viewType = viewType; }
}
