package eu.example.thehangman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlin.system.exitProcess

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val newGameButton = findViewById<Button>(R.id.newGame)
        newGameButton.setOnClickListener{
            val intent = Intent(this, GameSettings::class.java)
            startActivity(intent)
        }
        val exitButton = findViewById<Button>(R.id.exit)
        exitButton.setOnClickListener{
            this@Menu.finish()
            exitProcess(0)
        }
    }
}