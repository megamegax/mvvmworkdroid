package hu.hanprog.worktime.service

import hu.hanprog.worktime.service.model.WorkDay
import hu.hanprog.worktime.service.timeevent.model.Event
import hu.hanprog.worktime.service.work.model.WorkTime
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WorkServiceApi {

    @GET("worktime/{userId}")
    fun workTimes(@Path("userId") id: Int): Call<List<WorkTime>>

    @POST("time")
    fun addTimeEvent(@Body event: Event): Call<Void>

    @GET("workdays")
    fun workDays():Call<List<WorkDay>>
}