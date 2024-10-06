Hi folks!

This is a Marvel app where I like to experiment with new Android technologies. Below is a list of the things I am using in this project.

## What you'll find
- Clean architecture with 3 layers: app, data, and domain
- Kotlin & Jetpack Compose
- MVVM as the presentation pattern, using StateFlows & Coroutines
- Koin for dependency injection
- Unit testing: testing use cases in the domain, repositories, and Room database in the data layer
- Basic UI Compose tests at the app layer
- Use of Retrofit for network calls
- Handling service call results with Result.kt
- Two navigation methods:
   - Navigation between login and main using Navigation Safe Args
   - Another navigation method inspired by Antonio Leiva (Google Developer Expert)
- SharedPreferences and EncryptedSharedPreferences
- The app supports two languages: Spanish and English. It automatically adapts to the device's default language. For devices running API 30 or higher, there will be a button at login to allow language selection between these two options.
- Coil for image loading
- Some basic animations using crossfade
- On the sharedTransition branch, you can test the new Shared Transition tools. This applies to the list-detail navigation inside the app. BEWARE: This feature is still a work in progress (WIP), so there might be some navigation issues.
- AnimationSplash for splash screen animation



## How to use it
It's simple! Just add a user and password. Be sure to follow the app's rules, which will appear on the login screen. For example:
User: email1@
Pass: 123456



## Reminder
This is just a test app. You might find personal annotations and reminders throughout, which help me understand my code.
