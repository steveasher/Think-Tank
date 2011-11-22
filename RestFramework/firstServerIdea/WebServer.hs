-- Taken from a blog: http://lstephen.wordpress.com/2008/02/14/a-simple-haskell-web-server/

module WebServer where
    import System.IO
    import Network hiding (accept)
    import Network.Stream
    import Network.HTTP
    import Network.Socket
    import Control.Monad
    import Control.Concurrent
    import Control.Arrow
    
    type RequestHandler = Request -> IO Response
    
    runHttpServer :: RequestHandler -> IO ()
    runHttpServer r = withSocketsDo $ do
        sock <- listenOn (PortNumber 8080) 
        forever $ acceptConnection sock $ handleHttpConnection r

    successResponse :: String -> Response
    successResponse = Response (2,0,0) "" []
    
    acceptConnection :: Socket -> (Handle -> IO ()) -> IO ()
    acceptConnection s k = accept s >>= \(h,_,_) -> forkIO $ k h
    
    instance Stream Handle where
      readLine h = hGetLine h >>= \l -> return $ Right $ l ++ "\n"
      readBlock h n = replicateM n (hGetChar h) >>= return . Right
      writeBlock h s = mapM_ (hPutChar h) s >>= return . Right
      close = hClose
    
    handleHttpConnection :: RequestHandler -> Handle -> IO ()
    handleHttpConnection r c = runKleisli 
        (receiveRequest >>> handleRequest r >>> handleResponse) c >> Network.HTTP.close c
        where
            receiveRequest = Kleisli receiveHTTP
            handleRequest r = right (Kleisli r)
            handleResponse = Kleisli (print ||| respondHTTP c)