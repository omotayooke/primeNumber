# primeNumber
API to get Prime numbers between 0 and a number inclusive

To access the API on cloud use below information

### Endpoint: /primes/{number}
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

### Endpoint: /threads/{thread}/primes/{number}
This endpoint uses thread for large integers and is faster. specify the number of threads to run in the GET request
http://primenumber-env.eba-wzhtnuea.eu-north-1.elasticbeanstalk.com/threads/8/primes/1000000

