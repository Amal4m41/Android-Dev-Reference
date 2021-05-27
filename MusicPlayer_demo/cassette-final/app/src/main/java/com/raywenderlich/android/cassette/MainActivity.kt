package com.raywenderlich.android.cassette

import android.os.Bundle
import android.text.*
import android.text.style.UnderlineSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), AddSongFragment.OnSongAdded {

  private lateinit var songStore: SongStore
  // 1
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
    showAllSongs(songStore.allSongs.toList())
  }

  // 1
  private fun showAddSongDialog() {
    // 2
    AddSongFragment.show(supportFragmentManager)
  }

  // 1
  private fun setClickListeners() {
    // 2
    button_add_song_empty.setOnClickListener {
      showAddSongDialog()
    }
    // 3
    button_add_song.setOnClickListener {
      showAddSongDialog()
    }
  }

  // 1
  private fun showAllSongs(songs: List<String>) {
    // 2
    val spans = mutableListOf<Spanned>()
    // 3
    val underlineTitle: (String) -> SpannableString = {
      val songTitle = it.split(",")[0]
      SpannableString(it).apply {
        setSpan(
          UnderlineSpan(), 0, songTitle.length,
          Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
      }
    }
    // 4
    for (song in songs) {
      spans.add(underlineTitle(song))
      spans.add(SpannedString("\n\n"))
    }
    // 5
    text_view_all_songs.text = TextUtils.concat(*spans.toTypedArray())
    // 6
    toggleEmptyView(spans.isEmpty())
    findPlaylistYearRange(songs)
  }

  override fun onSongAdded() {
    showAllSongs(songStore.allSongs.toList())
    toggleEmptyView(false)
  }
  // 1
  private fun findPlaylistYearRange(songs: List<String>) {
    // 2
    if (songs.isEmpty()) {
      return
    }
    // 3
    var startYear = songs[0].split(",")[2].trim().toInt()
    var endYear = startYear
    // 4
    val findStartEndYear = {
      songs.forEach { song ->
        val songYear = song.split(",")[2].trim().toInt()
        if (songYear > endYear) {
          endYear = songYear
        } else if (songYear < startYear) {
          startYear = songYear
        }
      }
    }
    // 5
    findStartEndYear()

    // 6
    text_view_total.text = "($startYear - $endYear) - Total: ${songs.count()}"
  }

}
