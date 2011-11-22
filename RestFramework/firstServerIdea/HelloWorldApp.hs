import WebServer
import Text.Html

main :: IO ()
main = runHttpServer helloWorldHandler

helloWorldHandler :: RequestHandler
helloWorldHandler _ =  return $ successResponse $ helloWorldDoc

helloWorldDoc :: Html
helloWorldDoc = header << thetitle << "Hello World"
            +++ body << h1 << "Hello World"