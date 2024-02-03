package com.amarinag.randomuser.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkInfo(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: Int,
    @SerialName("seed")
    val seed: String,
    @SerialName("version")
    val version: String
)