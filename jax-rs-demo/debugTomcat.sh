#!/usr/bin/env bash

##############################################################################
##
##  Maven / Tomcat start up script for UN*X
##
##############################################################################


            #To remote debug, execute this command first:
            #============================================
            ##mvnDebug clean tomcat7:run-war -P debug -e
            #(this opens port 8080 as the debug port)


            #To add more memory to run Tomcat:
            #=================================
            #(Linux) export JAVA_OPTS="-Xmx512m -XX:MaxPermSize=512m"
            #(Windows) set JAVA_OPTS="-Xmx512m -XX:MaxPermSize=512m"

            #mvn tomcat7:run -e -P embedded
            #mvn tomcat7:run-war -e -P embedded

# Add default JVM options here. You can also use JAVA_OPTS to pass JVM options to this script.
#DEFAULT_JVM_OPTS=""

# Uncomment to increase memory for the JVM:
#JAVA_OPTS="-Xmx512m -XX:MaxPermSize=512m"

mvnDebug clean tomcat7:run-war -P debug -e

