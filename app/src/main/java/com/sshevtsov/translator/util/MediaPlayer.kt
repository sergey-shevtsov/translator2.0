package com.sshevtsov.translator.util

import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.sshevtsov.translator.domain.entity.UrlPath

class MediaPlayer(private val exoPlayer: ExoPlayer) {

  private val playerListener = object : Player.Listener {
    override fun onPlaybackStateChanged(playbackState: Int) {
      when (playbackState) {
        Player.STATE_ENDED -> {
          exoPlayer.clearMediaItems()
          exoPlayer.stop()
        }
        else -> Unit
      }
    }
  }

  init {
    exoPlayer.addListener(playerListener)
  }

  fun play(urlPath: UrlPath) {
    if (exoPlayer.playbackState == Player.STATE_IDLE) {
      exoPlayer.addMediaItem(MediaItem.fromUri(urlPath.value))
      exoPlayer.prepare()
      exoPlayer.play()
    }
  }
}