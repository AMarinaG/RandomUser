package com.amarinag.randomuser.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkLocation(
    @SerialName("city")
    val city: String,
    @SerialName("coordinates")
    val coordinates: NetworkCoordinates,
    @SerialName("country")
    val country: String,
    @SerialName("state")
    val state: String,
    @SerialName("street")
    val street: NetworkStreet,
    @SerialName("timezone")
    val timezone: NetworkTimezone
)