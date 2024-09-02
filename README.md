
# DECLUTTER !!! 💆

### Declutter is a open soruce command line tool to help users organize their Downloads folder






![Logo](https://stackify.com/wp-content/uploads/2017/05/Command-Line-881x441-1.jpg)


## Authors 📚️
 - [@kam-stand](https://github.com/kam-stand)


## Contributing 💁

Contributions are always welcome!

See `contributing.md` for ways to get started.



## Installation 🏗️

This is command line tool written in java ☕. Therefore, you need at least jdk 8 or higher to run this program.

1. clone this repository to your local computer.
``` bash
    git clone https://github.com/kam-stand/Declutter.git 
```
2. move to the root of the project directory and run the following commands
```bash
  javac command-line.java
  java command-line
```
    
## FAQ


#### Will Declutter add more features?
Absolutely, Declutter is in its early stages please see the contributon.md to add more feaures or submit an issue

#### Will this be able to run in the background?

Yes, this is only the first version of Declutter and I do plan to automate the organization process

#### Can Declutter listen to other directories outside of downloads?

1. Declutter is in its early stage of development and very soon will **prompt users for which directory to Declutter**
2. Declutter will also plan to automate cleaning of files open initial set for now please follow the directions below to organize ***one* directory at a time**

#### Directions
- open the source code command-line.java
- change **"/Downloads"** to your desired directory
- *Optional: you can also change "user.home"*
``` java
String directory = System.getProperty("user.home") + "/Downloads";
```

## Tech 🧑‍💻

**Language:** Java

**Operating System:** Linux


## Support

For support <mailto:kh84590@gmail.com> or submit an issue.

