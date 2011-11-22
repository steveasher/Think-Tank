import Primes

answer = sum $ takeWhile (\x -> x < 2000000) primes