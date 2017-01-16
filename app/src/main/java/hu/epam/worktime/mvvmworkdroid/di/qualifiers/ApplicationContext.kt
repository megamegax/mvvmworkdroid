package hu.epam.worktime.mvvmworkdroid.di.qualifiers

import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier
import kotlin.annotation.Retention

/**
 * Created by Mihaly_Hunyady on 2016. 11. 24..
 */

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext
