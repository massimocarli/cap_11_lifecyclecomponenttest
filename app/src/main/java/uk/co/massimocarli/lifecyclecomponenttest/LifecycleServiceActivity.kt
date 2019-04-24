package uk.co.massimocarli.lifecyclecomponenttest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_lifecycle_service.*

/**
 * Activity we use in order to test LifecycleService
 */
class LifecycleServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_service)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            startService(Intent(this, MyLifecycleService::class.java))
        }
    }

}