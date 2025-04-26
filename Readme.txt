LLDM + redis pub sub

1. Models
    message class : 
        -- message id
        -- message body
        -- from 
        -- to
    user interface
        -- publisher
        -- subscriber
        -- builder interface to give publisher or subscriber
        nope not needed this is over engineering : why? :
            --  because redis channel doesnt care , it doesnt differnetiate users into publisher and subscriber
            -- also its not broadcasting channel thing , its messaging : it will be i will post message and read other people message also
        
        -- id
        -- name
        -- subscribe to a channel
        -- unsubscribe to a channel
        -- post a message
        -- read a message
    user class

2.  messageChannel file : observer pattern (using redis)
3. we need RedisConfig.java
    -- spring dont automatically configure RedisTemplate
    -- spring needs redisConnectionFactory to form connection with redis
    -- LettuceConnectionFactory 
4. controller
5. download redis and get it started
    -- brew install redis
    -- brew services start redis
    -- open new terminal
    -- redis-cli
    -- ping





single responsiblity principle
interface
    L
    I
behavior design pattren : observer : pub sub (but we are using redis)
