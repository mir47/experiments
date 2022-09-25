# Xpense

This app is intended for experiments.

To build the app:
- Add `MAPS_API_KEY` to `local.properties` - see example in `local.properties.example`. Also see [Authentication for Google Play services](https://developers.google.com/android/guides/client-auth) on how to setup API keys, and [add restrictions](https://cloud.google.com/api-keys/docs/add-restrictions-api-keys#adding_android_restrictions).
- This app integrates with Firebase services and needs to be linked to a Firebase project. You can create your own project on Firebase, and download the `google-services.json` config file to the `app` module root folder.
