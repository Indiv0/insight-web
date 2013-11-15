insight-web
===========

The administration web-interface for the Insight plugin.

# Installation

* Clone the project
* Import the project into your IDE of choice
* Ensure you have a tomcat instance installed and running
* [Configure](http://stackoverflow.com/questions/6549504/maven-server-in-settings-xml) your settings.xml with the server login info.

# Deployment

## Standalone

* Run `mvn clean package tomcat7:exec-war-only` to create the standalone .war file
* Run `java -jar target/insight-web-1.0.0-war-exec.jar`

## Server

* Run `mvn clean package tomcat7:deploy -Dtomcat.url=URL -Dtomcat.username=USERNAME -Dtomcat.password=PASSWORD` to deploy to your local tomcat server.

# Common Issues

* If you cannot deploy, ensure you have provided the correct `URL`, `USERNAME`, and `PASSWORD`.
* If you are getting authorization errors when deploying (e.g. HTTP 401 or 403), then ensure the user you are using to deploy has the `manager-script` role.
