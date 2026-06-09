package com.moonloader.music.ui.subscriptions

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.moonloader.music.MoonLoaderApp
import com.moonloader.music.data.model.Subscription
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SubscriptionsFragment : Fragment() {
    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?) =
        View(requireContext()).apply { setBackgroundColor(0xFF0A0A14.toInt()) }

    override fun onViewCreated(view: View, s: Bundle?) {
        super.onViewCreated(view, s)
        lifecycleScope.launch {
            MoonLoaderApp.instance.database.subscriptionDao().getAllSubscriptions().collectLatest { }
        }
    }

    fun subscribe(id: String, name: String, url: String, avatar: String) {
        lifecycleScope.launch {
            MoonLoaderApp.instance.database.subscriptionDao().subscribe(Subscription(id, name, url, avatar))
        }
    }

    fun unsubscribe(channelId: String) {
        lifecycleScope.launch {
            MoonLoaderApp.instance.database.subscriptionDao().getSubscription(channelId)?.let {
                MoonLoaderApp.instance.database.subscriptionDao().unsubscribe(it)
            }
        }
    }
}
