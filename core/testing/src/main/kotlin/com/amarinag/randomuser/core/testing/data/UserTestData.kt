package com.amarinag.randomuser.core.testing.data

import com.amarinag.randomuser.core.model.User
import com.amarinag.randomuser.core.model.UserCoordinates
import com.amarinag.randomuser.core.model.UserDob
import com.amarinag.randomuser.core.model.UserLocation
import com.amarinag.randomuser.core.model.UserName
import com.amarinag.randomuser.core.model.UserPicture
import com.amarinag.randomuser.core.model.UserRegistered
import com.amarinag.randomuser.core.model.UserStreet
import com.amarinag.randomuser.core.model.UserTimezone

val userTestData = listOf<User>(
    User(
        gender = "male",
        name = UserName(title = "Mr", first = "Janique", last = "Costa"),
        location = UserLocation(
            street = UserStreet(number = 8364, name = "Rua Belo Horizonte "),
            city = "Araraquara",
            state = "Rondônia",
            country = "Brazil",
            coordinates = UserCoordinates(latitude = "-74.5614", longitude = "-150.0978"),
            timezone = UserTimezone(
                offset = "+1:00",
                description = "Brussels, Copenhagen, Madrid, Paris"
            )
        ),
        email = "janique.costa@example.com",
        dob = UserDob(date = "1993-12-07T16:16:12.770Z", age = 30),
        registered = UserRegistered(date = "2021-10-21T06:06:45.362Z", age = 2),
        phone = "(84) 5181-4592",
        cell = "(23) 6323-6609",
        picture = UserPicture(
            large = "https://randomuser.me/api/portraits/men/42.jpg",
            medium = "https://randomuser.me/api/portraits/med/men/42.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/men/42.jpg"
        ),
        nat = "BR"
    ),
    User(
        gender = "male",
        name = UserName(title = "Mr", first = "Paul", last = "Martin"),
        location = UserLocation(
            street = UserStreet(number = 3521, name = "George Street"),
            city = "Wakefield",
            state = "Berkshire",
            country = "United Kingdom",
            coordinates = UserCoordinates(latitude = "-9.6209", longitude = "-78.0173"),
            timezone = UserTimezone(offset = "-12:00", description = "Eniwetok, Kwajalein")
        ),
        email = "paul.martin@example.com",
        dob = UserDob(date = "1966-07-19T19:32:31.550Z", age = 57),
        registered = UserRegistered(date = "2003-04-02T20:33:03.111Z", age = 20),
        phone = "016977 96919",
        cell = "07707 464387",
        picture = UserPicture(
            large = "https://randomuser.me/api/portraits/men/17.jpg",
            medium = "https://randomuser.me/api/portraits/med/men/17.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/men/17.jpg"
        ),
        nat = "GB"
    ),
    User(
        gender = "female",
        name = UserName(
            title = "Mrs",
            first = "Lila",
            last = "Wegman"
        ),
        location = UserLocation(
            street = UserStreet(
                number = 356,
                name = "Kronenburgplantsoen"
            ),
            city = "Veendam",
            state = "Zeeland",
            country = "Netherlands",
            coordinates = UserCoordinates(
                latitude = "37.4599",
                longitude = "104.0025"
            ),
            timezone = UserTimezone(
                offset = "-5:00",
                description = "Eastern Time (US & Canada), Bogota, Lima"
            )
        ),
        email = "lila.wegman@example.com",
        dob = UserDob(
            date = "1983-07-13T04:24:44.375Z",
            age = 40
        ),
        registered = UserRegistered(
            date = "2019-07-17T08:59:49.920Z",
            age = 4
        ),
        phone = "(040) 6781871",
        cell = "(06) 54796829",
        picture = UserPicture(
            large = "https://randomuser.me/api/portraits/women/76.jpg",
            medium = "https://randomuser.me/api/portraits/med/women/76.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/women/76.jpg"
        ),
        nat = "NL"
    ),
    User(
        gender = "male",
        name = UserName(title = "Mr", first = "Dobrolyub", last = "Kalitovskiy"),
        location = UserLocation(
            street = UserStreet(number = 6416, name = "Puhivska"),
            city = "Zolochiv",
            state = "Zakarpatska",
            country = "Ukraine",
            coordinates = UserCoordinates(latitude = "80.9789", longitude = "-55.8117"),
            timezone = UserTimezone(
                offset = "-3:00",
                description = "Brazil, Buenos Aires, Georgetown"
            )
        ),
        email = "dobrolyub.kalitovskiy@example.com",
        dob = UserDob(date = "1982-01-17T09:29:08.878Z", age = 42),
        registered = UserRegistered(date = "2007-11-24T13:53:12.553Z", age = 16),
        phone = "(098) M96-0751",
        cell = "(096) R83-2487",
        picture = UserPicture(
            large = "https://randomuser.me/api/portraits/men/66.jpg",
            medium = "https://randomuser.me/api/portraits/med/men/66.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/men/66.jpg"
        ),
        nat = "UA"
    ),
    User(
        gender = "male",
        name = UserName(title = "Mr", first = "Rayan", last = "Sønsteby"),
        location = UserLocation(
            street = UserStreet(number = 1550, name = "Merkurveien"),
            city = "Siljan",
            state = "Oslo",
            country = "Norway",
            coordinates = UserCoordinates(latitude = "40.7131", longitude = "-18.3357"),
            timezone = UserTimezone(
                offset = "+2:00",
                description = "Kaliningrad, South Africa"
            )
        ),
        email = "rayan.sonsteby@example.com",
        dob = UserDob(date = "1952-11-08T13:03:15.270Z", age = 71),
        registered = UserRegistered(date = "2020-12-10T05:53:51.494Z", age = 3),
        phone = "86833567",
        cell = "46322639",
        picture = UserPicture(
            large = "https://randomuser.me/api/portraits/men/96.jpg",
            medium = "https://randomuser.me/api/portraits/med/men/96.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/men/96.jpg"
        ),
        nat = "NO"
    ),
    User(
        gender = "female",
        name = UserName(title = "Miss", first = "Ashley", last = "Davidson"),
        location = UserLocation(
            street = UserStreet(number = 6435, name = "Main Road"),
            city = "Leeds",
            state = "Grampian",
            country = "United Kingdom",
            coordinates = UserCoordinates(latitude = "-11.3441", longitude = "74.9512"),
            timezone = UserTimezone(
                offset = "+10:00",
                description = "Eastern Australia, Guam, Vladivostok"
            )
        ),
        email = "ashley.davidson@example.com",
        dob = UserDob(date = "1950-12-04T11:08:33.318Z", age = 73),
        registered = UserRegistered(date = "2007-12-22T02:09:32.595Z", age = 16),
        phone = "015394 17323",
        cell = "07001 090890",
        picture = UserPicture(
            large = "https://randomuser.me/api/portraits/women/59.jpg",
            medium = "https://randomuser.me/api/portraits/med/women/59.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/women/59.jpg"
        ),
        nat = "GB"
    ),
    User(
        gender = "female",
        name = UserName(title = "Ms", first = "Amanda", last = "Heino"),
        location = UserLocation(
            street = UserStreet(number = 2730, name = "Pispalan Valtatie"),
            city = "Saarijärvi",
            state = "Northern Savonia",
            country = "Finland",
            coordinates = UserCoordinates(latitude = "-27.9601", longitude = "159.3542"),
            timezone = UserTimezone(
                offset = "+7:00",
                description = "Bangkok, Hanoi, Jakarta"
            )
        ),
        email = "amanda.heino@example.com",
        dob = UserDob(date = "1968-12-19T11:21:24.819Z", age = 55),
        registered = UserRegistered(date = "2014-10-14T02:06:39.733Z", age = 9),
        phone = "07-422-188",
        cell = "045-559-41-60",
        picture = UserPicture(
            large = "https://randomuser.me/api/portraits/women/90.jpg",
            medium = "https://randomuser.me/api/portraits/med/women/90.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/women/90.jpg"
        ),
        nat = "FI"
    ),
    User(
        gender = "male",
        name = UserName(title = "Mr", first = "Tarjei", last = "Lyngmo"),
        location = UserLocation(
            street = UserStreet(number = 585, name = "Magnus Hydles vei"),
            city = "Svøo",
            state = "Nordland",
            country = "Norway",
            coordinates = UserCoordinates(latitude = "45.9519", longitude = "-159.8303"),
            timezone = UserTimezone(
                offset = "-12:00",
                description = "Eniwetok, Kwajalein"
            )
        ),
        email = "tarjei.lyngmo@example.com",
        dob = UserDob(date = "1961-09-08T22:02:54.116Z", age = 62),
        registered = UserRegistered(date = "2018-06-11T18:05:48.807Z", age = 5),
        phone = "52828605",
        cell = "42552667",
        picture = UserPicture(
            large = "https://randomuser.me/api/portraits/men/67.jpg",
            medium = "https://randomuser.me/api/portraits/med/men/67.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/men/67.jpg"
        ),
        nat = "NO"
    ),
    User(
        gender = "female",
        name = UserName(title = "Mrs", first = "Andrea", last = "Subašić"),
        location = UserLocation(
            street = UserStreet(number = 3205, name = "Porodice Marković"),
            city = "Preševo",
            state = "Moravica",
            country = "Serbia",
            coordinates = UserCoordinates(latitude = "56.9660", longitude = "144.4530"),
            timezone = UserTimezone(
                offset = "+9:00",
                description = "Tokyo, Seoul, Osaka, Sapporo, Yakutsk"
            )
        ),
        email = "andrea.subasic@example.com",
        dob = UserDob(date = "1948-11-13T10:28:52.774Z", age = 75),
        registered = UserRegistered(date = "2010-11-14T06:34:03.294Z", age = 13),
        phone = "012-3395-339",
        cell = "064-4121-119",
        picture = UserPicture(
            large = "https://randomuser.me/api/portraits/women/45.jpg",
            medium = "https://randomuser.me/api/portraits/med/women/45.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/women/45.jpg"
        ),
        nat = "RS"
    ),
    User(
        gender = "male",
        name = UserName(title = "Mr", first = "Jayden", last = "Andrews"),
        location = UserLocation(
            street = UserStreet(number = 888, name = "Bruce St"),
            city = "Aurora",
            state = "Kentucky",
            country = "United States",
            coordinates = UserCoordinates(latitude = "89.4783", longitude = "-161.7074"),
            timezone = UserTimezone(
                offset = "-12:00",
                description = "Eniwetok, Kwajalein"
            )
        ),
        email = "jayden.andrews@example.com",
        dob = UserDob(date = "1975-08-17T21:21:11.372Z", age = 48),
        registered = UserRegistered(date = "2010-11-03T09:19:09.318Z", age = 13),
        phone = "(421) 734-9649",
        cell = "(766) 828-8724",
        picture = UserPicture(
            large = "https://randomuser.me/api/portraits/men/90.jpg",
            medium = "https://randomuser.me/api/portraits/med/men/90.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/men/90.jpg"
        ),
        nat = "US"
    )
)