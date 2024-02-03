package com.amarinag.randomuser.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkRegistered(
    @SerialName("age")
    val age: Int,
    @SerialName("date")
    val date: String
)