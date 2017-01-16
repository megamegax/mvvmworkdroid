package hu.epam.worktime.mvvmworkdroid.modules.save.router

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import hu.epam.worktime.mvvmworkdroid.R
import hu.epam.worktime.mvvmworkdroid.databinding.ActivityMainBinding
import hu.epam.worktime.mvvmworkdroid.databinding.ActivitySaveTimeBinding
import hu.epam.worktime.mvvmworkdroid.di.main.MainActivityComponent
import hu.epam.worktime.mvvmworkdroid.di.save.SaveActivityComponent
import hu.epam.worktime.mvvmworkdroid.modules.save.viewmodel.SaveViewModel
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi

import javax.inject.Inject

class SaveActivity : AppCompatActivity(), SaveRouter {

    @Inject
    var saveViewModel: SaveViewModel? = null

    @Inject
    var workServiceApi: WorkServiceApi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        val viewDataBinding = DataBindingUtil.setContentView<ActivitySaveTimeBinding>(this, R.layout.activity_save_time)
        viewDataBinding.viewModel = saveViewModel

    }

    override fun goBack() {
        this.finish()
    }

    override fun onStart() {
        super.onStart()
        saveViewModel!!.onStart()
    }

    override fun onStop() {
        super.onStop()
        saveViewModel!!.onStop()
    }

    private fun inject() {
        val component = SaveActivityComponent.Injector.buildComponent(this)
        component.inject(this)
    }
}
