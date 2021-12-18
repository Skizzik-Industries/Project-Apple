# Skizzik & Co. [Project Apple]
[![CurseForge](http://cf.way2muchnoise.eu/versions/skizzik.svg)](https://www.curseforge.com/minecraft/mc-mods/skizzik)
[![Discord Server](https://img.shields.io/discord/591914197219016707.svg?color=7289da&label=Skizzium%20Server&logo=discord&style=flat-square)](https://discord.gg/5rjBEzT9Bm)

From seemingly harmless candies and rainbows, to dark back stories and multiversal aliens, this mod adds a lot of dimensions, boss battles and structures.

## Description:
You can choose your own adventure. Will it be fighting Princess Bubblegum for her throne or maybe going into a rainbow dimension which hides dark secrets.<br>
Maybe you want something even darker. Maybe you would like to go to a toxic dimension full of suffering mutants, taking the risk of becoming one of them.<br>
Or maybe you would like something more peaceful which can still benefit you a ton. If that's the case then you can explore the endless ScrapLands where people threw away their trash. Maybe you can find something interesting there.<br>
Don't like any of that? Well then you can experience the core of the mod: Skizzik boss battles.<br>
You can fight the distant nethery Wither relative: the original Skizzik.<br>
Or you can fight the new trio, appearing in all Skizzium projects and controlling the multiverse: The Cosmic Council.<br>
They can be found in the (Cosmic) Skizzik's origin dimension which is of course the Cosmium.<br>
What will it be? Chose wisely!

## Building the mod yourself
Tired of waiting for the next beta? Want to just experience one of the new features?<br>
Well, you can build the mod yourself then! This is one of the beauties of open source projects.<br>

**DISCLAMER: I don't promise stability. I'm taking my time for a reason. A lot of bugs and unbalanced features might be present.
If you end up building and testing the mod yourself, feedback would be appreciated in the [Discord](https://discord.gg/5rjBEzT9Bm) server, the [Guild](https://guilded.gg/skizzium) server or the [Issues](https://github.com/Skizzium/Project-Apple/issues)**

Now, onto the steps.
1. Download an IDE (the instructions will be for IntelliJ but Eclipse can be used too)
1. Clone the desired branch repository or download as zip
   * If you're unsure of what branch to chose, use the Development one.<br>
     The main branch (Forge-{Latest Version}) is the production branch, containing the same mod you can download from CurseForge.
1. Open the mod's files via the IDE
1. Wait for the loading to finish
1. Go to `build.gradle` and remove the `curseforge` and the `tasks.getByName("curseforge")` code blocks.
1. Open the terminal (on the bottom bar of IntelliJ) and type `./gradlew genIntellijRuns`
1. Once it's done, restart IntelliJ (or Eclipse)
1. After restarting, on the top right you'll see "runClient". Open the dropdown and select "runData".
1. After that, click the little green arrow and wait for the terminal to say "Finished with exit code 0"
1. Finally, you can re-open the previous terminal and type './gradlew build'
1. After that's done, you'll find the jar under `build/libs`

I'm willing to help out anyone trying to follow the steps. Just ask me on one of the previously mentioned places.
I'll respond as soon as possible. This way you're helping me out to find bugs and inbalanced features.
