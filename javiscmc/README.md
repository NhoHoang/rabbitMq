1. Step one:

    run rabbitMQ in docker:

    docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management

    MQ port: 5672

2. Run user-service (producer)

    Run notification-service  (consumer)

    Run notification2-service  (consumer)


   