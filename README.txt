Tomcat 5.0 Support (the recommended server for running RICHES is Tomcat 5.0.28)

Riches requires Java 5.

TOMCAT_HOME is your top level tomcat directory

## FIRST TIME SETUP ##

copy WEB-INF/lib/hsqldb.jar to ${TOMCAT_HOME}/common/lib

For Tomcat 5.0, rename TOMCAT_HOME/common/endorsed/xml-apis.jar to xml-apis.jar.bak


## BUILDING AND RUNNING THE APPLICATION ##
run ant in this directory
copy dist/riches.war to ${TOMCAT_HOME}/webapps
NOTE: if you do not deploy via the .war you will additionally need to copy context.xml to ${TOMCAT_HOME}/conf/Catalina/localhost/riches.xml
start tomcat
browse to http://localhost:8080/riches
login with admin/admin, eddie/eddie, gary/gary or another profile defined in create_db.sql


## REDEPLOYING ##
stop tomcat ()
delete ${TOMCAT_HOME}/webapps/riches (delete the whole directory)
delete ${TOMCAT_HOME}/webapps/riches.war
follow the "building and runnig the application" instructions to deploy a fresh copy


## STARTING TOMCAT ##
run ${TOMCAT_HOME}/bin/startup

## STOPPING TOMCAT ##
run ${TOMCAT_HOME}/bin/shutdown



****************************************************************************

Tomcat 5.5 Support

use the instructions from above with the following change:
run 'ant tomcat5.5' to build the app instead of just 'ant'


****************************************************************************

JBoss 4 Support

JBOSS_SERVER_DIR is the jboss/server/default (unless you changed the configuration, in which case
you should know what this directory is supposed to be)


## FIRST TIME SETUP ##
copy WEB-INF/lib/hsqldb.jar to ${JBOSS_SERVER_DIR}/lib (this should overwrite an older version of hsqldb.jar)
create the directory ${JBOSS_SERVER_DIR}/data/hypersonic


## BUILDING AND RUNNING THE APPLICATION (as a .war) ##
run 'ant jboss4' in this directory
copy dist/riches.war to ${JBOSS_SERVER_DIR}/deploy
copy etc/jboss4-support/riches-ds.xml to ${JBOSS_SERVER_DIR}/deploy
copy WEB-INF/database/* to ${JBOSS_SERVER_DIR}/data/hypersonic
copy the context of etc/jboss4-support/login-config-fragment.xml into ${JBOSS_SERVER_DIR}/conf/login-config.xml right after the opening <policy> tag

## BUILDING AND RUNNING THE APPLICATION (as a .ear) ##
Same as above except run 'ant -Dserver=jboss4 ear'

********************************************************************************************

SSL Configuration for tomcat 5

1. Check JSSE set up
You need to have JSSE, it is bundled with Sun's JDK 1.4 and later.  You can also download JSSE from http://java.sun.com/products/jsse/

2. Create a certificate by executing the following command:

Windows:	
%JAVA_HOME%\bin\keytool -genkey -alias tomcat -keyalg RSA

Unix:
$JAVA_HOME/bin/keytool -genkey -alias tomcat -keyalg RSA

3. Enter default password use by tomcat: "changeit".  Fill out general information about the certificate, such as company, name, etc.

4. Finally, you will prompted for key password, MUST use the same password as keystore password itself.  (Just pressing ENTER key)

5. Uncomment the "SSL HTTP/1.1 Connector" entry in $CATALINA_HOME/conf/server.xml

6. Edit web.xml in riches/WEB-INF, add the following to <security-constrain> section

<user-data-constraint>
<transport-guarantee>CONFIDENTIAL</transport-guarantee>
</user-data-constraint>

	
		