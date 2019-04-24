package uk.co.massimocarli.lifecyclecomponenttest

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner

/**
 * Class which implements the LifecycleObserver for the ProcessLifecycleOwner
 */
class LifecycleApp : Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartedEvent() {
        logProcess("LifecycleApp: ON_START")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStoppedEvent() {
        logProcess("LifecycleApp: ON_STOP")
    }
}