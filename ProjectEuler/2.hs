import Shared

evenFibsUnder4Mil = filter even (takeWhile (\x -> x < 4000000) fibs)

answer = sum evenFibsUnder4Mil