package com.raywenderlich.android.cassette

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit


/**
 * Helper to store and retrieve songs from preferences
 */
class SongStore private constructor(context: Context) {

  private val preferences: SharedPreferences =
      PreferenceManager.getDefaultSharedPreferences(context)

  val allSongs: Set<String>
    get() = getStoredSongs()

  companion object {

    /**
     * store preference key
     */
    const val STORE_KEY: String = "songs"

    /**
     * Factory method to create new store instance
     *
     * @param context Context for new store
     */
    fun from(context: Context): SongStore = SongStore(context)
  }

  /**
   * Get all songs
   *
   */
  private fun getStoredSongs(): MutableSet<String> {
    return preferences.getStringSet(STORE_KEY, mutableSetOf<String>()) ?: mutableSetOf()
  }

  // 1
  fun saveSong(song: String, onSuccess: (() -> Unit)?, onError: (Int) -> Unit) {
    // 2
    val songs = getStoredSongs()
    if (songs.contains(song)) {
      // 3
      onError(R.string.error_song_exists)
    } else {
      // 4
      songs.add(song)
      preferences.edit(true) {
        putStringSet(STORE_KEY, songs)
      }
      onSuccess?.invoke()
    }
  }
}
