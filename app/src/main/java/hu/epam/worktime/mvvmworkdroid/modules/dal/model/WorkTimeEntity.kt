package hu.epam.worktime.mvvmworkdroid.modules.dal.model

import io.realm.RealmList
import io.realm.RealmObject
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime


open class WorkTimeEntity() : RealmObject() {
    lateinit var date: String
    lateinit var arrive: String
    lateinit var leave: String
    lateinit var dinner: PairEntity
    lateinit var offtime: RealmList<PairEntity>
    lateinit var nettoWork: String

    constructor(date: String, arrive: String, leave: String, dinner: PairEntity, offtime: RealmList<PairEntity>, nettoWork: String) : this() {
        this.date = date
        this.arrive = arrive
        this.leave = leave
        this.dinner = dinner
        this.offtime = offtime
        this.nettoWork = nettoWork
    }
}