package uk.co.massimocarli.lifecyclecomponenttest

import android.content.Intent
import android.os.Handler
import androidx.lifecycle.LifecycleService

class MyLifecycleService : LifecycleService() {

    val serviceCallback = object : StartedServiceCallback<String> {
        override fun onEvent(event: String) {
            logHomeMade("Event $event Received!")
        }
    }

    val handler = Handler()

    override fun onCreate() {
        super.onCreate()
        with(getLifecycle()) {
            addObserver(MockLifecycleObserver(handler, this, serviceCallback))
            addObserver(MockLifecycleObserver(handler, this, serviceCallback))
            addObserver(MockLifecycleObserver(handler, this, serviceCallback))
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.postDelayed({
            serviceCallback.onEvent("SERVICE ENDED!")
            stopSelf()
        }, 1000L)
        return super.onStartCommand(intent, flags, startId)
    }
}