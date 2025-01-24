package com.amarinag.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amarinag.randomuser.core.model.User
import com.amarinag.randomuser.core.model.UserCoordinates
import com.amarinag.randomuser.core.model.UserDob
import com.amarinag.randomuser.core.model.UserLocation
import com.amarinag.randomuser.core.model.UserName
import com.amarinag.randomuser.core.model.UserPicture
import com.amarinag.randomuser.core.model.UserRegistered
import com.amarinag.randomuser.core.model.UserStreet
import com.amarinag.randomuser.core.model.UserTimezone

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val email: String,
    val cell: String,
    val phone: String,
    val gender: String,
    val usernameFirst: String,
    val usernameLast: String,
    val usernameTitle: String,
    val dobAge: Int,
    val dobDate: String,
    val pictureLarge: String,
    val pictureMedium: String,
    val pictureThumbnail: String,
    val locationCity: String,
    val locationLatitude: String,
    val locationLongitude: String,
    val locationCountry: String,
    val locationState: String,
    val locationStreetName: String,
    val locationStreetNumber: Int,
    val locationTimezoneDescription: String,
    val locationTimezoneOffset: String,
    val registeredAge: Int,
    val registeredDate: String

)

fun UserEntity.asModel() = User(
    cell = cell,
    dob = UserDob(dobAge, dobDate),
    email = email,
    gender = gender,
    location = UserLocation(
        locationCity,
        UserCoordinates(locationLatitude, locationLongitude),
        locationCountry,
        locationState,
        UserStreet(locationStreetName, locationStreetNumber),
        UserTimezone(locationTimezoneDescription, locationTimezoneOffset)
    ),
    phone = phone,
    picture = UserPicture(pictureLarge, pictureMedium, pictureThumbnail),
    name = UserName(usernameFirst, usernameLast, usernameTitle),
    registered = UserRegistered(registeredAge, registeredDate),
)
