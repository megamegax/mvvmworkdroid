package hu.epam.worktime.mvvmworkdroid.common.extensions

import android.app.Activity
import android.content.Intent
import android.view.View
import android.support.v4.app.ActivityOptionsCompat
import hu.epam.worktime.mvvmworkdroid.modules.details.router.DetailsActivity
import android.support.v4.util.Pair

/**
 *
 *
 * Created by Mihaly_Hunyady on 2017. 02. 03..
 */
inline fun <reified T : Activity> Activity.navigate(param: String? = null, sharedViews: Array<Pair<View, String>> = emptyArray()) {
    val intent = Intent(this, T::class.java)
    intent.putExtra(DetailsActivity.WORK_ITEM, param)
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        // Do something for lollipop and above versions
        val transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *sharedViews)
        startActivity(intent, transitionActivityOptions.toBundle())
    } else {
        // do something for phones running an SDK before lollipop
        startActivity(intent)
    }
}

inline fun <reified T : Activity> Activity.navigate() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

inline fun <reified T : Activity> Activity.navigateForResult(requestCode: Int = 0) {
    val intent = Intent(this, T::class.java)
    startActivityForResult(intent, requestCode)
}