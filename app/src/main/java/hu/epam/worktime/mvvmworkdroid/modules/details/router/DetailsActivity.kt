package hu.epam.worktime.mvvmworkdroid.modules.details.router

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import hu.epam.worktime.mvvmworkdroid.R
import hu.epam.worktime.mvvmworkdroid.databinding.ActivityDetailsBinding
import hu.epam.worktime.mvvmworkdroid.di.details.DetailsActivityComponent
import hu.epam.worktime.mvvmworkdroid.modules.details.viewmodel.DetailsViewModel
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), DetailsRouter {
    @Inject
    lateinit var detailsViewModel: DetailsViewModel

    @Inject
    lateinit var gson: Gson

    private var workTime: WorkTime? = null
        set(value) {
            field = value
            value?.let {
                detailsViewModel.setSelectedWorkTime(value)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        val viewDataBinding = DataBindingUtil.setContentView<ActivityDetailsBinding>(this, R.layout.activity_details)
        viewDataBinding.viewModel = detailsViewModel
        loadExtras()
    }

    private fun inject() {
        val component = DetailsActivityComponent.buildComponent(this)
        component.inject(this)
    }

    override fun goBack() {
        onBackPressed()
    }

    fun loadExtras() {
        val intent = intent
        val jsonWorkTime = intent.getStringExtra(WORK_ITEM)
        workTime = gson.fromJson(jsonWorkTime, WorkTime::class.java)
    }

    companion object {

        val WORK_ITEM = "workItem"
    }
}
