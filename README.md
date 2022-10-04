# MS2-game-service
Spring Feign Client + Scheduling + Rabbitmq

Feign client is scheduled to call pbp service every 10s. He collects data and stores it in postgreSQL, after any update in database message is sent 
on RabbitMQ queue.
