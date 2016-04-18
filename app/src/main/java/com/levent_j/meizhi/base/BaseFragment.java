package com.levent_j.meizhi.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by levent_j on 16-4-18.
 */

public abstract class BaseFragment extends Fragment {
    private String TAG;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(setRootViewId(), container, false);
        TAG = this.getClass().getSimpleName();
        ButterKnife.bind(this,view);
        return view;
    }

    protected abstract int setRootViewId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    protected void msg(String s) {
        Log.d(TAG, s);
    }

    protected void Toa(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
