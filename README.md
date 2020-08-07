# JustAI Junior test task
This project is a simple chat bot for VK.com social network capable of quoting text it is being messaged
## Usage
Command prompt:
```
./mvnw spring-boot:run
```
### Configuration
Sample configuration is presented in [/src/main/resources](/src/main/resources);


**api.properties**
```properties
vk.api.confirmation-string  #String VK requires server to respond with on first connect
vk.api.expected-group-id    #Your VK group id, confirmation used to prevent possible fraud
vk.api.address              #VK API requests address (https://api.vk.com/method/ as of v5.122)
vk.api.version              #VK API version
vk.api.access-key           #Your VK Group access key
```
**bot.properties**
```
bot.prefix                  #Prefix used for message quoting
bot.random                  #Service property needed to override randomId with deterministic value in tests
```
**paths.properties**
```
vk.callback                 #Relative path used for callback handling controller
```
**application.properties**
```
server.port                     #Port used by the server
server.ssl.key-store            #Path to keystore with SSL certificate
server.ssl.key-store-password   #Keystore password
server.ssl.key-store-type       #Keystore type (can either be PKCS12 or JKS)