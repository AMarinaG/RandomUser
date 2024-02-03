package com.amarinag.randomuser.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkTimezone(
    @SerialName("description")
    val description: String,
    @SerialName("offset")
    val offset: String
)