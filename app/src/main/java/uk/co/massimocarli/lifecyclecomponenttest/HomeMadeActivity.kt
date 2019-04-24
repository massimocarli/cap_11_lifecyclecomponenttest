package uk.co.massimocarli.lifecyclecomponenttest

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class HomeMadeActivity : AppCompatActivity() {

    val serviceCallback = object : StartedServiceCallback<String> {
        override fun onEvent(event: String) {
            logHomeMade("Event $event Received!")
        }
    }

    val handler = Handler()

    val startedService1 = MockStartedService(handler, serviceCallback)
    val startedService2 = MockStartedService(handler, serviceCallback)
    val startedService3 = MockStartedService(handler, serviceCallback)

    override fun onStart() {
        super.onStart()
        startedService1.start()
        startedService2.start()
        startedService3.start()
    }

    override fun onStop() {
        startedService3.stop()
        startedService2.stop()
        startedService1.stop()
        super.onStop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
