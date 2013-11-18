insight-web
===========

The administration web-interface for the Insight plugin.

# Installation

* Ensure that you have JRE 1.7.0 or above.
* Clone the project.
* Import the project into your IDE of choice.
* Ensure you have a Tomcat8 instance installed and running (if you are not planning on doing standalone deployment).

# Deployment

## Standalone

* Create the standalone .jar file:

```
mvn clean package -P standalone
```

* Execute the server:

```
java -jar target/insight-web-1.0.0.jar
```

## Server

* Deploy to your Tomcat server:

```
mvn clean package tomcat7:undeploy tomcat7:deploy
    -Dtomcat.url=URL
    -Dtomcat.username=USERNAME
    -Dtomcat.password=PASSWORD
```

# Common Issues

* If you cannot deploy, ensure you have provided the correct `URL`, `USERNAME`, and `PASSWORD`.
* If you are getting authorization errors when deploying (e.g. HTTP 401 or 403), then ensure the user you are using to deploy has the `manager-script` role.
