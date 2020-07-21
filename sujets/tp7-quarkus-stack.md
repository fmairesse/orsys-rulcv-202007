# TP7: stack de micro-services

Créer une stack Docker qui lance tous nos services. Le service `gateway` _dépend_ de tous les autres (ne démarre pas avant eux) et _expose_ son port `8080` sur le port `8081` de la machine hôte.

Tester que l'application est accessible sur [localhost:8081](http://localhost:8081).

Tester les requêtes sur `/api` dans Postman (modifier la variable `baseUrl` dans l'environnement de variables Postman).