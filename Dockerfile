FROM tomcat:8-jre11

RUN echo "export JAVA_OPTS=\"-Dapp.env=Intership_Poject_Web_Department\"" > /usr/local/tomcat/bin/setenv.sh
COPY target/Intership_Poject_Web_Department.war /usr/local/tomcat/webapps/Intership_Poject_Web_Department.war

CMD ["catalina.sh", "run"]
