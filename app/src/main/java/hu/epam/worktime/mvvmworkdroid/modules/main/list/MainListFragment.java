package hu.epam.worktime.mvvmworkdroid.modules.main.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import hu.epam.worktime.mvvmworkdroid.R;
import hu.epam.worktime.mvvmworkdroid.databinding.ActivityMainListBinding;
import hu.epam.worktime.mvvmworkdroid.di.main.MainActivityComponent;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.router.MainActivity;
import hu.epam.worktime.mvvmworkdroid.modules.main.list.viewmodel.MainListViewModel;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 12. 12..
 */

public class MainListFragment extends Fragment {

    @Inject
    MainListViewModel mainListViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainListViewModel = ((MainActivity) getActivity()).getMainListViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ActivityMainListBinding binding = DataBindingUtil.inflate(inflater, R.layout.activity_main_list, container, false);
        binding.setViewModel(mainListViewModel);
        return binding.getRoot();
    }

    private void inject() {
        MainActivityComponent component = MainActivityComponent.Injector.buildComponent(getMainActivity());
        component.inject(getMainActivity());
    }

    private MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }
}