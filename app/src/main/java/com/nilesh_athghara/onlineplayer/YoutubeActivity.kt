package com.nilesh_athghara.onlineplayer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.google.android.youtube.player.internal.p


const val video_id="NNqLB7znMLU"
const val video_playlist="PLx0sYbCqOb8TBPRdmBHs5Iftvv9TPboYG"
class YoutubeActivity : YouTubeBaseActivity(),YouTubePlayer.OnInitializedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_youtube)
//        var layout=findViewById<ConstraintLayout>(R.id.youtube_layout)
        //we can also set the content view by inflating our own layout
        val layout=layoutInflater.inflate(R.layout.activity_youtube,null) as ConstraintLayout
        setContentView(layout)

        //creating widgets in code
        //but it takes a lot of effort to set all the constraints here
        //fortunately our youtub3e player will occupy whole of the screen so we can use this
        /*val button1=Button(this)
        button1.layoutParams=ConstraintLayout.LayoutParams(600,180)
        button1.text="button"
        layout.addView(button1)*/

        //add youtube palyer view

        val playerview=YouTubePlayerView(this)
        playerview.layoutParams=ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerview)
        playerview.initialize(getString(R.string.api_key),this)//give the palyer vierw the api key

    }


    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        Log.d("youtube","provider is ${p0?.javaClass}")
        Log.d("youtube","youtube player is ${p1?.javaClass}")
        Toast.makeText(this,"Initialized player successfully",Toast.LENGTH_SHORT).show()
        p1?.setPlayerStateChangeListener(playerStateChange)
        p1?.setPlaybackEventListener(playbackEventListener)
        if(!p2)
        {
//            p1?.cueVideo(video_id)//cue video was called to play a video which was ini the saved instance
            //this only loads the video
            //so we used load video so video can we started on its own
            p1?.loadVideo(video_id)
        }
        else
        {
            p1?.play()
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        val Result_Code=0
        if(p1?.isUserRecoverableError==true)
        {
            p1.getErrorDialog(this,Result_Code).show()
        }
        else
        {
            val error_message="error im initializing the player $p1"
            Toast.makeText(this,error_message,Toast.LENGTH_SHORT).show()
        }
    }
    private val playbackEventListener=object:YouTubePlayer.PlaybackEventListener
    {
        override fun onSeekTo(p0: Int) {

        }

        override fun onBuffering(p0: Boolean) {

        }

        override fun onPlaying() {
//            Toast.makeText(this@YoutubeActivity,"Playing",Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {

        }

        override fun onPaused() {
//            Toast.makeText(this@YoutubeActivity,"Paused",Toast.LENGTH_SHORT).show()
        }
    }

    private val playerStateChange=object : YouTubePlayer.PlayerStateChangeListener{
        override fun onAdStarted() {

        }

        override fun onLoading() {

        }

        override fun onVideoStarted() {
            Toast.makeText(this@YoutubeActivity,"Video Started",Toast.LENGTH_SHORT).show()
        }


        override fun onLoaded(p0: String?) {

        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity,"Video Completed",Toast.LENGTH_SHORT).show()

        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {

        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        //here we will get a result code that will help to restart the activity on its own if an error was occured after the fix
//        Log.d("request","$resultCode")
//    }
}
