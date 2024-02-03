package com.amarinag.randomuser.core.model

data class UserLocation(
    val city: String,
    val coordinates: UserCoordinates,
    val country: String,
    val state: String,
    val street: UserStreet,
    val timezone: UserTimezone
)