package com.nguyennhatminh614.foodbmianalyzercompose.repository

import com.nguyennhatminh614.foodbmianalyzercompose.database.AppDatabase
import com.nguyennhatminh614.foodbmianalyzercompose.database.asDomainModel
import com.nguyennhatminh614.foodbmianalyzercompose.domain.Details
import com.nguyennhatminh614.foodbmianalyzercompose.network.DetailsApi
import com.nguyennhatminh614.foodbmianalyzercompose.network.model.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val detailsApi: DetailsApi,
    private val appDatabase: AppDatabase
) {

    fun getUserDetails(user: String): Flow<Details?> =
        appDatabase.usersDao.getDetails(user).map { it?.asDomainModel() }

    suspend fun refreshDetails(user: String) {
        val userDetails = detailsApi.getDetails(user)
        appDatabase.usersDao.insertDetails(userDetails.asDatabaseModel())
    }

}