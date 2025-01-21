[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/KprAwj1n)
# APCS - Stuyrim

## Features

:white_check_mark: play with a group of 3 different types adventurers

:white_check_mark: play against 1-3 randomly chosen adventurer opponents

:white_check_mark: use attack/special operations on your opponents

:white_check_mark: use support operations on your team

:white_check_mark: program ends when the user choosed to quit, or all enemies is defeated, or the entire party is defeated. A win/lose screen is useful here.

:white_check_mark: display the results of the attack/special/support inside your border. Do not print things at the bottom of the screen or it will scroll.

## Adventurer Subclasses

# **Barista**
- special: rating
- attack: make a really bad drink that makes the enemy throw up, decreasing their HP
- support: make a really delicious drink, increasing own HP and special
- special attack: throwing drinks, taking away own HP for more damage
- passive ability: if HP is below 50%, damage of any attack (both normal and special attack) is increased by 20%
# **PastryChef**
- special: sugar
- attack: make a pastry with raw eggs so that the opponent gets salmonella. They will then lose HP at every one of their subsequent turns until they are healed by either a teammate or themself.
- support: make a really good pastry that cheers up a chosen party member (including themself), increasing their HP and special
- special attack: if they have enough sugar, they will be able to use their normal attack on the entire enemy team
- passive ability: when sugar is below 30%, their pastry also will also taste really bad, so the opponent loses some HP upon eating it in addition to contracting salmonella
# **PrepChef**
- special: money
- attack: steals ingredients from their opponent, decreasing their special attribute
- support: they went outside, bought ingredients and prepped them, increasing HP and special of everyone in their party and increasing their damage for the next move. (This buff does not affect shield rebound damage.)
- special: they buy everyone on their team aprons, casting a shared shield over the entire team (which has shieldHP based on money consumed). If a shield is cast while an existing shield is still up, it will replace the existing shield. This shield will protect against the PrepChef's "stealing" attack regardless of the amount of shieldHP, but it will not protect against the salmonella status effect or the self-HP loss from the Barista special attack.
- passive ability: when casting the shield, there is a 50% chance the aprons are extra fancy and deal rebound damage when hit by any opponent. (PrepChef's "stealing" attack will not trigger rebound damage.)
