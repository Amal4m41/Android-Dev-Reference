package com.raywenderlich.android.cassette

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_add_song.*

class AddSongFragment : BottomSheetDialogFragment() {

  companion object {

    private const val FRAGMENT_TAG = "add_song_fragment"

    fun show(fragmentManager: FragmentManager) {
      val fragment = AddSongFragment()
      fragment.show(fragmentManager, FRAGMENT_TAG)
    }
  }

  interface OnSongAdded {
    fun onSongAdded()
  }

  private lateinit var onSongAdded: OnSongAdded

  private lateinit var songStore: SongStore

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    songStore = SongStore.from(context!!.applicationContext)
    return inflater.inflate(R.layout.dialog_add_song, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    handleSongSaveClick()
  }

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    onSongAdded = context as AddSongFragment.OnSongAdded
  }

  private fun showError(message: Int) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
  }

  private fun handleSongSaveClick() {
    button_save.setOnClickListener {
      if (saveSong()) {
        dismiss()
      }
    }
  }

  // 1
  private fun saveSong(): Boolean {
    // 2
    val title = edit_text_title.text?.toString()
    val artist = edit_text_artist.text?.toString()
    val year = edit_text_year.text?.toString()

    if (title.isNullOrEmpty()) {
      showError(R.string.error_title_empty)
      return false
    }

    if (!isValidArtist(artist)) {
      return false
    }

    if (!isValidYear(year)) {
      showError(R.string.error_year_invalid)
      return false
    }

    songStore.saveSong("$title, $artist, $year", onSongAdded::onSongAdded, this::showError)
    return true
  }

  // 1
  private fun isValidArtist(artist: String?): Boolean {
    // 2
    val artistValidator = fun(value: String?): Boolean {
      // 3
      if (value.isNullOrEmpty()) {
        showError(R.string.error_artist_empty)
        return false
      }

      if (value.length < 2) {
        showError(R.string.error_artist_invalid)
        return false
      }
      return true }

    // 4
    return artistValidator(artist)
  }

  // 5
  private fun isValidYear(year: String?): Boolean {
    val yearValidator: (String?) -> Boolean = { !it.isNullOrEmpty() && it.toInt() in 1877..2019 }
    return yearValidator(year)
  }
}
