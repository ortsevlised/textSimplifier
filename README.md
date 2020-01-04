# Text Simplifier

>“Never use a long word when a short one will do. Never use a foreign phrase, a  scientific word, or a jargon word if you can think of an everyday English equivalent.” 
-**George Orwell**.

### This application allows the user to enter text into a console and dynamically swap every word for its synonym from the Google list of 1,000 most common words in English
- It makes use of the JNativeHook library to add a listener to the application.
JNativeHook leverages platform-dependent native code through Java's native interface to create low-level system-wide hooks and deliver those events to the application.
- Depending on your set up it might ask for permission to run on your computer, please allow it.
<br/>
- Lambda expressions and functional programming were used when possible as made the code easier to read and the performance difference is not noticeable in such a small app.
<br/>

***

## Run using the compiled jars from /textSimplifier path:

```
$ java -cp .:./simplifier.jar:jnativehook-2.1.0.jar ie.gmit.dip.Runner
```
I assume that the list of 1000 Google words and the thesaurus is available in your local environment.
<br/>

***
## In case you get any error just run the following steps:
*For Windows, ":" should be replaced with ";".*
## Compile
```
$ javac -cp .:jnativehook-2.1.0.jar  ie/gmit/dip/*.java
```
## Run
```
$ java -cp .:jnativehook-2.1.0.jar  ie.gmit.dip.Runner
```
The application was developed using IntelliJ IDEA 2019.1 on mac OS High Sierra Version 10.13.6
It works flawless on the IDE console. However, there might be some issues if ran on others setups.

## Known issues:
- When the google equivalent word is shorter than the word originally entered there might some residual text from the previous word. I imagine it is because the use of '/r' is not the same in every OS, On IntelliJ console works fine.
- The words entered are submitted to the OS command line, and empty scanner to capture this input was implemented, but it will be properly fixed on next release, again in the IntelliJ console works fine.
- Most versions of Windows don't support ANSI colors on the console so the app might not work there.

## To do:
-  Add tests