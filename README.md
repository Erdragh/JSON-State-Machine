# JSON-State-Machine
 A sample implementation of [my Finite State Machine](https://github.com/Erdragh/Finite-State-Machine) that can read a language.json file

 If you want to modify this and contribute or just try this out yourself, you'll have to use my State Machine Library and [This: the JSON-java library](https://github.com/stleary/JSON-java)

However, in the releases tab you'll find a jar file you can execute, it already includes the JSON library and my final state machine:
```
java -jar /path/to/JSON-State-Machine-v_*_*_*.jar /path/to/*.json stringtotest
```

The example `language.json` in the repository is an implementation of natural numbers (positive without digits after a comma) with the thousands divided by ".".