
---------------------
-- | Goals:
-- * Describe a domain as a type
--   * probably a 'Domain' typeclass
-- * GET, POST, and DELETE have default, obvious behaviors
--   * probably a 'RestApp' typeclass
-- * Authentication / Users
--   * Security
--     * predicate for resource auth
-- * A map between 'addresses' and Resources
import Text.Email.Validate
import Data.Bson   
       
data User = User {  name :: String,
                    email :: EmailAddress,
                    role :: String         
                 } deriving (Show)
                 
data Context = Context {    user :: User
                        } deriving (Show)
                 
class Resource a where
    address :: a -> String
    toDocument :: a -> Document
    fromDocument :: Document -> a
       
isAuth :: (Resource r) => User -> r -> Bool
isAuth = undefined

type Transformer = (Show r) => r -> String