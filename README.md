**Have questions? Visit the wiki or get help on discord:**

**Discord: https://discord.gg/7nAYtdyuEA**

![](https://i.imgur.com/EOAWyP4.gif)


**What is SimpleHeal?**

SimpleHeal is a lightweight utility plugin that allows Players to easily heal or feed themselves. This is very easy to give as a playtime or donation perk on your survival server! There is a 60 second default cooldown, so it is not too overpowered.

Current features:
- **/heal** to heal yourself
- **/feed** to feed yourself
- **/heal** [player] to heal another player
- **/feed** [player] to feed another player
- Fully customizable messages and cooldown
- Permission Support

**Permissions:**
- Heal yourself: **simpleheal.heal**
- Heal others: **simpleheal.heal.others**
- Feed yourself: **simpleheal.feed**
- Feed others: **simpleheal.feed.others**


**Config:**

```yaml
# SIMPLEHEAL CONFIG #

# COOLDOWN DURATION #
healCooldown: 60
feedCooldown: 60

# ERROR MESSAGES #
playersOnly: §cThis is for Players only Console!
noPermission: §cYou don't have permission to use that command.
playerNotFound: §cPlayer was not found

# PLAYER MESSAGES #
healMessage: §aYou have been healed.
feedMessage: §aYou have been fed.
cantHeal: §eYou cannot heal again for another
cantFeed: §eYou cannot satisfy your hunger again for another
healedBy: §aYou have been healed by
youHealed: §aYou have just healed
fedBy: §aYou have been fed by
youFed: §aYou have just fed
```



Created and tested on Minecraft 1.20, but it should work with most older versions as well. This is my first public plugin! I would love any feedback you have, PRs are welcome as the project is open source!


Good luck!