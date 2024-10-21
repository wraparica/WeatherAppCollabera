Weather App
This app is built using Kotlin and Jetpack Compose.
The architecture used is MVVM (Model View View-Model)
This app used tools Retrofit, dagger, location, room database and more.
Retrofit is used for the API Open Weather.
Dagger is for injecting the classes used in Retrofit such as network call, and Room Database such as DAO. 

I also created a login and signup. A very simple UI as I am running out of time as I have work as well.
The log in and sign up is only saved in the ROOM Database

Previous Weather tab is just a cache / data from the Room database.
I added time stamp also in order to check if it is correct.

If you have multiple users, user 1 cannot see user 2's data in previous weather

The icon is from openweather api. I used their url to get the icon and used Glide to see it.

NOTE: If the weather states "Globe" it is because the Location is currently fetching
