# Projet TAA

Par [Mathieux DESCHAMPS](https://github.com/MathieuDeschamps) et [Charles LARZILLIÈRE](https://github.com/JodAetaem)

Dépôt de la partie back-end du projet de TAA. Le serveur se lance sur le port 8081 et la base de données est une base HSQL sur le port 8085

#Lancer l'application

Pour lancer l'application il faut au choix :

 - Le lancer directement depuis l'ide de votre choix
 - Builder l'application via maven puis lancer le serveur via le jar créer dans le reperoire target.
 - Lors du premier lancement executer le script pour remplire la base de données

# Travail effectué

## Modèle de données

Notre modèle de données (utile) contient 5 tables: User, Lieu, Sport, Lieu_Sport, Sport_User. Il y a deux tables suplémentaires dont on ne s'est pas servie au final (region et region_user).

## Gestion des CORS

Comme nous ne passons pas par un serveur NGINX pour lier le back avec le front nous avons du gérer le cross-domain en créant une classe de configuration qui implémente l'interface WebMvcConfigurer (classe WebConfig dans le package security). Nous n'avons ouvert le serveur n'importe comment mais juste permit au front d'acceder à l'API (localhost:4200).

Le mapping à été effectué avec spring data jpa.

##  API Rest

L'API rest à été implémenter via les annotations @RestController de spring, pour la documentaitonde celle-ci nous avons intégrer swagger. Commme nous avons créer une classe implémentant WebMVCConfigurer nous sommes obligé de surcharger la méthode addRessourceHandler afin que l'url pour accéder à swagger soit accesible.


## Authentification

Pour l'auhentification des utilisateur nous n'avons pas eu le temps de gérer ça correctement. La configuration de spring security est complétement ouvert. Lors de la connection nous nous servons de l'api et comparons le mot de passe utilisateur hasher en bcrypt avec le mot de passe envoyer par le front end (encoder en base 64 pour le transport).
