## Introduction
ICareRest is the rest api service for the Eye clinic software. The
frontend of the same is in the repository of ICareUI.

## SetUp
Below steps can be used to setup this repository

### Pre-requisites

* Git client - https://git-scm.com/download/gui/linux ( Version : 1.9.1)
* Gradle - https://gradle.org/install/#with-a-package-manager ( Version 2.14 )
* MongoDB - https://docs.mongodb.com/manual/installation ( Version : 3.2.10 )

### Steps

1. Clone the repository using below command
```
    git clone https://github.com/AnandMuley/ICareRest
```
2. Switch to the folder and run the gradle command as below to start standalone jetty
server
```
    gradle clean build jettyRun
```
3. Use the postman rest client to make the REST requests to the server.
Default port is 8091. You can change it by updating the build.gradle below
property:
```
    httpPort=8091
```
Base Url of the Rest API is : http://localhost:8091/ICareRest/rest

## Contact
In case of any issues you can reach out to me at <br>
**EmailId:** [muley.anand66@gmail.com!](muley.anand66@gmail.com)

## Copyright
&copy; Anand Muley