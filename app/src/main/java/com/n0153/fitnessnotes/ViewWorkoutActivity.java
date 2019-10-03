package com.n0153.fitnessnotes;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.n0153.fitnessnotes.dialogs.ModifySetFragment;
import com.n0153.fitnessnotes.interfaces.DateGettable;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ViewWorkoutActivity extends AppCompatActivity implements DateGettable {
    long dateLong;
    TextView header;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);
        dateLong =  getIntent().getLongExtra(ModifySetFragment.EXERCISE_DATE_LONG_KEY2, 0);
        setTitle(getString(R.string.title_workout));
        header = findViewById(R.id.woHeader);
        SimpleDateFormat sdf = new SimpleDateFormat(headerDatePattern);
        Date date = new Date(dateLong);
        header.setText(sdf.format(date));


    }

    public long getDateLong() {
        return dateLong;
    }

    @Override
    public long getLongDate() {
        return dateLong;
    }
}
