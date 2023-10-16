package eu.example.thehangman

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import java.io.BufferedReader
import java.io.InputStreamReader

class Game : AppCompatActivity() {
    // Setting up timer
    private var isRunning = false
    var timerSeconds = 0
    val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            timerSeconds ++
            val hours = timerSeconds / 3600
            val minutes = (timerSeconds % 3600) / 60
            val seconds = timerSeconds % 60
            val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            val timer = findViewById<TextView>(R.id.timer)
            timer.text = time
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val secret = pickWord()
        println(secret)
        val secretCharArray = secret.toCharArray().distinct()
        val guessedLetters = mutableListOf<Char>()
        val secretTV = findViewById<TextView>(R.id.secret)
        secretTV.text = " _ ".repeat(secret.length)
        val lifeCounterTv = findViewById<TextView>(R.id.life_counter)
        var lifeCount = setDifficulty()
        lifeCounterTv.text = "❤️".repeat(setDifficulty())
        val q = findViewById<Button>(R.id.q)
        val w = findViewById<Button>(R.id.w)
        val e = findViewById<Button>(R.id.e)
        val r = findViewById<Button>(R.id.r)
        val t = findViewById<Button>(R.id.t)
        val z = findViewById<Button>(R.id.z)
        val u = findViewById<Button>(R.id.u)
        val i = findViewById<Button>(R.id.i)
        val o = findViewById<Button>(R.id.o)
        val p = findViewById<Button>(R.id.p)
        val a = findViewById<Button>(R.id.a)
        val s = findViewById<Button>(R.id.s)
        val d = findViewById<Button>(R.id.d)
        val f = findViewById<Button>(R.id.f)
        val g = findViewById<Button>(R.id.g)
        val h = findViewById<Button>(R.id.h)
        val j = findViewById<Button>(R.id.j)
        val k = findViewById<Button>(R.id.k)
        val l = findViewById<Button>(R.id.l)
        val y = findViewById<Button>(R.id.y)
        val x = findViewById<Button>(R.id.x)
        val c = findViewById<Button>(R.id.c)
        val v = findViewById<Button>(R.id.v)
        val b = findViewById<Button>(R.id.b)
        val n = findViewById<Button>(R.id.n)
        val m = findViewById<Button>(R.id.m)

        val buttons =
            arrayOf(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z)

        val menuButton = findViewById<Button>(R.id.menu)
        menuButton.setOnClickListener{
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        fun checkWinLoss(){
            val winDialog = Dialog(this)
            winDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            winDialog.setCancelable(false)
            winDialog.setContentView(R.layout.win_dialog)
            winDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val winOKButton = winDialog.findViewById<AppCompatImageButton>(R.id.win_dialog_cancel)
            winOKButton.setOnClickListener {
                winDialog.dismiss()
            }
            val winRepeatButton = winDialog.findViewById<AppCompatButton>(R.id.win_new_repeat)
            val newDiff = intent.getStringExtra("difficulty")
            val newLength = intent.getStringExtra("length")
            winRepeatButton.setOnClickListener {
                val intent = Intent(this, Game::class.java).also {
                    it.putExtra("length", newLength)
                    it.putExtra("difficulty", newDiff)
                }
                startActivity(intent)
            }
            val backSettings = winDialog.findViewById<AppCompatButton>(R.id.win_back_to_settings)
            backSettings.setOnClickListener {
                val intent = Intent(this, GameSettings::class.java)
                startActivity(intent)
            }
            val lossDialog = Dialog(this)
            lossDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            lossDialog.setCancelable(false)
            lossDialog.setContentView(R.layout.loss_dialog)
            lossDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val lossOKButton = lossDialog.findViewById<AppCompatImageButton>(R.id.loss_dialog_cancel)
            lossOKButton.setOnClickListener {
                lossDialog.dismiss()
            }
            val lossRepeatButton = lossDialog.findViewById<AppCompatButton>(R.id.loss_new_repeat)
            val newDiffLoss = intent.getStringExtra("difficulty")
            val newLengthLoss = intent.getStringExtra("length")
            lossRepeatButton.setOnClickListener {
                val intent = Intent(this, Game::class.java).also {
                    it.putExtra("length", newLengthLoss)
                    it.putExtra("difficulty", newDiffLoss)
                }
                startActivity(intent)
            }
            val backSettingsLoss = lossDialog.findViewById<AppCompatButton>(R.id.loss_back_to_settings)
            backSettingsLoss.setOnClickListener {
                val intent = Intent(this, GameSettings::class.java)
                startActivity(intent)
            }
            if ("❤️" !in lifeCounterTv.text){
                val secretRevealLoss = lossDialog.findViewById<TextView>(R.id.secretRevealLoss)
                val revealText = "${getString(R.string.secret_reveal)}\n\n$secret"
                secretRevealLoss.text = revealText
                handler.removeCallbacks(runnable)
                isRunning = false
                lifeCount -= 1
                val failSound = MediaPlayer.create(this, R.raw.fail)
                failSound.start()
                lossDialog.show()
            }else if(guessedLetters.size == secretCharArray.size){
                val secretRevealWin = winDialog.findViewById<TextView>(R.id.secretRevealWin)
                handler.removeCallbacks(runnable)
                isRunning = false
                val timer = findViewById<TextView>(R.id.timer)
                val seconds = timerSeconds % 60
                val revealText = "${getString(R.string.you_win_reveal)}\n$secret\n\n${getString(R.string.total_time)}\n${timer.text}\n\n${getString(R.string.your_score_is)}\n${countScore(seconds, setDifficulty(), setLength(), lifeCount).toString()}"
                secretRevealWin.text = revealText
                val fanfareSound = MediaPlayer.create(this, R.raw.fanfare)
                fanfareSound.start()
                winDialog.show()
            }
        }

        fun checkInput(secret: String, guess: Char) {
            checkWinLoss()
            if (guess in secret) {
                val ringSound = MediaPlayer.create(this, R.raw.bell)
                ringSound.start()
                guessedLetters.add(guess)
                var toDisplay = ""
                for (char in secret){
                    toDisplay += if (char in guessedLetters){
                        "$char"
                    }else {
                        " _ "
                    }
                }
                secretTV.text = toDisplay
                checkWinLoss()
            }else{
                val duckSound = MediaPlayer.create(this, R.raw.duck)
                duckSound.start()
                lifeCounterTv.text = (lifeCounterTv.text as String).replaceFirst("❤️", "🖤")
                checkWinLoss()
            }
        }

        for (button in buttons) {
            button.setOnClickListener {
                if (!isRunning){
                    handler.postDelayed(runnable, 1000)
                    isRunning = true
                }
                checkInput(secret, button.text[0])
                println(button.text[0])
                button.apply {
                    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }
                button.setBackgroundResource(R.drawable.disabled_btn)
                button.isEnabled = false
            }
        }
    }
    private fun setDifficulty(): Int {
        when (intent.getStringExtra("difficulty")) {
            "0" -> {
                return 10
            }
            "1" -> {
                return 8
            }
            "2" -> {
                return 6
            }
        }
        return 8
    }
    private fun setLength(): Int {
        return intent.getStringExtra("length")!!.toInt()
    }

    private fun pickWord(): String {
        val nouns = InputStreamReader(assets.open("nouns.csv"))
        val readerNouns = BufferedReader(nouns)
        val nounList = readerNouns.readLines()
        val mutableNounList = nounList.filter { it.length == setLength() }.toMutableList()
        return mutableNounList.random().uppercase()
    }

    fun countScore(time:Int, difficulty:Int, length:Int, livesLeft:Int): Int{
        val score = (livesLeft * 50) - time - (length * 5) - (difficulty * 10)
        return score
    }
}