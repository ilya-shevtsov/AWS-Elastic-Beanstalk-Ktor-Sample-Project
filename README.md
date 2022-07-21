# AWS Elastic Beanstalk Ktor Project
This is a AWS Elastic Beanstalk with Ktor Project. You can clone it and use it or follow the instructions and make one yourself.  
Here are the complite guide to make such project yourself. To make a project I will be useing IntelliJ IDEA.

# Setting up a project 
## 1. Make a project
First you have to make a project. Click File - New - Project.  

The settings are: 
- Language – Kotlin 
- Build system – Gradle 
- Gradle DSL – Koltin 
Should look something like this:

<img src="README%20Images/Screenshot%202022-07-21%20184254.jpg" width="400">


## 2. Setting up build.gradle.kts 
Now you have to setup your gradle file. 
- add this to the repositories   
```koltin
maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }  
```
- add this 
```koltin
implementation("io.ktor:ktor-server-core:2.0.3")
implementation("io.ktor:ktor-server-netty:2.0.3")
implementation("ch.qos.logback:logback-classic:1.2.1")
```
- replace mainClass.set to
```koltin
mainClass.set("io.ktor.server.netty.EngineMain")
```
- add "application.conf" file to resources and past this code into it
```kotlin
ktor {
    deployment {
        port = 5000
        port = ${?PORT}
    }
    application {
        modules = [ ApplicationKt.module ]
    }
}
```
- add this to plugins
```koltin
id("com.github.johnrengelman.shadow") version "7.1.2" (for shadow file for AWS)
```
## 3. Make the server 
Now you set up your server (the project it self)
- add this to your main file "Application.kt". "/haha" - is the path that will be added to the url and responcse - is the thing that the server will output
```kotlin
fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {

    routing {
        get("/haha"){
            val response = "keke"
            call.respond(response)
        }
    

```
## 4. Make a FatJar file 
Now you have to make a FatJar file. This is your project that will be uploaded to the AWS.
- go to the terminal and type 
```koltin
./gradlew shadowJar
```
the file should appear in the Build – libs folder
# Uploading the project to AWS Beanstalk Services
Now you have to upload the FatJar file to the AWS Beanstalk services. To do so, firstly you have to make an AWS account and than do the following steps.
## Create an application 
- type "Elastic Beanstalk into the serch bar 

<img src="README%20Images/Picture1.png">

- click "Create Application"

<img src="README%20Images/Picture2.png">
