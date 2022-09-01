# ZSMQ

This is an **Zola Simple zolaMessage queue (ZSMQ)** based on a SpringBoot that can be used in various places.

And it's not suitable for production at all.

#### ZSMQs main goals are

- **Low performance!!**
- High Productivity
- Easy to learn
- Ready-to-use

# Quick Start

You can easily run Zola (ZSMQ) server using docker

```shell
docker run -d -p [port]:8080 dhslrl321/zsmq
```

# Motivation

One day, I tried to use a message queue simply for studying.

It was not relevant to the message queue.

But I spent most of my time setting up message queues and building infrastructure.

It was a very difficult time just to perform convertAndSend.

So I decided to create a server that has low performance and low availability but imitates message queue very efficiently.

This is the beginning of zsmq.
