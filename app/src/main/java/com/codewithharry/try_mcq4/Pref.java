package com.codewithharry.try_mcq4;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Pref {

    private static final String HIGHEST_SCORE ="highets_Scpore" ;
    private SharedPreferences preferences;

    public Pref(Activity context) {
        this.preferences = context.getPreferences( Context.MODE_PRIVATE);// Shared preference are used with Activity or Contrxt
    }

    public void saveHighestScore(int score)
    {
        int CurrentScore = score;
        int lastScore= preferences.getInt(HIGHEST_SCORE,0);

        if(CurrentScore>lastScore)
        {
            // WE will have a new highest Score
            preferences.edit().putInt(HIGHEST_SCORE,CurrentScore).apply();
        }
    }
    public int getHighScore()
    {

        return  preferences.getInt(HIGHEST_SCORE,0);
    }

}