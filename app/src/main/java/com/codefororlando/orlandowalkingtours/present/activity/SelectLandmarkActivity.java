package com.codefororlando.orlandowalkingtours.present.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.codefororlando.orlandowalkingtours.event.OnCancelSelectLandmarkEvent;
import com.codefororlando.orlandowalkingtours.event.OnSelectLandmarkEvent;
import com.codefororlando.orlandowalkingtours.present.base.BaseActivity;
import com.codefororlando.orlandowalkingtours.present.fragment.SelectLandmarkFragment;

import java.io.Serializable;

// Host for standalone landmark selection
public class SelectLandmarkActivity extends BaseActivity {
    public static Intent getIntent(Context context, Class cls, long tourId) {
        return new Intent(context, SelectLandmarkActivity.class)
                .putExtra(SelectLandmarkFragment.CALLER_KEY, cls)
                .putExtra(SelectLandmarkFragment.TOUR_ID_KEY, tourId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Serializable caller =
                    getIntent().getSerializableExtra(SelectLandmarkFragment.CALLER_KEY);
            long tourId = getIntent().getLongExtra(SelectLandmarkFragment.TOUR_ID_KEY, 0);
            Fragment fragment = SelectLandmarkFragment.newInstance(caller, tourId);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment)
                    .commit();
        }

        busSubscribe();
    }

    @Override
    protected void onEvent(Object event) {
        if (event instanceof OnSelectLandmarkEvent ||
                event instanceof OnCancelSelectLandmarkEvent) {
            // Host activity always closes on done/cancel
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        busUnsubscribe();
        super.onDestroy();
    }
}
