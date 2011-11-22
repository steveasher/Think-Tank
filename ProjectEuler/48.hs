import Shared
import Char

powerOfItself = [ x^x | x <- [1..] ]

lastN n xs = reverse $ take n $ reverse xs

answer = smashToString $ lastN 10 $ splitDigits $ sum $ take 1000 powerOfItself