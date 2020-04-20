package com.ccpp.shared.network.repository

import com.ccpp.shared.core.base.BaseRepository
import com.ccpp.shared.core.result.Results
import com.ccpp.shared.domain.user.UserRes
import com.ccpp.shared.network.ApiService
import javax.inject.Inject

class RssFeedRepository @Inject constructor(
    private val service: ApiService,
    private val baseRepository: BaseRepository
) {

    suspend fun callBcCoinsLedgersAsync(page: Int) = baseRepository.safeApiCall(
        call = {
            service.callBcCoinsLedgersAsync(page).await()
        },
        errorMessage = "Error occurred"
    )

}
