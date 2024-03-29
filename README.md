# Jira Toolbox Java Application

The Jira Toolbox Java Application is a desktop application built with Java 21 and JavaFX that serves as a toolbox to help users manage various aspects of Jira using the Java REST API.

## Features

- **Holiday Management**: From a magnificent calendar, send your next holidays to your jira instance (this is not the right tool for that though).
- **IDK**: I might remove later

## Usage
### Prerequisites
Download the [JDK 21](https://www.oracle.com/fr/java/technologies/downloads/)
 
### Run
Download the last jar released and start it

## Build at home
### Prerequisites

 - Download the [JDK 21](https://www.oracle.com/fr/java/technologies/downloads/)
 - Download [JavaFX 21](https://gluonhq.com/products/javafx/)
 - Download [Maven](https://maven.apache.org/download.cgi)
 - Your IDE of choice
 
### Installation
1. Clone the repository in your workspace:

```bash
git clone https://github.com/CamDevHub/jiratoolbox
```

2. Navigate to the directory:

```bash
cd jiratoolbox
```

3. Build the project using Maven:

```bash
mvn clean package
```

4. Run the application:

```bash
java -jar shade/jiratoolbox.jar
```
