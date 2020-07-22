# Formation Micro-services en Java

## Liens utiles

Les 7 articles de NGINX sur les microservices, par Chris Richardson (auteur connu dans le domaine des micro-services): https://www.nginx.com/blog/introduction-to-microservices/

10 bonnes pratiques pour écrire des APIs REST: https://medium.com/@mwaysolutions/10-best-practices-for-better-restful-api-cbe81b06f291

Vidéo d'un exemple de désastre d'utilisation des micro-services: https://www.youtube.com/watch?v=gfh-VCTwMw8

À propos de la gestion des pannes, article sur Hystrix (lib Java de gestion de panne apparue avant MicroProfile): https://github.com/Netflix/Hystrix/wiki

## CDI

Context and Dependency Injection

La solution JEE d'injection de dépendance. Définit les annotations comme `@Inject` et `@ApplicationScoped`.

## REST

HATEOAS: Hypermedia As The Engine Of Application State
https://howtodoinjava.com/resteasy/writing-restful-webservices-with-hateoas-using-jax-rs-and-jaxb-in-java/


JAX-RS: La solution JEE pour écrire des APIs REST. Définit les annotations comme `@Path`, `@GET` et les classes comme `Response`, `NotFoundException`.
Implémentée par Jersey (GlassFish et Payara), RESTEasy (JBoss EAOP et Wildfly), Apache CXF (TomEE et Websphere)

Spring n'implémente pas JAX-RS. REST implémenté par Spring MVC (arrivé avant JAX-RS)
https://stackoverflow.com/questions/42944777/difference-between-jax-rs-and-spring-rest

10 best pratices: https://medium.com/@mwaysolutions/10-best-practices-for-better-restful-api-cbe81b06f291

### OpenAPI

Spécification pour documenter APIs REST sous la forme d'un JSON ou YAML
Un doc OpenAPI peut être consommé par différents outils: SoapUI, Postman

Quarkus est capable de générer un tel document: https://quarkus.io/guides/openapi-swaggerui

Ce document peut être transformé vers différents formats avec https://www.apimatic.io/


## Microprofile

Étend JEE pour apporter les briques nécessaires à la construction d'APIs web. 
C'est une spec implémentée par différents projets
=> pas lié à un vendor
=> émulation des vendors
=> évolue plus vite que si géré par Oracle

Prez officielle: https://docs.google.com/presentation/d/1KsVjbmGcZuFCtx5F7ZeCwogM6VPCC6VwwLYFQelb8H8/edit

Github: https://github.com/eclipse/microprofile-open-api

Générateur de projet: https://start.microprofile.io/

## Résilience

### Hystrix

Hystrix: lib créée par Netflix avant les MicroProfile Fault Tolerance Annotations.
Wiki contient image intéressantes:
https://github.com/Netflix/Hystrix/wiki
```
For example, for an application that depends on 30 services where each service has 99.99% uptime, here is what you can expect:

99.99^30 = 99.7% uptime
0.3% of 1 billion requests = 3,000,000 failures
2+ hours downtime/month even if all dependencies have excellent uptime.

Reality is generally worse.
```

### MicroProfile Fault Tolerance Annotations

https://www.tomitribe.com/blog/microprofile-fault-tolerance-annotations/
https://www.eclipse.org/community/eclipse_newsletter/2017/september/article4.php

## Bus de messages

- AMQP: Advanced Message Queuing Protocol
- MOM: message-oriented-middleware, logiciels permettant d'échanger des messages entre applications
https://fr.wikipedia.org/wiki/Message-oriented_middleware
- Message broker. Un agent de messages peut valider, transformer et rediriger les messages. Il agit comme médiateur entre les émetteurs et les récepteurs en leur permettant de communiquer efficacement avec un couplage minimum entre eux.

Kafka est un agent de messages en vogue. Vidéo qui présente kafka et le principe d'agent de message (en anglais): https://www.youtube.com/watch?v=06iRM1Ghr1k&t=235s

## Concepts architecturaux

- Saga : pour maintenir la cohérence des données au sein d'une architecture microservice. https://www.youtube.com/watch?v=YPbGW3Fnmbc
- CQRS (Command Query Responsibility Segregation) : pattern de conception où les opérations de lecture et d'écriture sont clairement séparées. Peut-être couplé à de l'event sourcing : https://www.youtube.com/watch?v=DEhfISeR_go