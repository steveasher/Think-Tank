import Shared

divisibleByAll xs y = all (divides y) xs
	
answer = head $ filter (divisibleByAll [20,19..2]) [20,40..]