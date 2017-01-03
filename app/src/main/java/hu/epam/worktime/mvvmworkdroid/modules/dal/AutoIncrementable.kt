package hu.epam.worktime.mvvmworkdroid.modules.dal

import io.realm.Realm

/**
 * Helper interface for creating autoincrement ID'ss
 * Created by hunyadym on 2016. 12. 24..
 */
interface AutoIncrementable {
    fun setPrimaryKey(primaryKey: Int)
    fun getNextPrimaryKey(realm: Realm): Int?
}