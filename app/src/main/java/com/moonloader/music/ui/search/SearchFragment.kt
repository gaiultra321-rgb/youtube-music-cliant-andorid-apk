package com.moonloader.music.ui.search

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.moonloader.music.data.extractor.YouTubeExtractor
import com.moonloader.music.data.model.Song
import kotlinx.coroutines.*

class SearchFragment : Fragment() {
    private var searchJob: Job? = null
    private var results = listOf<Song>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return View(requireContext()).apply { setBackgroundColor(0xFF0A0A14.toInt()) }
    }

    fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            try {
                results = YouTubeExtractor.search(query)
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    fun playSong(song: Song) {
        lifecycleScope.launch {
            try {
                val streamUrl = YouTubeExtractor.getStreamUrl(song.id)
                // Dispatch to MusicPlaybackService
            } catch (e: Exception) { e.printStackTrace() }
        }
    }
}
