# AnotherAnotherTinyTwit

Projet de Web & Cloud 2018 - 2019 sur la création d'une application TinyTwitt.

Projet réalisé par Adrien Bazoge, Maëlle Brassier, Solène Catella et Hugo Le Baher.

Lien de l'API : https://20190106t210914-dot-tinytwittencore.appspot.com

Lien de l'Explorer : https://20190106t210914-dot-tinytwittencore.appspot.com/_ah/api/explorer

Notre base est peuplé d'environ 1000 utilisateurs. 
Tous suivent l'utilisateur Bob et ont donc ses tweets dans leur timeline.
Les 100 premiers utilisateurs suivent Calvin.

Afin de le vérifier, il suffit de tester la méthode tinytwittsmah.message.timeline, 
d'entrer n'importe quel pseudo d'utilisateur (et un nombre de tweets). Tous auront
Bob dans leur timeline. 

Voici un échantillon des utilisateurs présents : "Calvin", "azwwfmzw", "bvie", "fhyu", "elrhoo", "rbif", "gzpxwgqj", "yltsm", "eeycu", "lintqfq", "wcmy", "ellpsjk", "avayfrk", "xlvpp", "fadpc", "zrqp" ......

Si vous voulez tous les voir, il suffit de lancer la méthode tinytwittsmah.user.get.all

**Mesures pour poster un tweet d'une personne ayant :**
* 100 followers : 357ms
* 1000 followers : 405ms
* 5000 followers : (Dépassement quotidien du quota)

**Mesures pour extraires les M messages de ces personnes, lorsque :**
* M = 10 : 130ms 
* M = 50 : 152ms
* M = 100 : 150 ms
