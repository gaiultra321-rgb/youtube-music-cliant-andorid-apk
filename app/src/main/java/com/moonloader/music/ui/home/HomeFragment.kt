package com.moonloader.music.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.moonloader.music.data.extractor.YouTubeExtractor
import com.moonloader.music.data.model.Song
import com.moonloader.music.download.DownloadWorker
import androidx.work.WorkManager
import kotlinx.coroutines.launch

/**
 * HomeFragment - Shows trending music and recommendations.
 * Uses NewPipe extractor to fetch YouTube Music trending.
 */
class HomeFragment : Fragment() {

    private var songs = listOf<Song>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // In production: use ViewBinding with fragment_home.xml layout
        // This returns a simple placeholder for compilation
        return View(requireContext()).apply {
            setBackgroundColor(0xFF0A0A14.toInt())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadTrending()
    }

    private fun loadTrending() {
        lifecycleScope.launch {
            try {
                songs = YouTubeExtractor.getTrending()
                // Update UI with trending songs
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun downloadSong(song: Song) {
        val workRequest = DownloadWorker.buildRequest(
            songId = song.videoId,
            songTitle = song.title,
            videoUrl = song.id
        )
        WorkManager.getInstance(requireContext()).enqueue(workRequest)
    }
}
