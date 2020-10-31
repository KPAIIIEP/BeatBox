package com.bignerdranch.android.beatbox;

import android.app.Activity;
import android.view.View;
import android.widget.SeekBar;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class SoundViewModel extends BaseObservable {
    private Sound mSound;
    private BeatBox mBeatBox;
    private int mValue = 100;

    public SoundViewModel(BeatBox beatBox) {
        mBeatBox = beatBox;
    }

    @Bindable
    public String getTitle() {
        return mSound.getName();
    }

    public Sound getSound() {
        return mSound;
    }

    public void setSound(Sound sound) {
        mSound = sound;
        notifyChange();
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        this.mValue = value;
        notifyChange();
    }

    public String getTextViewText() {
        return "Скорость воспроизведения: " + getValue() + "%";
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        setValue(progress);
    }

    public void onButtonClicked(View v) {
        Activity activity = (Activity) v.getContext();
        SeekBar seekBar = activity.findViewById(R.id.seek_bar);
        this.mValue = seekBar.getProgress();
        mBeatBox.play(mSound, (float) getValue() / 100);
    }
}
