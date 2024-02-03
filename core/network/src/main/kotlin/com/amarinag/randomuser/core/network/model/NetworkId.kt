package com.amarinag.randomuser.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkId(
    @SerialName("name")
    val name: String,
    @SerialName("value")
    val value: String
)