package com.amarinag.randomuser.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkLogin(
    @SerialName("md5")
    val md5: String,
    @SerialName("password")
    val password: String,
    @SerialName("salt")
    val salt: String,
    @SerialName("sha1")
    val sha1: String,
    @SerialName("sha256")
    val sha256: String,
    @SerialName("username")
    val username: String,
    @SerialName("uuid")
    val uuid: String
)