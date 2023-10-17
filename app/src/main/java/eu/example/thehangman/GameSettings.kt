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
        val valuesDiff = arrayOf(getString(R.string.diff_easy), getString(R.string.diff_medium), getString(R.string.diff_hard))
        difficultyPicker.displayedValues = valuesDiff
        difficultyPicker.minValue = 0
        difficultyPicker.maxValue = valuesDiff.size - 1
        difficultyPicker.value = 1


        val lengthPicker = findViewById<NumberPicker>(R.id.lengthPicker)
        val valuesLength = arrayOf(getString(R.string.length_short), getString(R.string.length_medium), getString(R.string.length_long), getString(R.string.length_very_long))
        lengthPicker.displayedValues = valuesLength
        lengthPicker.minValue = 0
        lengthPicker.maxValue = valuesLength.size - 1
        lengthPicker.value = 1

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