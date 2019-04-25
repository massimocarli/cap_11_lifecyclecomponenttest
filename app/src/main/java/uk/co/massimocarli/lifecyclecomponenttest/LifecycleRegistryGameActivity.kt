package uk.co.massimocarli.lifecyclecomponenttest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.android.synthetic.main.activity_lifecycle_registry_test.*
import kotlinx.android.synthetic.main.content_lifecycle_registry_test.*


class LifecycleRegistryGameActivity : AppCompatActivity(), LifecycleObserver {

    lateinit var lifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_registry_test)
        setSupportActionBar(toolbar)

        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.addObserver(this)

        markStateButton.setOnClickListener {
            val spinnerState = stateSpinner.selectedItem.toString()
            val selectedState = Lifecycle.State.valueOf(spinnerState)
            lifecycleRegistry.markState(selectedState)
        }

        handleLifecycleButton.setOnClickListener {
            val spinnerEvent = eventSpinner.selectedItem.toString()
            val selectedEvent = Lifecycle.Event.valueOf(spinnerEvent)
            lifecycleRegistry.handleLifecycleEvent(selectedEvent)
        }
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun showEvent() {
        Toast.makeText(this, "Event: ${lifecycleRegistry.currentState.name}", Toast.LENGTH_SHORT).show()
    }
}
