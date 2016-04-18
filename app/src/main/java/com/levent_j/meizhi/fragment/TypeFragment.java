package com.levent_j.meizhi.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.levent_j.meizhi.R;
import com.levent_j.meizhi.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by levent_j on 16-4-18.
 */
public class TypeFragment extends BaseFragment{
    @Bind(R.id.tv_fragment_txt)
    TextView textView;

    private static final String TYPE = "TYPE";
    private int type;

    public static TypeFragment newInstance(int type){
        TypeFragment typeFragment = new TypeFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        typeFragment.setArguments(args);
        return typeFragment;
    }

    @Override
    protected int setRootViewId() {
        return R.layout.fragment_type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            type = getArguments().getInt(TYPE);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    private void loadData() {
        textView.setText("类型："+type);
    }
}
