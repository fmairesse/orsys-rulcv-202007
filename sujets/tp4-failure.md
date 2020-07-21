# TP4: failure tolerance

Le but de ce TP est d'expérimenter la librairie _Fault Tolerance_ de `eclipse.microprofile`.
Pour cela, on simulera des pannes des micro-services et la gateway utilisera les annotations de gestion de panne.

Liens utiles:
- https://www.tomitribe.com/blog/microprofile-fault-tolerance-annotations/
- https://quarkus.io/guides/microprofile-fault-tolerance

## Simulation des pannes

- Faire planter de manière aléatoire la requête `/products/v1/{id}` (en lancant une exception par ex.)
- Ralentir de manière aléatoire la requête `/accounts/{id}/transactions`

Pour cela, utiliser les annotations mises à disposition dans le package `common`. Par la suite, on pourra désactiver la simulation d'erreur de ces annotations avec la ligne suivante dans le fichier `application.properties`:
```ini
simulate-failure=false
```

## Gestion des pannes

- Utiliser les annotations créées sur quelques endpoints des micro-services. On peut aussi utiliser les annotations sur les clients REST qui appellent nos micro-services.
- Expérimenter les annotations de `microprofile.faulttolerance` dans la gateway