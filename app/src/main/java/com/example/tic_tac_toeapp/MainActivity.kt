package com.example.tic_tac_toeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()
    private var activePlayer = 1
    private var cellCount = 9

    private var namePlayer1 = ""
    private var namePlayer2 = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences(PlayerNamesActivity().sharedPreferencesName , MODE_PRIVATE)

        namePlayer1 = sharedPreferences.getString("player1" , "noName")!!
        namePlayer2 = sharedPreferences.getString("player2" , "noName")!!

        tv_nameTurn.text = " $namePlayer1"

        tv_scoreName1.text = " $namePlayer1 : "
        tv_scoreName2.text = " $namePlayer2 : "
        tv_scoreNumber1.text = WinnerPlayerActivity.scoreCountP1.toString()
        tv_scoreNumber2.text = WinnerPlayerActivity.scoreCountP2.toString()
    }

    fun btnClicked(view: View) {
        val btnSelected = view as Button
        var cellId = 0

        when(btnSelected.id){
            R.id.button1->cellId = 1
            R.id.button2->cellId = 2
            R.id.button3->cellId = 3
            R.id.button4->cellId = 4
            R.id.button5->cellId = 5
            R.id.button6->cellId = 6
            R.id.button7->cellId = 7
            R.id.button8->cellId = 8
            R.id.button9->cellId = 9
        }
        playGame(cellId, btnSelected)
    }


    private fun playGame(cellId: Int, btnSelected: Button) {
        cellCount--

        if(activePlayer == 1){
            btnSelected.background = ContextCompat.getDrawable(this, R.drawable.ic_x)
            player1.add(cellId)
            activePlayer = 2
            tv_nameTurn.text = " $namePlayer2"
        }else{
            btnSelected.background = ContextCompat.getDrawable(this, R.drawable.ic_o)
            player2.add(cellId)
            activePlayer = 1
            tv_nameTurn.text = " $namePlayer1"
        }
        btnSelected.isEnabled = false
        checkWinner()
    }

    private fun checkWinner() {
        var winner = "no"

        if(cellCount==0){
            val intent = Intent(this , WinnerPlayerActivity::class.java)
            startActivity(intent)
        }

        // Check rows
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)) winner = "p1"
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)) winner = "p2"

        if(player1.contains(4) && player1.contains(5) && player1.contains(6)) winner = "p1"
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)) winner = "p2"

        if(player1.contains(7) && player1.contains(8) && player1.contains(9)) winner = "p1"
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)) winner = "p2"

        // Check columns
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)) winner = "p1"
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)) winner = "p2"

        if(player1.contains(2) && player1.contains(5) && player1.contains(8)) winner = "p1"
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)) winner = "p2"

        if(player1.contains(3) && player1.contains(6) && player1.contains(9)) winner = "p1"
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)) winner = "p2"

        // Diameter check
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)) winner = "p1"
        if(player2.contains(1) && player2.contains(5) && player2.contains(9)) winner = "p2"

        if(player1.contains(3) && player1.contains(5) && player1.contains(7)) winner = "p1"
        if(player2.contains(3) && player2.contains(5) && player2.contains(7)) winner = "p2"

         if(winner == "p1"){
             WinnerPlayerActivity.scoreCountP1 += 1
             val intent = Intent(this , WinnerPlayerActivity::class.java)
             intent.putExtra("playerWin" , namePlayer1)
             startActivity(intent)
         }

        if(winner == "p2"){
            WinnerPlayerActivity.scoreCountP2 += 1
            val intent = Intent(this , WinnerPlayerActivity::class.java)
            intent.putExtra("playerWin" , namePlayer2)
            startActivity(intent)
        }

    }


}