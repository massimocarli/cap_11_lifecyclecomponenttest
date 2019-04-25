package uk.co.massimocarli.lifecyclecomponenttest

import android.os.Handler
import android.util.Log
import kotlin.random.Random
import androidx.lifecycle.Lifecycle
import androidx.annotation.NonNull
import androidx.lifecycle.LifecycleObserver





/**
 * Abstraction for a service that can be started and stopped
 */
interface StartedService {
    /**
     * Starts the service
     */
    fun start()

    /**
     * Stops the service
     */
    fun stop()
}

/**
 * This is the interface in order to implement a callback
 */
interface StartedServiceCallback<T> {

    /**
     * Invoked on every event from the service
     */
    fun onEvent(event: T)
}

/**
 * Implementation of the StartedService which manages a StartedServiceCallback
 */
abstract class StartedServiceSource<T>(
    val listener: StartedServiceCallback<T>? = null
) : StartedService {

    protected fun notifyEvent(event: T) {
        listener?.onEvent(event)
    }
}

fun randomTime(min: Long = 0, max: Long = 300) = min + Math.abs(Random.nextLong(max))

fun logHomeMade(msg: String) {
    Log.d("HomeMadeLifecycle:", msg)
}

fun logProcess(msg: String) {
    Log.d("ProcessLifecycle:", msg)
}

var serviceCount = 0

class MockStartedService(val handler: Handler, listener: StartedServiceCallback<String>) :
    StartedServiceSource<String>(listener) {

    var started = false
    val name = "SERVICE_${serviceCount++}"

    override fun start() {
        started = true;
        Thread({
            var counter = 0
            while (started) {
                handler.postDelayed({
                    // First version of the notification
                    handler.postDelayed({
                        val msg = "EVENT $counter from $name"
                        listener?.onEvent(msg)
                        counter++
                    }, randomTime(min = 1000))
                    Thread.sleep(randomTime())
                    // Second version for notification
//                    sendEvent("EVENT $counter from $name")?.let {
//                        counter++
//                    }
                }, randomTime(1000))
                Thread.sleep(randomTime())
            }
        }, name).start()
        sendEvent("${name}  STARTED")
    }

    override fun stop() {
        sendEvent("${name}  STOPPED")
        started = false
    }

    private fun sendEvent(event: String): String? {
        if (started) {
            listener?.onEvent(event)
            return event
        }
        return null
    }
}


