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

# SSL Setup

To ensure a secure environment and reduce the risk of a MITM attack, please enable SSL on your server by following [these instructions](http://www.mulesoft.com/tomcat-ssl).

# Authentication Setup

## Context.xml

Modify the following in your `tomcat/webapps/insight-web/META-INF/context.xml`:

```
<Realm localDataSource="true"
       dataSourceName="jdbc/insight"
       className="org.apache.catalina.realm.JDBCRealm"
       driverName="org.mariadb.jdbc.Driver"
       connectionURL="jdbc:mysql://[URL]:[Port]/[DatabaseName]"
        connectionName="[DatabaseUsername]" connectionPassword="[DatabasePassword]"
       userTable="tomcat_users" userNameCol="user_name" userCredCol="password"
       userRoleTable="tomcat_users_roles" roleNameCol="role_name" />
```

Replace the placeholder values (the values surrounded by square brackets) with your preferred database connection information (ensure you remove the square brackets themselves, those are only meant to help you find the things you need to replace).

## Creating the Table

For the same database as you provided above, run the following SQL query:

```
DROP DATABASE IF EXISTS [DatabaseName];
CREATE DATABASE [DatabaseName];
USE [DatabaseName];
CREATE TABLE tomcat_users (
    user_name varchar(20) NOT NULL PRIMARY KEY,
    password varchar(32) NOT NULL
);
CREATE TABLE tomcat_roles (
    role_name varchar(20) NOT NULL PRIMARY KEY
);
CREATE TABLE tomcat_users_roles (
    user_name varchar(20) NOT NULL,
    role_name varchar(20) NOT NULL,
    PRIMARY KEY (user_name, role_name),
    CONSTRAINT tomcat_users_roles_foreign_key_1 FOREIGN KEY (user_name) REFERENCES tomcat_users (user_name),
    CONSTRAINT tomcat_users_roles_foreign_key_2 FOREIGN KEY (role_name) REFERENCES tomcat_roles (role_name)
);
INSERT INTO tomcat_users (user_name, password) VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3');
INSERT INTO tomcat_roles (role_name) VALUES ('insight-user');
INSERT INTO tomcat_users_roles (user_name, role_name) VALUES ('admin', 'insight-user');
COMMIT;
```

Replace the placeholder values (the values surrounded by square brackets) with your preferred database connection information (ensure you remove the square brackets themselves, those are only meant to help you find the things you need to replace).

## Downloading the Connector

[Download](https://downloads.mariadb.org/client-java/1.1.5/) the MariaDB connector jar and add it to your ```tomcat/lib/``` directory.

# Common Issues

* If you cannot deploy, ensure you have provided the correct `URL`, `USERNAME`, and `PASSWORD`.
* If you are getting authorization errors when deploying (e.g. HTTP 401 or 403), then ensure the user you are using to deploy has the `manager-script` role.

# FAQ

## How do I login?

If you have correctly setup the [authentication](https://github.com/Indiv0/insight-web#authentication-setup), then use the default credentials, username: ```admin```, password: ```admin```.
