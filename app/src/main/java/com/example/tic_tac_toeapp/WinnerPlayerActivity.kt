package com.example.tic_tac_toeapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_winner_player.*
import kotlin.system.exitProcess

class WinnerPlayerActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
         var scoreCountP1 = 0

        @JvmStatic
         var scoreCountP2 = 0
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner_player)

        animationView.playAnimation()

        val sharedPreferences = getSharedPreferences(PlayerNamesActivity().sharedPreferencesName , MODE_PRIVATE)

        val i = intent
        if(i.getStringExtra("playerWin") != null){
            val nameWinPlayer =  i.getStringExtra("playerWin")
            tv_mesWinner.visibility = View.VISIBLE
            tv_playerWin.text = "$nameWinPlayer "
        }



        btn_playAgain.setOnClickListener{
            val i = Intent(this , MainActivity::class.java)
            startActivity(i)
        }


        btn_newTurn.setOnClickListener{
            scoreCountP1 = 0
            scoreCountP2 = 0
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            val i = Intent(this , PlayerNamesActivity::class.java)
            startActivity(i)
        }


        btn_exit.setOnClickListener{
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            Toast.makeText(this, "Thank You",Toast.LENGTH_LONG).show()

//            moveTaskToBack(true);
//            exitProcess(-1)
               // OR
          finishAffinity()  // this end application and is best
        }


    }
}
