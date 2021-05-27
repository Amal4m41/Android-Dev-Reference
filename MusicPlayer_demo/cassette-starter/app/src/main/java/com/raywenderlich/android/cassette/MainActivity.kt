package com.raywenderlich.android.cassette

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), AddSongFragment.OnSongAdded {

  private lateinit var songStore: SongStore
  // 1
  //lambda expression to change the visibility feature of the View components
  val toggleEmptyView = { show: Boolean ->
    // 2
    group_empty.visibility = if (show) View.VISIBLE else View.GONE
    button_add_song.visibility = if (show) View.GONE else View.VISIBLE
  }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    songStore = SongStore.from(applicationContext)
    setClickListeners()
  }

  // 1
  private fun showAddSongDialog()
  {
    // 2
    AddSongFragment.show(supportFragmentManager)
  }

  private fun setClickListeners() {
    // 2
    button_add_song_empty.setOnClickListener {
      showAddSongDialog()
      toggleEmptyView(true)
    }
    // 3
    button_add_song.setOnClickListener {
      showAddSongDialog()
    }
  }
  override fun onSongAdded() {
  }
}
