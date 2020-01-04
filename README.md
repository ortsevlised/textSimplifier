# Text Simplifier

>“Never use a long word when a short one will do. Never use a foreign phrase, a  scientific word, or a jargon word if you can think of an everyday English equivalent.” 
-**George Orwell**.

##### This application allows the user to enter text into a console and dynamically swap every word for its synonym from the Google list of 1,000 most common words in English
It makes use of the JNativeHook library to add a listener to the application.
JNativeHook leverages platform-dependent native code through Java's native interface to create low-level system-wide hooks and deliver those events to the application.
<br/>
<br/>
*Depending on your set up it might ask for permission to run on your computer, please allow it.*
<br/>
<br/>

***

## Compile
```
$ javac -cp .:jnativehook-2.1.0.jar  ie/gmit/dip/*.java
```
## Run
```
$ java -cp .:jnativehook-2.1.0.jar  ie.gmit.dip.Runner
```
