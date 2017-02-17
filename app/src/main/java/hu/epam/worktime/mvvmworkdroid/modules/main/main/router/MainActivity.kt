package hu.epam.worktime.mvvmworkdroid.modules.main.main.router

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.gson.Gson
import hu.epam.worktime.mvvmworkdroid.R
import hu.epam.worktime.mvvmworkdroid.common.extensions.navigate
import hu.epam.worktime.mvvmworkdroid.common.extensions.navigateForResult
import hu.epam.worktime.mvvmworkdroid.databinding.ActivityMainBinding
import hu.epam.worktime.mvvmworkdroid.di.main.MainActivityComponent
import hu.epam.worktime.mvvmworkdroid.modules.details.router.DetailsActivity
import hu.epam.worktime.mvvmworkdroid.modules.main.main.viewmodel.MainViewModel
import hu.epam.worktime.mvvmworkdroid.modules.save.router.SaveActivity
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainRouter {

    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        val viewDataBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        viewDataBinding.viewModel = mainViewModel
        val toolbar = viewDataBinding.toolbar
        val tabLayout = viewDataBinding.tabs
        setSupportActionBar(toolbar)
        val pager = viewDataBinding.viewpager

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_stats)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_list)))
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

    private fun inject() {
        val component = MainActivityComponent.buildComponent(this)
        component.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            mainViewModel.refresh()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun openDetails(sharedViews: Array<Pair<View, String>>, workTime: WorkTime) {
        navigate<DetailsActivity>(gson.toJson(workTime), sharedViews)
    }

    override fun openNewEntry() {
        navigateForResult<SaveActivity>()
    }

    override fun onStart() {
        super.onStart()
        mainViewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        mainViewModel.onStop()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            super.onActivityResult(requestCode, resultCode, data)
        }
        mainViewModel.refresh()
    }
}
