#!/bin/bash

set -x

oc delete ksvc pam-client-service
oc delete ksvc hr-expenses-business-application-service
oc delete ksvc hr-expenses-validations-service
               
