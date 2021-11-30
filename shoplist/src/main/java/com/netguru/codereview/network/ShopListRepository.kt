package com.netguru.codereview.network

class ShopListRepository(private val shopListApi: ShopListApi) {

    suspend fun getShopLists() = shopListApi.getShopLists()

    suspend fun getShopListItems(listId: String) = shopListApi.getShopListItems(listId)
    //TODO: this needs to be suspended function as it will be used within a coroutine
    fun updateEvents() = shopListApi.getUpdateEvents()
}
