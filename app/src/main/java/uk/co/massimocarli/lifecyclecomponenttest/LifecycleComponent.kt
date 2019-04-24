package uk.co.massimocarli.lifecyclecomponenttest

import android.os.Handler
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent


class MyLifecycleObserver(val lifecycle: Lifecycle? = null) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartService() {
        // Do something when you start
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopService() {
        // Do something when you stop
    }
}

class MockLifecycleObserver(
    val handler: Handler,
    val lifecycle: Lifecycle,
    val listener: StartedServiceCallback<String>
) : LifecycleObserver {

    var started = false
    val name = "SERVICE_${serviceCount++}"

    private fun sendEvent(event: String): String? {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            listener?.onEvent(event)
            return event
        }
        return null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartService() {
        if (!started) {
            started = true
            Thread({
                var counter = 0
                while (started) {
                    handler.postDelayed({
                        sendEvent("EVENT $counter from $name")?.let {
                            counter++
                        }
                    }, randomTime(min = 50))
                    Thread.sleep(randomTime(max = 50))
                }
            }, name).start()
            sendEvent("${name}  STARTED")
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopService() {
        sendEvent("${name}  STOPPED")
        started = false
    }
}
