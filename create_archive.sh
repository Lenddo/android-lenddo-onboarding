#!/bin/sh

hg archive ../LenddoSDK-android.zip
zip -d ../LenddoSDK-android.zip "LenddoSDK-android/prepare_distribution.sh"
zip -d ../LenddoSDK-android.zip "LenddoSDK-android/create_archive.sh"