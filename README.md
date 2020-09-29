# dncomponents-java-starter-bs
Starter project for java dncomponents with bootstrap ui.

## How to use
```
# clone project
$ git clone https://github.com/dncomponents/dncomponents-java-starter-bs.git

# change directory to project folder
cd  dncomponents-java-starter-bs

# Start developing and serve your app:
$ mvn gwt:devmode
```
For you convenience, install intellij dncomponents plugin.
https://plugins.jetbrains.com/plugin/13486-dn-components .
When editing html files it will generate needed code on a save. Just reload browser to see changes.

If you don't use Intellij and dncomponents plugin - after editing html files you'll have to either manually run a new terminal and execute:
```
$ mvn compile 
```
or run:
``` 
$ mvn fizzed-watcher:run
```
Which will monitor client folder for changes in html files and automatically run mvn compile command.
