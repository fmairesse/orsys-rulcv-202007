# TP8: Kafka

Le but du TP est d'expérimenter un "Message Broker" pour gérer les intéractions entre nos services de manière asynchrone et pouvoir implémenter le principe de "saga" qui permet d'assurer la cohérence des données.

Il s'agira de répondre à la requête `POST /api/v2/orders`. On mettra en place Kafka pour échanger des messages et on s'inspirera du guide de Quarkus: https://quarkus.io/guides/kafka. Dans ce guide, la partie "réactive" ne nous intéresse pas, on utilisera le style impératif.

Le but n'est pas d'implémenter une logique métier complète, mais juste d'expérimenter Kafka. Ainsi un premier scénario à tester est le suivant:
- l'utilisateur envoie `POST /api/v2/orders`
- `GatewayResourceV2` crée la commande
- `GatewayResourceV2` envoie un objet stockant le montant à débiter et l'id de la commande sur un _topic_ Kafka "order-created".
- `AccountResource` reçoit cet objet, débite le compte et renvoie l'objet reçu sur un topic "account-debited"
- `OrderResource` reçoit le message est modifie l'état de la commande (ajouter un attribut à OrderModel pour cela)
