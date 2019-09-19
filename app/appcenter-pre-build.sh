#!/usr/bin/env bash

if [ -e "$GOOGLE_JSON_FILE" ]
then
    echo "Updating Google Json"
    echo $GOOGLE_JSON | base64 --decode > app/google-services.json

    echo "File content:"
    cat $GOOGLE_JSON_FILE
fi
