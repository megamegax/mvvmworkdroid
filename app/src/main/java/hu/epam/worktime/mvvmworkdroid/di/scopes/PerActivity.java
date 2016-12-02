package hu.epam.worktime.mvvmworkdroid.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Annotation class to grant only one working-instance in the application.
 * Created by Gergely_Levente_Pinter on 8/18/2016.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
