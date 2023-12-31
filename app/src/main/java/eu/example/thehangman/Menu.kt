package eu.example.thehangman

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageButton
import java.util.Locale

class Menu : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val newGameButton = findViewById<Button>(R.id.newGame)
        newGameButton.setOnClickListener{
            val intent = Intent(this, GameSettings::class.java)
            startActivity(intent)
        }
        setFlag()
        val languageButton = findViewById<AppCompatImageButton>(R.id.language)

        languageButton.setOnClickListener{
            var locale : Locale
            if (resources.configuration.locales.get(0).toString() == "en" || resources.configuration.locales.get(0).toString() == "en_US"){
                locale = Locale("cs")
                resources.configuration.setLocale(locale)
                resources.updateConfiguration(resources.configuration, resources.displayMetrics)
                startActivity(Intent(this, Menu::class.java))
            }else{
                languageButton.setImageResource(R.drawable.cze)
                locale = Locale("en")
                resources.configuration.setLocale(locale)
                resources.updateConfiguration(resources.configuration, resources.displayMetrics)
                startActivity(Intent(this, Menu::class.java))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setFlag(){
        val languageButton = findViewById<AppCompatImageButton>(R.id.language)
        if (resources.configuration.locales.get(0).toString()  == "cs"){
            languageButton.setImageResource(R.drawable.eng)
        }else{
            languageButton.setImageResource(R.drawable.cze)
        }
    }
}