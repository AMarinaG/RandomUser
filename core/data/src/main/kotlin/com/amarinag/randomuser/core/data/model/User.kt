package com.amarinag.randomuser.core.data.model

import com.amarinag.core.database.model.UserEntity
import com.amarinag.randomuser.core.model.User
import com.amarinag.randomuser.core.model.UserCoordinates
import com.amarinag.randomuser.core.model.UserDob
import com.amarinag.randomuser.core.model.UserLocation
import com.amarinag.randomuser.core.model.UserName
import com.amarinag.randomuser.core.model.UserPicture
import com.amarinag.randomuser.core.model.UserRegistered
import com.amarinag.randomuser.core.model.UserStreet
import com.amarinag.randomuser.core.model.UserTimezone
import com.amarinag.randomuser.core.network.model.NetworkCoordinates
import com.amarinag.randomuser.core.network.model.NetworkDob
import com.amarinag.randomuser.core.network.model.NetworkLocation
import com.amarinag.randomuser.core.network.model.NetworkName
import com.amarinag.randomuser.core.network.model.NetworkPicture
import com.amarinag.randomuser.core.network.model.NetworkRegistered
import com.amarinag.randomuser.core.network.model.NetworkStreet
import com.amarinag.randomuser.core.network.model.NetworkTimezone
import com.amarinag.randomuser.core.network.model.NetworkUser

fun NetworkUser.asDomain(): User = User(
    cell = cell,
    dob = dob.asDomain(),
    email = email,
    gender = gender,
    location = location.asDomain(),
    name = name.asDomain(),
    phone = phone,
    picture = picture.asDomain(),
    registered = registered.asDomain()
)

fun User.asEntity(): UserEntity = UserEntity(
    email = email,
    usernameFirst = name.first,
    usernameLast = name.last,
    usernameTitle = name.title,
    phone = phone,
    gender = gender,
    dobAge = dob.age,
    dobDate = dob.date,
    pictureLarge = picture.large,
    pictureMedium = picture.medium,
    pictureThumbnail = picture.thumbnail,
    locationCity = location.city,
    locationCountry = location.country,
    locationState = location.state,
    cell = cell,
    locationLatitude = location.coordinates.latitude,
    locationLongitude = location.coordinates.longitude,
    locationStreetName = location.street.name,
    locationStreetNumber = location.street.number,
    locationTimezoneDescription = location.timezone.description,
    locationTimezoneOffset = location.timezone.offset,
    registeredAge = registered.age,
    registeredDate = registered.date,
)

fun List<NetworkUser>.asDomain(): List<User> = map(NetworkUser::asDomain)
fun List<NetworkUser>.asEntity(): List<UserEntity> =
    map(NetworkUser::asDomain).map { it.asEntity() }

internal fun NetworkDob.asDomain(): UserDob = UserDob(
    age = age, date = date
)

internal fun NetworkLocation.asDomain(): UserLocation = UserLocation(
    city = city,
    coordinates = coordinates.asDomain(),
    country = country,
    state = state,
    street = street.asDomain(),
    timezone = timezone.asDomain(),
)

internal fun NetworkName.asDomain(): UserName = UserName(
    first = first, last = last, title = title
)

internal fun NetworkPicture.asDomain(): UserPicture = UserPicture(
    large = large, medium = medium, thumbnail = thumbnail
)

internal fun NetworkCoordinates.asDomain(): UserCoordinates = UserCoordinates(
    latitude = latitude, longitude = longitude
)

internal fun NetworkStreet.asDomain(): UserStreet = UserStreet(name = name, number = number)
internal fun NetworkTimezone.asDomain(): UserTimezone = UserTimezone(
    description = description, offset = offset
)

internal fun NetworkRegistered.asDomain(): UserRegistered = UserRegistered(age, date)