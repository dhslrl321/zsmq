<div align="center">

#### [home (engish version)](https://github.com/dhslrl321/zsmq)

#### [korean version docs](https://github.com/dhslrl321/zsmq/blob/main/README-kor.md)

</div>

# ZSMQ

This is an **Zola Simple Message queue (ZSMQ)** based on a Java that can be used in various places.

And it's not suitable for production at all.

#### ZSMQs main goals are

- **Low performance!!**
- High Productivity
- Easy-to-use
- Easy-to-learn

# Quick Start

Since zsmq is a fast and easy-to-use system, we aim for minimal configuration.

You can quickly create a great message queue by following these steps!

1. run messaging server
2. gradle dependency
3. configure property
4. Just U.S.E it!!

## 1. run messaging server

You can easily run Zola (ZSMQ) server using docker

```shell
docker run --rm -d -p 8290:3000 dhslrl321/zsmq:dashboard.0.1.0
docker run --rm -d -p 8291:8291 dhslrl321/zsmq:server.0.1.0
```

You can use custom port, but **8291** is recommended because there may be a port conflict.

## 2. gradle dependency

We provide a spring boot starter so that you can skip the complicated process and set it up easily.

It can be downloaded from the [jitpack](https://jitpack.io/#dhslrl321/zsmq) repository.

You can manually set bin without using the spring boot starter.

Check the [reference guide](https://github.com/dhslrl321/zsmq/wiki/Reference-Guide) for more information

```groovy
repositories {
    // ... other maven repository
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.dhslrl321.zsmq:zola-messaging-sprint-boot-starter:${version}'
}
```

## 3. configure property

The url of the Zola Messaging Server must be specified in application.yml.

```yaml
zsmq:
  url: http://localhost:8291
```

## 4. Just U.S.E it !

You have to use it as it is.

- If you want to publish a message, please use `ZolaQueueMessagingTemplate`.
- If you want to consume a message, please use '@ZolaConsumer' and `@ZolaMessageListener`

### publish message

```java
@RequiredArgsConstructor
public class MessageProducer {

    private final ZolaQueueMessageTemplate template;

    public void send() {
        template.convertAndSend("MY-QUEUE", "foo");
    }
}
```

### consume message

```java
@Component
@ZolaConsumer
public class MyConsumer {

    @ZolaMessageListener(queueName = "MY-QUEUE", deletionPolicy = DeletionPolicy.ALWAYS)
    public void listen(String message) {
        System.out.println("message = " + message);
    }
}
```

# Motivation

One day, I tried to use a message queue simply for studying.

It was not relevant to the message queue.

But I spent most of my time setting up message queues(RabbitMQ, SQS) and building infrastructure.

It was a very tough time just to perform convertAndSend.

So I decided to **create a server** that has **low performance** but **highly productive** message queue.

This is the beginning of zsmq.

> 'zola' is a korean slang. It means 'utterly', 'extremely', 'super', 'very'
