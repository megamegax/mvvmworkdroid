package hu.epam.worktime.mvvmworkdroid.modules.dal.model

import hu.epam.worktime.mvvmworkdroid.modules.services.models.Event
import io.realm.RealmObject

open class EventEntity() : RealmObject() {
    var userRef: Int = 0
    var event: Int = Event.ARRIVE
    lateinit var date: String

    constructor(userRef: Int, event: Int, date: String) : this() {
        this.userRef = userRef
        this.event = event
        this.date = date
    }
}