package com.amarinag.randomuser.core.model

data class User(
    val cell: String,
    val dob: UserDob,
    val email: String,
    val gender: String,
    val location: UserLocation,
    val name: UserName,
    val nat: String,
    val phone: String,
    val picture: UserPicture,
    val registered: UserRegistered
)