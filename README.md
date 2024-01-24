# Jwalk - Cracked

[Jwalk](https://staffwww.dcs.shef.ac.uk/people/A.Simons/jwalk/download.html) is an automated test generation suite. It requires a license file to be requested from the University of Sheffield in order for it to run, thankfully it is incredibly easy to circumvent the DRM, as I have done so here.

## Requirements

The only dependency needed is some version of java newer than 21, this is needed for your system to actually run `.jar` files, which is how Jwalk is distributed.

You may also need to give execute privileges to `./gradlew`, `./REPACK.sh`, and `./START.sh` on Linux. Do this with `chmod +x <file>`.

## Usage

I have already patched the source files to no longer require a license, all you need to do is:

 - run `./REPACK.sh` to recompile the patched source files into a jar file
    - This process uses the Gradle wrapper, which is built into this repository (you do not need to install `gradle` on your system)
    - The built `.jar` file can be found in `./build/libs/JwalkCracked-uber.jar`.
 - run `./START.sh` to start the program GUI
