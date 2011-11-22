module Primes where
	import Shared
	
	sieve:: (Integral a) => [a] -> [a]
	sieve (p:xs) = p : sieve [x | x <- xs, x `mod` p > 0]
	sieve [] = []

	primes :: [Integer]
	primes = 2 : filter isPrime [3,5..]
	
	isPossiblePrime :: (Integral a) => a -> Bool
	isPossiblePrime x = x == 2 || x == 3 || ((x - 1) `mod` 6 == 0) || ((x - 5) `mod` 6 == 0)

	isPrime :: Integer -> Bool
	isPrime n = all (not . divides n) $ takeWhile (\p -> p*p <= n) primes
	
	primeFactorsOf :: Integer -> [Integer]
	primeFactorsOf x = filter (divides x) $ filter isPrime (possiblePrimeFactorsOf x)

	possiblePrimeFactorsOf :: (Integral a) => a -> [a]
	possiblePrimeFactorsOf x = filter isPossiblePrime [sqrtOfX, (sqrtOfX-1)..2]
		where sqrtOfX = ceiling $ sqrt $ fromIntegral x