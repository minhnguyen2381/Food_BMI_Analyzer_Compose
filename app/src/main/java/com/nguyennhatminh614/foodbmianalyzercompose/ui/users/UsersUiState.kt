package com.nguyennhatminh614.foodbmianalyzercompose.ui.users

import com.nguyennhatminh614.foodbmianalyzercompose.domain.User

data class UsersUiState(
    val list: List<User> = listOf(),
    val offline: Boolean = false
)