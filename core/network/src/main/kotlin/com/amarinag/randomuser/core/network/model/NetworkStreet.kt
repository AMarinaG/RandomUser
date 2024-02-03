package com.amarinag.randomuser.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkStreet(
    @SerialName("name")
    val name: String,
    @SerialName("number")
    val number: Int
)