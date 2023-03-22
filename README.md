# primeNumber
API to get Prime numbers between 0 and a number inclusive

To access the API on cloud use the following below
__________________________________________________________________________________________________
Method: GET
url: http://primenumber-env.eba-wzhtnuea.eu-north-1.elasticbeanstalk.com/primes/{number}
where number is an Integer.
Header: 
  Accept: application/json or application/xml
__________________________________________________________________________________________________  

for example:
curl --location 'http://primenumber-env.eba-wzhtnuea.eu-north-1.elasticbeanstalk.com/primes/1000' \
--header 'Accept: application/json'


