package com.amarinag.randomuser.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse(
    @SerialName("info")
    val info: NetworkInfo,
    @SerialName("results")
    val users: List<NetworkUser>
)