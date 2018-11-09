# AngularTaa

Par [Mathieux DESCHAMPS](https://github.com/MathieuDeschamps) et [Charles LARZILLIÈRE](https://github.com/JodAetaem)

## Installation et informations générales

Cloner le projet sur votre ordinateur, et executer depuis le dossier GLI la commande `ng serve`. Pour que le frontend communique correctement avec le backend de notre application, il est indispensable de l'executer sur le port 4200 de votre ordinateur.
Acceder ensuite a l'application dans votre navigateur a l'adresse [`http://localhost:4200/`](http://localhost:4200/).

Pour installer et executer le backend de notre application, rendez-vous sur le  [dépot associé](https://github.com/MathieuDeschamps/TAASpring/tree/master/TAAProject).

# Travail effectué

## Étapes préliminaires

### Choix technologiques
Au debut du projet, nous nous sommes mis d'accord sur la forme que prendrais notre applications, ainsi que les différents modules et API utilisés. 
Pour la récupération de la météo, nous avons choisis de faire appel a l'API de [OpenWeatherMap](https://openweathermap.org/), car elle était a la fois simple, efficace, et gratuite.
Nous avons pris la decision de devellopé l'application avec Angular, car nous étions tout les deux plus a l'aise avec cette technologie qu'avec React.
Enfin, la partie graphique de notre applications a été géré avec [Materialize](https://materializecss.com/) afin d'obtenir facilement une interface qui nous plait.

### Conception

Nous avons décidé de séparer notre application en 4 pages :

 - **Login** où l’utilisateur est invité a rentrer ses identifiant afin de se connecter. Deux champs *Login* et *Mot de passe* sont présent, ainsi que deux boutons *connection* et *Register* afin de pouvoir s'inscrire.
 - **Register** où l'utilisateur peut rentrer ses informations pour pouvoir s'inscrire.
 - **Home** où l'utilisateur pourra choisir parmis les sports qu'il a rentré , les lieux ou il pourras les pratiqué. Une fois sont choix fait, un bouton *Get Result* lui donnera les prédictions météos et les informations sur la praticabilité sur les cinq jours a venir.
 - **Préférence** où l'utilisateur pourra gérer ses sports favoris.

Nous avons donc fait un premier documents qui nous servira de ligne rouge pour le développement, ainsi qu'une répartition des taches. Vous pouvez retrouver ce document [sur notre depot](https://github.com/MathieuDeschamps/GLI/blob/master/Document%20Conception%20pr%C3%A9liminaire%20LARZILLIERE_DESCHAMPS.pdf).


## Développement

Notre applications est composé de 5 composants, correspondants aux 4 pages de l'applications auqel on ajoute la barre de navigation. Ces composants sont appuyé par 6 services.

### Barre de navigation
La barre de navigation est un compostant court, qui sert a l’ergonomie général du site. Placé en haut, elle contient le titre de l'application (qui sert aussi de bouton de retour a l'accueil) , et de deux boutons (déconnexion et préférence) qui apparaissent une fois l'utilisateur connecté.
### Register
Pour s'inscrire, l'utilisateur a besoin de rentré un Pseudo, un mot de passe ainsi qu'une adresse email.
Une fois les champs remplis, une le mot de passe est chiffré en base64 avant d'être envoyé via une requête POST.
L'utilisateur est ensuite automatiquement connecté en renvoyé a la page *Home*.

### LogIn
La page login contient deux champs pour que l'utilisateur rentre ses informations. Lorsqu'il clique sur le bouton de connexion, le mot de passe est encrypté en base64, puis envoyé au back avec le pseudo via une requete POST. Le mot de passe sera chiffré a l'aide de Bcrypt dans la base de donné.
La requête  retourne -1 en cas d’échec de la connexion, un message d'erreur s'affichera alors. De même, si jamais la requête échoue pour une autre raison, l'utilisateur en sera informé.
Dans le cas ou la requête est réussi, elle renvoi l'id de l'utilisateur. Cet id sera utilisé pour connecter l’utilisateur dans notre application via notre service *user*, et nous le conserverons ensuite pour la suite de la navigation.

### Home

Cette page est le cœur de l'application. Ici, l’utilisateur peut choisir dans un menu déroulant un de ses sports favoris, puis un des lieux ou il souhaite le pratiqué. Une fois cette sélection faite, le bouton *Get Result* donne a l'utilisateur contenant toutes les informations utiles.
Ce tableau contient 5 lignes, pour les 5 journées a venir. Chaque ligne donne la date et l'heure de l'estimation, la température attendus ce jour, une description courte de la météo (*light rain*, *clear sky* etc ...) , une fourchette de température pour laquelle le sport est praticable, et un indicateur en forme de Smiley. Si la température attendu ce jour la est inclus dans la fourchette enregistré en base de donné, un smiley souriant apparaît, et dans le cas contraire, le smiley fait la tête.

Les prédiction météorologique vienne de l'API de OpenWeatherMap, qui est appelé directement depuis le front  lorsque l'utilisateur appuie sur *Get Result*, et renvois la météo sur 5 jours par créneaux de 3 heures. Nous trions ces résultats pour ne garder que les données journalières de 15h00. Cependant, nous pourrions choisir un autre horaire, ou même affiché plus d'horaires, mais par soucis de clarté, nous avons choisis de nous limité a une valeur par jour.

### Préférence

Ici, l'utilisateur peu choisir parmi tout les sports en base de donné ceux qu'il souhaite ajouter à ses favoris. Cette étapes est indispensable si il veux pouvoir afficher des résultats sur la page *Home*.
La page se sépare en deux zones : En bas, une liste déroulante contenant tout les sports présent en base de données, et en haut, un tableau affichant tout les sports pour lequel l'utilisateur s'est enregistré.
A coté de chaque champs du tableau, un bouton  moins **( - )** permet de retiré a la volé les sports qu'il ne souhaite plus suivre. La liste déroulante possède elle un bouton plus **( + )** .
Ces deux boutons font, a chaque fois qu'ils sont utilisés, une requête PUT vers le backend afin d'actualiser les changement de l'utilisateur sur son profil. Ces changement sont répercuté en direct coté front afin de pouvoir suivre simplement les sports ajoutés ou supprimés.

## Ergonomie

### Routing
Pour le déplacement au sein de l'application, nous avons géré nos routes afin de redirigé correctement l'utilisateur selon le scénario dans lequel il se trouve.

Dans le cas où l'URL taper ne correspond pas a une page existante, ou est simplement vide, il sera systématiquement redirigé vers la page de connection. De même , si il cherche a acceder a l'une des pages de l'application sans se connecter, il sera ramener automatiquement a la page login grace a l'*AuthGuard*.
Au contraire, si il se connecte et qu'il cherche a revenir en arrière vers la page de login ou de creation de compte, un second *AuthGuard* l'en empêchera. Enfin, forcer l'accès à la page login via la barre d'adresse déconnectera systématiquement l'utilisateur.

Les déplacement normaux de l'utilisateur sont eux effectué via le package [@angular/router](https://angular.io/api/router).

### Materialize

La partie graphique de l'application a été géré grâce a Materialize. Ce framework basé sur le *material design* nous a permis de donner a l'application un look minimaliste qui nous plaisait. Materialize a géré la css de tout les champs utilisateur (input) , les listes déroulantes, les boutons et les tableaux.
De plus, il nous a permis de géré facilement le placement sur la page les différents éléments de nos composant.
Grace a son système de [grille](https://materializecss.com/grid.html), la page était divisé en 12 colonne, que l'ont peut cumuler afin d'obtenir des éléments de la taille voulu.
Par exemple, les éléments de la page *préférence* sont étalé sur 10 des 12 colonnes, et automatiquement centré au milieu de la page, là ou les éléments de la page *home* prennent l'intégralité des 12 colonnes.
