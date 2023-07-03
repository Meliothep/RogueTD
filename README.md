# Rogue TD

## Description du projet :

Une version "IUT friendly" du jeu [Rogue Tower](https://store.steampowered.com/app/1843760/Rogue_Tower/)
disponible sur STEAM.

Ce repos est publique et open source (ptdr on dirais presque un projet sérieux)
par conséquent n'hésitez pas à contribuer (faire des pr).

## Motivations

Les cours de base de donnée c'est bien, mais c long quand meme...

Plus serieusement, le projet viens d'un ennoncé ou nous devions developpé un jeu en java implementant 3 design pattern minimum. 

## Comment lancer le jeux 

Apres avoir cloné le projet vous avez 2 choix possibles :
- Utiliser votre IDE pour lancer le jeu
- Utiliser la commande suivante `mvn clean package exec:java '-Dexec.mainClass="game.RogueTD"'`

## Comment jouer ?

En attendant qu'il y ai un veritable tutoriel il va faloir se contenté de ceci (y a un guide des touches quand meme !!!)

Éliminer les adversaires (actuellement sphère rouge) avant qu'il n'arrive au monument (actuellement gros cube attirance) pour cela, vous pouvez poser des tours qui gagneront en dégât si elles sont posées sur les emplacements les plus hauts ainsi qu'en augmentant de niveau, sachant que celles-ci gagnent de l'xp à chaque tir.  

Il est possible d'afficher les statistiques et stratégie de focus possible en effectuant un clic gauche sur une tourelle (ou sur la cellule sur laquelle elle est posée), cet affichage pourra être fermé à l'aide d'un clic droit. 

Afin de lancer une nouvelle vague, il suffit d'appuyer sur le "+" qui s'affiche au bout du chemin.
