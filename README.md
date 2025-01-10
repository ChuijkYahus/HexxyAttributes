# Hexxy Attributes

This modification adds a few Player Entity Attributes that may be used to change your experience playing [Hex Casting](https://github.com/FallingColors/HexMod).

### Attribute list


For now, there are 4 new attributes under hexxyattributes namespace:
- `hexxyattributes:feeble_mind` - Consider this as a boolean attribute, like `hexcasting:scry_sight`. 
  If player has Feeble Mind, they will not be able to open Staff's casting GUI.
  Players with feeble minds won't be able to use Scrying lenses, too.
  They are still able to use artifacts tho!! Base value is `0`, minimum is `0`, maximum is `1`.
- `hexxyattributes:media_consumption_modifier` - This sets the multiplying modifier:
  Imagine cost for the cast is 100 media points. The player with modifier of 0.5 will only pay 50 media points.
  On the other hand, player with modifier 1.7 will have to pay 170 media points.
  This also applies to taken health. Base value is `1`, minimum is `0`, maximum is `MAX_DOUBLE`.
- `hexxyattributes:ambit_radius` - As the name suggests, this attribute is used to determine if player's cast
  is inside their own ambit. Base value is `32`, minimum is `0`, maximum is `MAX_DOUBLE`.
- `hexxyattributes:sentinel_radius` - As the name suggests, this attribute is used to determine if player's cast
  is inside their sentinel's ambit. Base value is `16`, minimum is `0`, maximum is `MAX_DOUBLE`.

### Purpose and usage

While I was making a modpack for my friend group I tried to finetune Hex parameters for specific origins.
In my research I found out that there are no mods/addons that give you enough freedom to do what I was trying to achieve.
So I decided to create this little modification as an intermediary between Hex Casting and [Origins](https://github.com/apace100/origins-fabric).

At first, I just wanted to create specific origin powers for my case, but then my idea shifted into the direction of general purpose addon.
If you want to use this addon with the Origins mod, you can check out my [example datapack](example-origins-datapack) that uses new attributes.

I am not very active in modding scene, so don't expect a lot of updates or ports (if any would even be requested).
But feel free to ask questions and report bugs on the [issue page](https://github.com/JustS-js/HexxyAttributes/issues), fork or create pull requests. 

### License

This addon is available under the CC0 license. Feel free to learn from it and use it in your modpacks.
