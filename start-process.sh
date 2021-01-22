#!/bin/bash

set -x

TOKEN=$1
PAM_CLIENT_SERVICE_ROUTE_URL=$2

echo '======= REQUEST ========================================================'
echo "$TOKEN"
echo "$PAM_CLIENT_SERVICE_ROUTE_URL"
echo 'curl --location --request POST "$PAM_CLIENT_SERVICE_ROUTE_URL/rhpam/process/ExpensesApproval" --header "Authorization: Bearer $TOKEN" -H  "accept: application/json" -H  "content-type: application/json"   -d  "{\"listOfExpenseItems\":[{\"expenseType\":\"Food\",\"expenseValue\":100000},{\"expenseType\":\"Taxi\",\"expenseValue\":26000},{\"expenseType\":\"Accommodation\",\"expenseValue\":10000}],\"departmentRole\":\"HR Manager\",\"departmentName\":\"HR\",\"expenseSubmitterID\":\"PetraJones-35324\",\"expenseFormCorrelationKey\":\"expenseReport-62\",\"expenseOwnerID\":\"NickWatkins-0253\",\"expenseOwnerNotificationAddress\":\"nick.watkings@redhat.com\",\"financialAffairsDirectorApproverUsername\":\"fadirectorapprover\",\"firstApproverUsername\":\"firstapprover\",\"secondApproverUsername\":\"secondapprover\"}"'

curl --location --request POST "$PAM_CLIENT_SERVICE_ROUTE_URL/rhpam/process/ExpensesApproval" --header "Authorization: Bearer $TOKEN" -H  "accept: application/json" -H  "content-type: application/json"   -d  "{\"listOfExpenseItems\":[{\"expenseType\":\"Food\",\"expenseValue\":100000},{\"expenseType\":\"Taxi\",\"expenseValue\":26000},{\"expenseType\":\"Accommodation\",\"expenseValue\":10000}],\"departmentRole\":\"HR Manager\",\"departmentName\":\"HR\",\"expenseSubmitterID\":\"PetraJones-35324\",\"expenseFormCorrelationKey\":\"expenseReport-62\",\"expenseOwnerID\":\"NickWatkins-0253\",\"expenseOwnerNotificationAddress\":\"nick.watkings@redhat.com\",\"financialAffairsDirectorApproverUsername\":\"fadirectorapprover\",\"firstApproverUsername\":\"firstapprover\",\"secondApproverUsername\":\"secondapprover\"}"
