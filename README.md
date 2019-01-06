# AnotherAnotherTinyTwit

Projet de Web & Cloud 2018 - 2019 sur la création d'une application TinyTwitt.

Projet réalisé par Adrien Bazoge, Maëlle Brassier, Solène Catella et Hugo Le Baher.

Lien de l'API : https://20190106t213743-dot-tinytwittencore.appspot.com/

Lien de l'Explorer : https://20190106t213743-dot-tinytwittencore.appspot.com/_ah/api/explorer

# FRONT 

* Tout d'abord, il faut se connecter en rentrant un pseudo. Par exemple : Bob ou Calvin 
* Ensuite, on peut écrire un message puis le poster avec Envoyer.
* Pour afficher la timeline, il suffit de cliquer sur Charger et indiquer le nombre de tweets qu'on souhaite voir.
* Enfin, on peut cliquer sur un Hashtag afin de consulter tous les messages correspondants.

# BACK 

Notre base est peuplée d'environ **1000 utilisateurs**. 
Tous suivent l'utilisateur **Bob** et ont donc ses tweets dans leur timeline.
Les 100 premiers utilisateurs suivent **Calvin**.

Afin de le vérifier, il suffit de tester la méthode **tinytwittsmah.message.timeline**, 
d'entrer n'importe quel pseudo d'utilisateur (et un nombre de tweets maximum). Tous auront
Bob dans leur timeline. 

Voici un échantillon des utilisateurs présents : "Calvin", "azwwfmzw", "bvie", "fhyu", "elrhoo", "rbif", "gzpxwgqj", "yltsm", "eeycu", "lintqfq", "wcmy", "ellpsjk", "avayfrk", "xlvpp", "fadpc", "zrqp" ......

Si vous voulez tous les voir, il suffit de lancer la méthode **tinytwittsmah.user.get.all**

Pour poster il faut utiliser la méthode **tinytwittsmah.message.add** avec un nom d'utilisateur et le contenu du message.

/!\ Les méthodes sur le front prennent en compte l'affichage des résultats et faussent donc les mesures, comparé à celles de l'API Explorer qui se trouvent ci-dessous. 

**Mesures pour poster un tweet d'une personne ayant :**
* 100 followers : 357ms
* 1000 followers : 405ms
* 5000 followers : (Dépassement quotidien du quota)

**Mesures pour extraire les M messages de ces personnes, lorsque :**
* M = 10 : 130ms 
* M = 50 : 152ms
* M = 100 : 150 ms

**Mesures pour extraire les 50 derniers messages d'un hashtag concernant 1000 messages**
* 600ms 
