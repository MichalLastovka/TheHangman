package eu.example.thehangman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker

class GameSettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_settings)

        val difficultyPicker = findViewById<NumberPicker>(R.id.difficultyPicker)
        val values = arrayOf(getString(R.string.diff_easy), getString(R.string.diff_medium), getString(R.string.diff_hard))
        difficultyPicker.displayedValues = values
        difficultyPicker.minValue = 0
        difficultyPicker.maxValue = values.size - 1
        difficultyPicker.value = 1


        val lengthPicker = findViewById<NumberPicker>(R.id.lengthPicker)
        lengthPicker.minValue = 4
        lengthPicker.maxValue = 14
        lengthPicker.value = 8

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener{
            val intent = Intent(this, Game::class.java).also {
                it.putExtra("length", lengthPicker.value.toString())
                it.putExtra("difficulty", difficultyPicker.value.toString())
            }
            startActivity(intent)
        }

        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener{
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
    }

}