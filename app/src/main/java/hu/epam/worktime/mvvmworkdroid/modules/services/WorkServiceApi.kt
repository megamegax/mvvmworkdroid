package hu.epam.worktime.mvvmworkdroid.modules.services


import hu.epam.worktime.mvvmworkdroid.modules.services.models.Event
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkDay
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import rx.Observable

interface WorkServiceApi {

    @GET("worktime/{userId}")
    fun workTimes(@Path("userId") id: Int): Observable<List<WorkTime>>

    @POST("time")
    fun addTimeEvent(@Body event: Event): Call<Void>

    @GET("workdays")
    fun workDays(): Observable<List<WorkDay>>
}