package com.ccpp.shared.network.repository

import com.ccpp.shared.core.base.BaseRepository
import com.ccpp.shared.network.ApiService
import javax.inject.Inject

class RssFeedRepository @Inject constructor(
    private val service: ApiService,
    private val baseRepository: BaseRepository
) {

    suspend fun callRssFeedAsync() = baseRepository.safeApiCall(
        call = {
            service.callRssFeedAsync().await()
        },
        errorMessage = "Error occurred"
    )

}
