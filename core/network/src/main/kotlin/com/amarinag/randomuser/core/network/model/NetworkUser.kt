package com.amarinag.randomuser.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkUser(
    @SerialName("cell")
    val cell: String,
    @SerialName("dob")
    val dob: NetworkDob,
    @SerialName("email")
    val email: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("location")
    val location: NetworkLocation,
    @SerialName("login")
    val login: NetworkLogin,
    @SerialName("name")
    val name: NetworkName,
    @SerialName("nat")
    val nat: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("picture")
    val picture: NetworkPicture,
    @SerialName("registered")
    val registered: NetworkRegistered
)