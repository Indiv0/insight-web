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
mvn -P standalone clean compile package
```

* Execute the server:

```
java -jar target/insight-web-1.0.0.jar
```

## Server

* Deploy to your Tomcat server:

```
mvn clean compile package tomcat7:undeploy tomcat7:deploy
    -Dtomcat.url=URL
    -Dtomcat.username=USERNAME
    -Dtomcat.password=PASSWORD
```

### Downloading the Connector

[Download](https://downloads.mariadb.org/client-java/1.1.5/) the MariaDB connector jar and add it to your ```tomcat/lib/``` directory.

# SSL Setup

To ensure a secure environment and reduce the risk of a MITM attack, please enable SSL on your server by following [these instructions](http://www.mulesoft.com/tomcat-ssl).

# Server Setup

* Create the database on your sql server:

```
CREATE DATABASE insight;
```

* Run the server at least one to create the property files (`insightweb-auth.properties`, `insightweb-main.properties`, `insightweb.properties`).

* Replace the placeholder values.

  * In `insightweb.properties`, modify any needed configuration parameters.

  * In `insightweb-main.properties` replace the placeholder values with the connection information for your Insight logging server.

  * In `insightweb-auth.properties` replace the placeholder values with the connection information for your insight-web authentication server.

* Run the server again and if it manages to connect to your provided authentication database, it will automatically create the needed authentication tables and fill them with default data.

# Logging In

If you have correctly setup the [authentication](https://github.com/Indiv0/insight-web#server-setup), then use the default credentials:

username: `admin`
password: `admin`

Or:

username: `user`
password: `admin`


# Common Issues

* If you cannot deploy, ensure you have provided the correct `URL`, `USERNAME`, and `PASSWORD`.
* If you are getting authorization errors when deploying (e.g. HTTP 401 or 403), then ensure the user you are using to deploy has the `manager-script` role.

