# TP9: health check

Le but est de manipuler l'API microprofile permettant le contrôle de santé de nos micro-services.

On n'utilisera le guide suivant de Quarkus: https://quarkus.io/guides/microprofile-health

Ajouter des `HealthCheck` (classes qui implémentent `HealthCheck`) dans des packages de nos micro-services. Par ex.:
- un HealthCheck qui renvoie "ready" (`@Readiness`) pour les accounts
- un autre qui renvoie "en vie/mort" (`@Liveness`) de manière aléatoire
- un autre qui attache des données à la `HealthCheckResponse`


Ensuite tester les URLs:
- http://localhost:8080/health
- http://localhost:8080/health/live
- http://localhost:8080/health/ready