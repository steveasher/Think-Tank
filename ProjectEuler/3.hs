import Primes

greatestPrimeFactorOf :: Integer -> Integer
greatestPrimeFactorOf x = head $ primeFactorsOf x
	
answer = greatestPrimeFactorOf 600851475143

