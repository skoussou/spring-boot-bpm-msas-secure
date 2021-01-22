#!/bin/bash

USERNAME=$1
PASSWORD=$1
SSO_ROUTE=$3

echo '======= TOKEN CREATION ========================================================'
echo "$USERNAME"
echo "$PASSWORD"
echo "$SSO_ROUTE"


RESULT=$(curl -sk -X POST $SSO_ROUTE/auth/realms/master/protocol/openid-connect/token   -d grant_type=password   -d username=stelios  -d password=stelios   -d client_id=pam-client-service   -d client_secret=bcf90d5f-56e5-4515-b1db-3cf95e9e3207)
export TOKEN=$( jq -r ".access_token" <<<"$RESULT")

echo $TOKEN
	
