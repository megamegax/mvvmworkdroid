package hu.epam.worktime.mvvmworkdroid.modules.services.models.preferences

import com.chibatching.kotpref.KotprefModel

/**
 * Created by Mihaly_Hunyady on 2016. 11. 18..
 */
class UserProfile : KotprefModel() {
    var name: String by stringPrefVar()
    var loggedIn: Boolean by booleanPrefVar(false)
    var id: Int by intPrefVar(2)
    var hasDinner: Boolean by booleanPrefVar(true)
}