module TreeTests where
    import Test.QuickCheck
    import Control.Monad
    import Data.List
    import Data.Tree
    import LocTree
    
    miniTree = Node 1 []
    
    -- addBranch
    prop_numBranchesIsOneLarger t1 t2 = (length $ subForest $ addBranch t1 t2) - (length $ subForest t1) == 1
    
    prop_newExistsInChildren t = newExists (addBranch t miniTree)
                                                    where newExists (Node _ ts) = any (\t' -> t' == miniTree) ts
    
    prop_restofChildrenUnchanged t = (delete miniTree $ subForest $ addBranch t miniTree) == subForest t
    
    -- exists
    prop_emptyLocationNeverExists t = not (exists [] t)
    
    rootLoc :: LocTree a -> Location
    rootLoc (Node (s, _) _) = [s]
    
    prop_rootShouldAlwaysExist t = exists (rootLoc t) t
                                            
    prop_invalidRootWillNeverExist t s | s /= (fst $ rootLabel t) = not (exists [s] t)
    prop_invalidRootWillNeverExist _ _ = True
    
    basicLocTree = Node ("a", 1) [b1ToC, Node ("b2", 3) []]
    b1ToC = Node ("b1", 2) [Node ("c", 4) []]
    
    prop_singlePositivePath = exists ["a", "b1", "c"] basicLocTree
    prop_subTreesDoNotCount = not (exists ["b1", "c"] basicLocTree)
    
    -- subTree
    shouldError (Left _) = True
    shouldError (Right _) = False
    
    prop_noLocProducesError t = shouldError $ subTree [] t
    
    prop_locOfRootProducesSameTree t = (subTree (rootLoc t) t) == Right t
    
    prop_produceExampleSubTree = (subTree ["a", "b1"] basicLocTree) == Right b1ToC
    
    prop_invalidRootProducesError t s | s /= head (rootLoc t) = shouldError $ subTree [s] t 
    prop_invalidRootProducesError _ _ = True
                                            
    -- chopOff
    prop_chopTakesNumberOfNodesAdded t1 t2 = 
    
    instance (Arbitrary a) => Arbitrary (Tree a) where
        arbitrary = tree 6
            where   tree 0 = liftM2 Node arbitrary (return [])
                    tree n | n > 0 = liftM2 Node arbitrary branches 
                        where branches = do randNum <- arbitrary :: Gen Int
                                            let numTrees = abs randNum
                                            replicateM numTrees (tree $ n `div` 2)
                                            
    instance Arbitrary Char where
        arbitrary = elements (['A'..'Z'] ++ ['a'..'z'] ++ " ~!@#$%^&*()")
                                            
