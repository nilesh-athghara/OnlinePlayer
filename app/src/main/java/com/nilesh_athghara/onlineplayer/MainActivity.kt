package com.nilesh_athghara.onlineplayer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.IllegalArgumentException


class MainActivity : AppCompatActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPlaySingle.setOnClickListener(this)
        mainStandalone.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val intent =when(v.id)
        {
            R.id.mainPlaySingle-> Intent(this,YoutubeActivity::class.java)
            R.id.mainStandalone->Intent(this,StandaloneActivity::class.java)
            else->throw IllegalArgumentException("undefined button clicked")
        }
        startActivity(intent)
    }
}
