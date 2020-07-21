# TP6: Docker Compose

Plutôt que de mettre en place nos précédents conteneurs "à la main", nous allons définir notre _stack_ au moyen d'un fichier `docker-compose.yml`.

- Créer un `docker-compose.yml` qui:
  - définit le _volume_ `datevol`
  - définit le _service_ `datewriter`
  - définit le service `datereader`
- Lancer la stack: `docker-compose up`. La date doit s'afficher toutes les secondes dans la console.
- Faire `Ctrl+C`. Cette fois-ci, cela tue tous les conteneurs de la stack (mais le supprime pas). Faire `docker-compose down` pour supprimer les conteneurs.
- Relancer la stack en mode détaché et :
  - afficher les logs de la stack,
  - vérifier que les conteneurs tournent bien
  - vérifier qu'un volume a été créé pour la stack

## Bonus

- Ajouter un service `pinger` à la stack qui `ping` le service `datereader`. Tester la stack.
- Afficher seulement les logs du service `pinger`
- Lister les _networks_ Docker: docker-compose a créé un réseau qui isole les conteneurs de la stack.
```sh
docker network list
```
- Ajouter la définition d'un `network` à la stack et l'utiliser dans chacun des services.
- Supprimer l'ancien réseau par défaut la stack.