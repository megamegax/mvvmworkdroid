package hu.hanprog.worktime.service.timeevent.model

data class Event(val userRef: Int, val event: Int, val date: String) {
    companion object {
        val ARRIVE: Int = 1
        val LEAVE: Int = 2
        val DINNER_BEGIN = 3
        val DINNER_END = 4
        val OTHER_LEAVE = 5
        val OTHER_ARRIVE = 6
    }

}