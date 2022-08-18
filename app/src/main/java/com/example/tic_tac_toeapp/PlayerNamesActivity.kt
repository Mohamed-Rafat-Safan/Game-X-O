package com.example.tic_tac_toeapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_player_names.*
import kotlin.system.exitProcess

class PlayerNamesActivity : AppCompatActivity() ,TextWatcher {
    private  var  name1 = ""
    private  var  name2 = ""
    val sharedPreferencesName = "sp_name"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_names)

        val animation2 = AnimationUtils.loadAnimation(this,R.anim.zoomin)
        tv_header.startAnimation(animation2)

        val sharedPreferences = getSharedPreferences(sharedPreferencesName , MODE_PRIVATE)

        // To listener any change in writer
        et_player1Name.addTextChangedListener(this)
        et_player2Name.addTextChangedListener(this)


        // when click on Button submitNames
        btn_submitNames.setOnClickListener{
            // to store name Players in sharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("player1",name1)
            editor.putString("player2",name2)
            editor.apply()
            editor.commit()

            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
        }


    }


    // this to Active button when users names palyers
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
         name1 = et_player1Name.text.toString()
         name2 = et_player2Name.text.toString()

        btn_submitNames.isEnabled = name1.trim().isNotEmpty() && name2.trim().isNotEmpty()
    }

    override fun afterTextChanged(p0: Editable?) {}


}