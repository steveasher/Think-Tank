
nextInSeq x = 	if even x
				then x `div` 2 
				else 3 * x + 1
		
seqFrom 1 = [1]		
seqFrom x = x : (seqFrom $ nextInSeq x)

lengthOfSeqOf :: Int -> [Int] -> Int
lengthOfSeqOf x knowns = 	if x < length knowns
							then 1 + (knowns !! (x - 1))
							else 1 + (lengthOfSeqOf (nextInSeq x) knowns)