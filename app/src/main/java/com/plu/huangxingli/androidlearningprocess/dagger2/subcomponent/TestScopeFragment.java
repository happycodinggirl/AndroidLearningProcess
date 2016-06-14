package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import javax.inject.Inject;

/**
 * Created by lily on 16-6-13.
 */
public class TestScopeFragment extends Fragment {

    @Inject Teacher teacher;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.linear_fragment_container,container,false);
        textView = (TextView) view.findViewById(R.id.tvDes);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentComponent fragmentComponent=DaggerFragmentComponent.builder().fragmentMould(new FragmentMould()).build();
      //  SubFragmentComponent subFragmentComponent=fragmentComponent.provideSubFragmentComponent();
        //subFragmentComponent.inject(this);
        fragmentComponent.inject(this);
       // fragmentComponent.inject(this);
        PluLogUtil.eLog("-----teacher is "+teacher+" name is "+teacher.getName()+"  teacher lover is "+teacher.getLover());
        teacher.setName("lily teacher");
        textView.setText(teacher.getName());



    }
}
