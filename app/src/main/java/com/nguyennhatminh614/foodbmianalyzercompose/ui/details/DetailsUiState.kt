package com.nguyennhatminh614.foodbmianalyzercompose.ui.details

import com.nguyennhatminh614.foodbmianalyzercompose.domain.Details
import com.nguyennhatminh614.foodbmianalyzercompose.util.formatDate

data class DetailsUiState(
    val detail: Details = Details(),
    val offline: Boolean = false
) {
    val formattedUserSince = formatDate(detail.userSince)
}