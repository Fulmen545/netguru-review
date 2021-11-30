package com.netguru.codereview.ui.model

import com.netguru.codereview.network.model.ShopListItemResponse
//TODO: This class only holds data so it should be data class
//TODO: This class should not be in the UI package but in the network/model package
//TODO: Currently only iconUrl variable is used. Will other variables be needed later? If not, remove them
class ShopList(
    val id: String,
    val userId: Int,
    val listName: String,
    val iconUrl: String,
    val items: List<ShopListItemResponse>
)
