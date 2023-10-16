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

        val languageButton = findViewById<AppCompatImageButton>(R.id.language)
        println(resources.configuration.locales.get(0))

        languageButton.setOnClickListener{
            println(Locale.getDefault().displayLanguage)
            var locale : Locale
            if (resources.configuration.locales.get(0) == Locale("en")){
                locale = Locale("cs")
                var res = resources
                var dm = res.displayMetrics
                var conf = res.configuration
                conf.locale = locale
                res.updateConfiguration(conf, dm)
                var refresh = Intent(Menu@this, Menu::class.java)
                startActivity(refresh)
                languageButton.setImageResource(R.drawable.eng)
            }else{
                languageButton.setImageResource(R.drawable.cze)
                locale = Locale("en")
                var res = resources
                var dm = res.displayMetrics
                var conf = res.configuration
                conf.locale = locale
                res.updateConfiguration(conf, dm)
                var refresh = Intent(Menu@this, Menu::class.java)
                startActivity(refresh)
                languageButton.setImageResource(R.drawable.cze)
            }
        }
    }
}