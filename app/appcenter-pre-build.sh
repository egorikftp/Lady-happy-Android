#!/usr/bin/env bash

echo "Updating Google Json"
echo $GOOGLE_JSON | base64 --decode > $APPCENTER_SOURCE_DIRECTORY/app/google-services.json
