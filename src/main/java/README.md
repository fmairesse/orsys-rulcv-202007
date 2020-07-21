Générer le jar:
```sh
javac -target 8 -source 8 DateWriter.java
jar cfe DateWriter.jar DateWriter DateWriter.class
```

Tester le jar:
```sh
java -jar DateWriter.jar date.txt
```

Construire l'image (à lancer depuis ce dossier):
```sh
docker build -t datewriter -f ../docker/datewriter/Dockerfile .
```

Lancer un conteneur de cette image:
```sh
docker run -d --rm --name datewriter datewriter
```

Vérifier que le programme écrit bien le fichier dans le conteneur:
```sh
docker exec datewriter cat /data/date.txt
```
⚠ Cette commande ne fonctionnera pas dans Git Bash.