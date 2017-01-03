package hu.epam.worktime.mvvmworkdroid.modules.dal.model

import io.realm.RealmObject

/**
 * Created by hunyadym on 2017. 01. 03..
 */
open class PairEntity() : RealmObject() {
    lateinit var first: String
    lateinit var second: String

    constructor(first: String, second: String) : this() {
        this.first = first
        this.second = second
    }

}