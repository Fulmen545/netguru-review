package com.netguru.codereview.network

import com.netguru.codereview.network.model.ShopListItemResponse
import com.netguru.codereview.network.model.ShopListResponse
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShopListApiMock : ShopListApi {
    override suspend fun getShopLists(): List<ShopListResponse> =
        coroutineScope {
            //TODO: It is obvious you are not sure what size the list will be. Find solution without fixed size list
            List(9999) { index ->
                ShopListResponse(
                    list_id = index.toString(),
                    userId = index,
                    listName = "ListName$index"
                )
            }
        }

    override suspend fun getShopListItems(listId: String): List<ShopListItemResponse> =
        coroutineScope {
            /*TODO: Since this is inside the coroutine it is better to use delay as it is a suspending function
                which won't block the thread. Thread.sleep() will block the thread.
             */
            Thread.sleep(2)
            //TODO: Are you certain that list will never be bigger than 5 items?
            List(5) { index ->
                ShopListItemResponse(
                    itemId = index.toString(),
                    name = "Name$index",
                    /*TODO: quantity will always be 2.0 so you can set it as default value in ShopListItemResponse data class
                        and remove assigning value here
                     */
                    quantity = 2.0
                )
            }
        }

    //TODO: this needs to be suspended function as it will be used within a coroutine
    override fun getUpdateEvents(): Flow<String> = flow {
        var counter = 0
        /*TODO: It is not a good practice to use never ending cycle. The app will then always use mobile's RAM
            and therefore make it slower and use more battery than necessary. Is it needed to show update
            every 5 seconds? From the user's point of view it can become annoying
        */
        while (true) {
            counter++
            delay(5000)
            emit("Update $counter")
        }
    }
}
