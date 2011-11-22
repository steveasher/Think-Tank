module Shared where
	import Char
	
	divides a b = a `mod` b == 0
	
	sumOfDigits n = sum $ splitDigits n
	
	splitDigits n = map digitToInt $ show n
	
	fac 0 = 1
	fac n = if n > 0
			then n * fac (n - 1)
			else undefined
			
	fibs = 0 : 1 : [ a + b | (a, b) <- zip fibs (tail fibs)]
	
	-- why doesn't this work???
	smashToNumber [] = 0
	smashToNumber (n:ns) = n * (10 ^ length ns) + (smashToNumber ns)

	smashToString [] = []
	smashToString (n:ns) = intToDigit n : smashToString ns