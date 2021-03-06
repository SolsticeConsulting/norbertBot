# LATAM IDEA Bot.

This repo contains the source code of the LATAM IDEA Bot for **Slack**. Our original idea was to make a refactor of an existing Bot made using [JBot](https://github.com/rampatra/jbot). However, this library is not supported now so we decided to build a new application. 

The main features of this bot are the following:

* Based on a diversity and inclusivity word, it will give you a set of words that can be similar to the given word and you can one of those to read a definition.
* There is a special calendar that contains a list of diversity and inclusivity dates, each of those days will be announce in [#latam-idea](https://kinandcarta.slack.com/archives/C8N0CSMCG) and [#kc-latam-anuncios](https://kinandcarta.slack.com/archives/C8DRCNZPZ).
* The last day of the month, it will announce how many words were searched during that month.

As we mentioned before, this bot supports the LATAM IDEA Team, thefore all of those features are pretending to be used in Spanish.

### How to run the bot locally 
Those are the following tools that you need to run this project: 
* Java 15.0+
* IntelliJ. We strongly recommend to use this IDE since is pretty easy to load enviroment variables and connect it to a database.
* Gradle 7.0+ 
* ngrok 2.0+. See [How to download ngrok](https://ngrok.com/download)

After download all those tools and cloning the repo, you can open the project in IntelliJ and start with some previous step before running the project. 
1. To download the dependencies, you can use the following tutorial for IntelliJ. [How to download dependencies in IntelliJ](https://www.jetbrains.com/idea/guide/tutorials/working-with-gradle/gradle-dependencies/)
2. You need to load some enviroment variables in your IDE, you can follow this [tutorial](https://www.jetbrains.com/help/objc/add-environment-variables-and-program-arguments.html#add-environment-variables) to learn how to load them. These are the variables that you need to load: 

* **SLACK_BOT_TOKEN** : 

* **SLACK_APP_TOKEN** : 

* **SLACK_SIGNING_SECRET** : 

* **DATA_SOURCE** : 

* **INSTANCE_CONNECTION_NAME** : 

* **MY_CHANNELS** : 
