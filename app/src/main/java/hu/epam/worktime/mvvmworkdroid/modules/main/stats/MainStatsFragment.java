package hu.epam.worktime.mvvmworkdroid.modules.main.stats;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import hu.epam.worktime.mvvmworkdroid.R;
import hu.epam.worktime.mvvmworkdroid.databinding.ActivityMainStatsBinding;
import hu.epam.worktime.mvvmworkdroid.di.main.MainActivityComponent;
import hu.epam.worktime.mvvmworkdroid.modules.main.stats.model.MainStatsModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.router.MainActivity;
import hu.epam.worktime.mvvmworkdroid.modules.main.stats.viewmodel.MainStatViewModel;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 12. 12..
 */

public class MainStatsFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ActivityMainStatsBinding binding = DataBindingUtil.inflate(inflater, R.layout.activity_main_stats, container, false);
        binding.setViewModel(new MainStatViewModel(new MainStatsModel()));
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