FROM tomcat:8-jre11

RUN echo "export JAVA_OPTS=\"-Dapp.env=Internship\"" > /usr/local/tomcat/bin/setenv.sh
COPY target/Internship.war /usr/local/tomcat/webapps/Internship.war

CMD ["catalina.sh", "run"]

