package hu.epam.worktime.mvvmworkdroid.common.extensions

import android.app.Activity
import android.content.Intent
import android.view.View
import android.app.ActivityOptions
import hu.epam.worktime.mvvmworkdroid.modules.details.router.DetailsActivity


/**
 *
 *
 * Created by Mihaly_Hunyady on 2017. 02. 03..
 */
inline fun <reified T : Activity> Activity.navigate(param: String,
                                                    sharedView: View? = null,
                                                    transitionName: String? = null) {
    val intent = Intent(this, T::class.java)
    intent.putExtra(DetailsActivity.WORK_ITEM, param)
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        // Do something for lollipop and above versions
        val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this, sharedView, transitionName)
        startActivity(intent, transitionActivityOptions.toBundle())
    } else {
        // do something for phones running an SDK before lollipop
        startActivity(intent)
    }

}