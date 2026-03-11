package com.nguyennhatminh614.foodbmianalyzercompose.repository

import com.nguyennhatminh614.foodbmianalyzercompose.database.AppDatabase
import com.nguyennhatminh614.foodbmianalyzercompose.database.asDomainModel
import com.nguyennhatminh614.foodbmianalyzercompose.domain.User
import com.nguyennhatminh614.foodbmianalyzercompose.network.UsersApi
import com.nguyennhatminh614.foodbmianalyzercompose.network.model.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersApi: UsersApi,
    private val appDatabase: AppDatabase
) {

    val users: Flow<List<User>?> =
        appDatabase.usersDao.getUsers().map { it?.asDomainModel() }

    suspend fun refreshUsers() {
        val users = usersApi.getUsers()
        appDatabase.usersDao.insertUsers(users.asDatabaseModel())
    }
}