package hu.epam.worktime.mvvmworkdroid.modules.details.router

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.google.gson.Gson

import javax.inject.Inject

import hu.epam.worktime.mvvmworkdroid.R
import hu.epam.worktime.mvvmworkdroid.databinding.ActivityDetailsBinding
import hu.epam.worktime.mvvmworkdroid.di.details.DetailsActivityComponent
import hu.epam.worktime.mvvmworkdroid.modules.details.viewmodel.DetailsViewModel
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime

class DetailsActivity : AppCompatActivity(), DetailsRouter {
    @Inject
    var detailsViewModel: DetailsViewModel? = null

    @Inject
    var gson: Gson? = null
    private var workTime: WorkTime? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        val viewDataBinding = DataBindingUtil.setContentView<ActivityDetailsBinding>(this, R.layout.activity_details)
        viewDataBinding.viewModel = detailsViewModel
        loadExtras()
        detailsViewModel!!.setSelectedWorkTime(workTime!!) //TODO never do this...
    }

    private fun inject() {
        val component = DetailsActivityComponent.Injector.buildComponent(this)
        component.inject(this)
    }

    override fun goBack() {
        onBackPressed()
    }

    fun loadExtras() {
        val intent = intent
        val jsonWorkTime = intent.getStringExtra(WORK_ITEM)
        workTime = gson!!.fromJson(jsonWorkTime, WorkTime::class.java)
    }

    companion object {

        val WORK_ITEM = "workItem"
    }
}
