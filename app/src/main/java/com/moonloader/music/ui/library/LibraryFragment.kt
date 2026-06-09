package com.moonloader.music.ui.library

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.moonloader.music.MoonLoaderApp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LibraryFragment : Fragment() {
    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?) =
        View(requireContext()).apply { setBackgroundColor(0xFF0A0A14.toInt()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            MoonLoaderApp.instance.database.songDao().getAllSongs().collectLatest { }
        }
    }
}
