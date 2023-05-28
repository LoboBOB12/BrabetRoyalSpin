package app.lobo.brabetroyalspin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val buttonView1 = findViewById<Button>(R.id.button2)
        val butoneView2 = findViewById<Button>(R.id.button3)

        buttonView1.setOnClickListener {
            val intent = Intent(this, level::class.java)
            startActivity(intent)
        }

        butoneView2.setOnClickListener {
            val intent = Intent(this, policy::class.java)
            startActivity(intent)
        }
    }


}