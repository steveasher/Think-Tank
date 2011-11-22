import List

remove x = filter (/= x)
permutations [] = [[]]
permutations xs = 

unique [] = []
unique (x:xs) = x : (unique (remove x xs))

answer = permutations $ (replicate 2 True) ++ (replicate 2 False)
