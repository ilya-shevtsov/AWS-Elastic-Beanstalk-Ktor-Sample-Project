# AWS Elastic Beanstalk Ktor Project
This is an AWS Elastic Beanstalk with Ktor Project. You can clone it and use it or follow the instructions and make one yourself.  
Here is the complete guide to make such project yourself. To make a project I will be using IntelliJ IDEA.

# Setting up a project 
## 1. Make a project
First you have to make a project. Click File - New - Project.  

The settings are: 
- Language – Kotlin 
- Build system – Gradle 
- Gradle DSL – Kotlin 
Should look something like this:

<img src="README%20Images/project.jpg" width="400">


## 2. Setting up build.gradle.kts 
Now you have to setup your Gradle file. 
- add this to the repositories   
```Kotlin
maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }  
```
- add this 
``` Kotlin
implementation("io.ktor:ktor-server-core:2.0.3")
implementation("io.ktor:ktor-server-netty:2.0.3")
implementation("ch.qos.logback:logback-classic:1.2.1")
```
- replace mainClass.set to
``` Kotlin
mainClass.set("io.ktor.server.netty.EngineMain")
```
- add "application.conf" file to resources and past this code into it
``` Kotlin
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
``` Kotlin
id("com.github.johnrengelman.shadow") version "7.1.2" (for shadow file for AWS)
```
## 3. Make the server 
Now you set up your server (the project it self)
- add this to your main file "Application.kt". "/haha" - is the path that will be added to the url and response - is the thing that the server will output
``` Kotlin
fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {

    routing {
        get("/haha"){
            val response = "keke"
            call.respond(response)
        }
    }
}
```
## 4. Make a FatJar file 
Now you have to make a FatJar file. This is your project that will be uploaded to the AWS.
- go to the terminal and type 
``` Kotlin
./gradlew shadowJar
```
the file should appear in the Build – libs folder
# Uploading the project to AWS Beanstalk Services
Now you have to upload the FatJar file to the AWS Beanstalk services. To do so, firstly you have to make an AWS account and then do the following steps.
## Create an application 
- type "Elastic Beanstalk into the search bar 

<img src="README%20Images/serch.jpg" width="400">

- click "Create Application"

<img src="README%20Images/create.jpg" width="400">

- name your application

<img src="README%20Images/name.jpg" width="400">

- platform settings

<img src="README%20Images/platform.jpg" width="400">

- click the upload your code and choose the FatJar file we have created before (its in build-libs path)

<img src="README%20Images/code.jpg" width="400">

Click create and wait for it for finish creating the environment (it might take a while). If it takes more than 10 minutes, then click “Services” and then “Elastic Beanstalk”.
The Elastic Beanstalk page should look something like this:

<img src="README%20Images/server.jpg" width="400">

The “Health” might be indicated as "Severe", but the server works. Now click the environment name, here you can see the causes of the "Health" status, update your project and most important here is where the URL of your server is shown. It’s the link at the top that starts with the name of your project.  

<img src="README%20Images/url.jpg" width="400">

You can copy the URL, add the path (/haha in our case) and you will get the response. That is it, now you have a working project.
