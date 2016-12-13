package hu.epam.worktime.mvvmworkdroid.modules.main.pages;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import hu.epam.worktime.mvvmworkdroid.R;
import hu.epam.worktime.mvvmworkdroid.databinding.ActivityMainListBinding;
import hu.epam.worktime.mvvmworkdroid.databinding.ActivityMainStatsBinding;
import hu.epam.worktime.mvvmworkdroid.modules.main.model.MainListModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.model.MainStatsModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.router.MainActivity;
import hu.epam.worktime.mvvmworkdroid.modules.main.viewmodel.MainListViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.viewmodel.MainStatViewModel;

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
}
