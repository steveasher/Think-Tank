
    
    -- role permissions
    
    -- transformations
    
    
    
    -- PUT (auto)
        -- location string: resource location
        -- deep: replaces whole branch
        -- shallow: replaces node (may delete full sub-branch if no longer referenced)
    
    -- GET (auto)
        -- location string: resource location
        -- deep int: retrieves branch to specified depth
        -- shallow: retrieves node with references
        -- transforms string: comma delimited list of transformations
        
    -- DELETE (auto)
        -- removes whole branch
    
    --POST 
        -- actions string: comma delimited list of transformation, replaces branch
        
        
