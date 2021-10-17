#!/bin/bash

# Java 11 and Maven have be installed

# xterm installation -> based Debian OS
apt-get update
apt-get install -y xterm

# Clean data
find . -name *addressBook.json -delete

# Build maven projects
mvn clean install

# Run Address Book Backend - Some data already there.
xterm -fn 10x20 -maximized -hold -e "cd ./address-book-backend/target/ && java -jar address-book-backend-1.0.0-SNAPSHOT.jar" &

sleep 5

# Run Address Book Command Line
xterm -fn 10x20 -maximized -hold -e "cd ./address-book-frontend-command-line/target/ && java -jar address-book-front-command-line-1.0.0-SNAPSHOT.jar" &

sleep 5

# Run Address Book Web
(cd address-book-frontend-web && npm install && npm start)
