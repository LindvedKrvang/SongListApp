package com.krvang.lindved.songlistapp.be;

/**
 * Created by Lindved on 22-02-2018.
 */

public class Song {

    private String mTitle, mArtist, mYear;
    private int mBPM;

    public Song(String title, String artist, String year, int bpm){
        mTitle = title;
        mArtist = artist;
        mYear = year;
        mBPM = bpm;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public int getBPM() {
        return mBPM;
    }

    public void setBPM(int BPM) {
        mBPM = BPM;
    }
}
