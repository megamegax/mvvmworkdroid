package hu.epam.worktime.mvvmworkdroid.modules.save.model

import java.io.IOException

import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDao
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi
import hu.epam.worktime.mvvmworkdroid.modules.services.models.Event

/**
 * Created by Mihaly_Hunyady on 2016. 12. 16..
 */

class SaveModel(private val workServiceApi: WorkServiceApi, private val workItemDao: WorkItemDao) {

    fun sendEvent(sendableEvent: Event) {
        try {
            workServiceApi.addTimeEvent(sendableEvent).execute().message()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
