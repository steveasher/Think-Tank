import List
import Shared

triangleNumbers = map triangleGenerator [1..]

triangleGenerator 0 = 0
triangleGenerator x = x + triangleGenerator (x - 1)

factors x = filter (divides x) [1..x]

answer = find (\x -> (length $ factors x) > 500) triangleNumbers

