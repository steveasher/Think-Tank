
first (a, _, _) = a
second (_, a, _) = a
third (_, _, a) = a

prod (a,b,c) = a * b * c

firstTwo = [(a,b) | a <- [1..1000], b <- [(a + 1)..(1000 - a)]]

addThird p = (fst p, snd p, 1000 - (fst p + snd p))

isPythagorean triplet = (first triplet ^ 2) + (second triplet ^ 2) == (third triplet ^ 2)


answer = prod $ head $ filter isPythagorean $ map addThird firstTwo