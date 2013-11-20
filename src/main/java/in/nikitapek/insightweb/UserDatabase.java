package in.nikitapek.insightweb;

public class UserDatabase {
    String databaseCreationQuery = "DROP DATABASE IF EXISTS insight;\n" +
            "CREATE DATABASE insight;\n" +
            "USE insight;\n" +
            "CREATE TABLE tomcat_users (\n" +
            "    user_name varchar(20) NOT NULL PRIMARY KEY,\n" +
            "    password varchar(32) NOT NULL\n" +
            ");\n" +
            "CREATE TABLE tomcat_roles (\n" +
            "    role_name varchar(20) NOT NULL PRIMARY KEY\n" +
            ");\n" +
            "CREATE TABLE tomcat_users_roles (\n" +
            "    user_name varchar(20) NOT NULL,\n" +
            "    role_name varchar(20) NOT NULL,\n" +
            "    PRIMARY KEY (user_name, role_name),\n" +
            "    CONSTRAINT tomcat_users_roles_foreign_key_1 FOREIGN KEY (user_name) REFERENCES tomcat_users (user_name),\n" +
            "    CONSTRAINT tomcat_users_roles_foreign_key_2 FOREIGN KEY (role_name) REFERENCES tomcat_roles (role_name)\n" +
            ");\n" +
            "INSERT INTO tomcat_users (user_name, password) VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3');\n" +
            "INSERT INTO tomcat_roles (role_name) VALUES ('insight-user');\n" +
            "INSERT INTO tomcat_users_roles (user_name, role_name) VALUES ('admin', 'insight-user');\n" +
            "COMMIT;";
}
