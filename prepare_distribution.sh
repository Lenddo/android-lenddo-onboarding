#!/bin/sh

cp LenddoSDK/build/intermediates/bundles/release/classes.jar LenddoSDK/libs/lenddosdk.jar

zip -d LenddoSDK/libs/lenddosdk.jar "com/lenddo/sdk/BuildConfig.class"
