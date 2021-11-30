package com.netguru.codereview.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.netguru.codereview.network.model.ShopListItemResponse
import com.netguru.codereview.network.model.ShopListResponse
import com.netguru.codereview.shoplist.R
import com.netguru.codereview.ui.model.ShopList
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    private var viewModel: MainViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        layoutInflater.inflate(R.layout.main_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //TODO: instead of keyword this use viewLifecycleOwner
        /*TODO: Do not use !! operator. If viewModel was null your app would crash.
            Rather use ? operator and handle NullPointerException */
        viewModel!!.shopLists.observe(this, { lists ->
            val progressBar = view.findViewById<ProgressBar>(R.id.message)
            val latestIcon = view.findViewById<ImageView>(R.id.latest_list_icon)

            /*TODO: shopLists is never used. Is it here for future development? If so then comment it out
                or finish the feature */
            val shopLists = lists.map { mapShopList(it.first, it.second) }.also {
                latestIcon?.load(it.first().iconUrl)
            }

            //TODO: ProgressBar is never visible.
            progressBar?.isVisible = false

            //TODO: Don't use string for Log as a TAG. Use string variable instead
            //TODO: Change the msg to something more telling or if not necessary remove Log altogether
            Log.i("LOGTAG", "LOLOLOL Is it done already?")

            /*TODO: Commented out unfinished feature. Finish adapter for recyclerview and remove
                unnecessary comment */
        // Display the list in recyclerview
            // adapter.submitList(shopLists)
        })
        //TODO: instead of this use viewLifecycleOwner
        /*TODO: Do not use !! operator. If viewModel was null your app would crash.
            Rather use ? operator and handle NullPointerException */
        viewModel!!.events().observe(this, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun mapShopList(list: ShopListResponse, items: List<ShopListItemResponse>) =
        ShopList(
            list.list_id,
            list.userId,
            list.listName,
            //TODO: listName is assigned to iconUrl. Are you sure this is correct?
            list.listName,
            items
        )
}
