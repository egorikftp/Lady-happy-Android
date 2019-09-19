#!/usr/bin/env bash

echo "Updating Google Json"
echo $GOOGLE_JSON | base64 --decode > app/google-services.json
