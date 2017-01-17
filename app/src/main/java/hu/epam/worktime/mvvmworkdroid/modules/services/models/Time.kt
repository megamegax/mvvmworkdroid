package hu.epam.worktime.mvvmworkdroid.modules.services.models

/**
 *
 *
 * Created by Mihaly_Hunyady on 2017. 01. 17..
 */
class Time(var hour: Long, var minute: Long, var second: Long) {

    fun plusHours(hour: Long): Time {
        this.hour += hour
        return this
    }

    fun plusMinutes(minute: Long): Time {
        val hours = minute / 60
        plusHours(hours)
        this.minute += minute - hours * 60
        if (this.minute > 59) {
            val hours = this.minute / 60
            plusHours(hours)
            this.minute = this.minute - hours * 60
        }
        return this
    }

    fun plusSeconds(second: Long): Time {
        val minutes = second / 60
        plusMinutes(minutes)
        this.second += second - minutes * 60
        if (this.second > 59) {
            val minutes = this.second / 60
            plusMinutes(minutes)
            this.second = this.second - minutes * 60
        }
        return this
    }

    override fun toString(): String {
        return "${hour.asTime()}:${minute.asTime()}:${second.asTime()}"
    }

    fun Long.asTime(): String {
        if (this < 9) {
            return "0$this"
        } else {
            return this.toString()
        }
    }

    companion object {
        fun parse(parsableTime: String): Time {
            val times = parsableTime.split(":")
            val hour = times[0].toLong()
            val minute = times[1].toLong()
            val second = times[2].toLong()
            return Time(hour, minute, second)

        }
    }
}