threeDigitNumbers = [100..999]
allProductsOf xs = [ a * b | a <- xs, b <- xs]

isPalindrome :: (Eq a) => [a] -> Bool
isPalindrome w = w == reverse w

isNonArrayPalindrome :: (Show a) => a -> Bool
isNonArrayPalindrome x = (isPalindrome . show) x 
						
answer = maximum $ filter isNonArrayPalindrome $ allProductsOf threeDigitNumbers