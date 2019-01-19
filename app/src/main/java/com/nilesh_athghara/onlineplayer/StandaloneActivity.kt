package com.nilesh_athghara.onlineplayer

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.google.android.youtube.player.internal.v
import com.nilesh_athghara.onlineplayer.R
import com.nilesh_athghara.onlineplayer.video_id
import kotlinx.android.synthetic.main.activity_standalone.*
import kotlinx.android.synthetic.main.activity_standalone.view.*
import java.lang.IllegalArgumentException

class StandaloneActivity: AppCompatActivity(), View.OnClickListener {

    //here we will try to extend the onclick listener and impplement its abstract functions instead of declaring it for individual buttons
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

        standalone_play.setOnClickListener(this)
        standalone_playlist.setOnClickListener(this)
//        standalone_play.setOnClickListener(object:View.OnClickListener
//        {
//            override fun onClick(v: View?) {
//
//            }
//        })

//        standalone_play.setOnClickListener(View.OnClickListener { v ->
//            //code
//        })

        //to have same listener for every button declare a variable
//        val listner=View.OnClickListener { v-> }
//        standalone_play.setOnClickListener(listner)
//        standalone_playlist.setOnClickListener(listner)
    }

    override fun onClick(v: View) {
        val intent=when(v.id)
        {
            R.id.standalone_play->YouTubeStandalonePlayer.createVideoIntent(this,getString(R.string.api_key), video_id,0,true,false)//look for parameters in documemtaion to learn to use api's
            R.id.standalone_playlist->YouTubeStandalonePlayer.createPlaylistIntent(this,getString(R.string.api_key),video_playlist,0,0,true,true)
            else-> throw IllegalArgumentException("undefined button clicked")
        }
        startActivity(intent)
    }
}