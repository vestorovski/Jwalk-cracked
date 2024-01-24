# Jwalk - Cracked

[Jwalk](https://staffwww.dcs.shef.ac.uk/people/A.Simons/jwalk/download.html) is an automated test generation suite. It requires a license file to be requested from the University of Sheffield in order for it to run, thankfully it is incredibly easy to circumvent the DRM, as I have done so here.

You can start using it now by downloading [this release](https://github.com/vestorovski/Jwalk-cracked/releases/download/v1.1/JwalkCracked-uber.jar). The `.jar` file can be an with `java -jar <file>` in your favourite terminal (Java must be in your PATH).

Follow the instructions below to recompile it for yourself.

## Requirements

The only dependency needed is some version of java newer than 21, this is needed for your system to actually run `.jar` files, which is how Jwalk is distributed. 

On Windows machines, you need the `java` binary added to your path and runnable via CMD. Instructions are a Google search away.

On Linux, you may also need to give execute privileges to `./gradlew`, `./REPACK.sh`, and `./START.sh`. Do this with `chmod +x <file>`.

## Usage

I have already patched the source files to no longer require a license, all you need to do is:

 - run `./REPACK.sh` or `.\REPACK.bat` to recompile the patched source files into a jar file
    - This process uses the Gradle wrapper, which is built into this repository (you do not need to install `gradle` on your system)
    - The built `.jar` file can be found in `./build/libs/JwalkCracked-uber.jar`.
 - run `./START.sh` or `.\START.bat` to start the program GUI
