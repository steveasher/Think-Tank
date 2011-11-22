module LocTree where
    import Data.Tree
  
    type LocPacket a = (String, a)
    
    type LocTree a = Tree (LocPacket a)
    
    type Transformation a = Tree a -> Tree a
    
    type Location = [String]
    
    data TreeError = TreeError String
        deriving (Show, Eq)
    
    addBranch :: Tree a -> Tree a -> Tree a
    addBranch (Node x ts) t = Node x (t : ts)
    
    exists :: Location -> LocTree a -> Bool
    exists l t = case (subTree l t) of
                    Right _ -> True
                    Left _ -> False
    
    subTree :: Location -> LocTree a -> Either TreeError (LocTree a)
    subTree [] _ = Left $ TreeError "Invalid path!"
    subTree (l : []) tree@(Node (tL, _) _) | l == tL = Right tree
    subTree (l : ls) (Node (tL, _) ts) = if l == tL
                                                    then head $ rights $ map (subTree ls) ts
                                                    else Left $ TreeError "Location not found!"
                                                        where rights = filter (\e -> case e of
                                                                                        Right _ -> True
                                                                                        Left _ -> False)
    
    chopOff :: Location -> LocTree a -> Either TreeError (LocTree a)
    {-
    chopOff [] _ = Left $ TreeError "Invalid path!"
    chopOff (thisLoc : lastLoc: []) (Node (tLoc, value) forest) = Node (thisLoc, value) $ filter (\x -> rootLabel x /= lastLoc) forest
    chopOff (l : []) 
    -}
    chopOff = undefined
    
    graft :: Location -> LocTree a -> LocTree a -> Either TreeError (LocTree a)
    graft = undefined
    
    data Phase = Positive | Negative
    
    type Delta a = [(Phase, LocPacket a)]
    
    difference :: LocTree a -> LocTree a -> Delta a
    difference = undefined
    
    size :: LocTree a -> Int
    size = length . flatten
    
    
    