package eu.example.thehangman

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatImageButton
import java.util.Locale

class Menu : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        var langSettings = "CZE"
        val newGameButton = findViewById<Button>(R.id.newGame)
        newGameButton.setOnClickListener{
            val intent = Intent(this, GameSettings::class.java)
            startActivity(intent)
        }
        val languageButton = findViewById<AppCompatImageButton>(R.id.language)
        languageButton.setOnClickListener{
            var locale : Locale
            if (langSettings == "CZE"){
                languageButton.setImageResource(R.drawable.eng)
                locale = Locale("cs")
                langSettings = "ENG"
                var res = resources
                var dm = res.displayMetrics
                var conf = res.configuration
                conf.locale = locale
                res.updateConfiguration(conf, dm)
                var refresh = Intent(Menu@this, Menu::class.java)
                startActivity(refresh)
            }else{
                languageButton.setImageResource(R.drawable.cze)
                locale = Locale("en")
                langSettings = "CZE"
                var res = resources
                var dm = res.displayMetrics
                var conf = res.configuration
                conf.locale = locale
                res.updateConfiguration(conf, dm)
                var refresh = Intent(Menu@this, Menu::class.java)
                startActivity(refresh)
            }

        }
    }
}