Weblogic requires some manual steps to setup riches.

weblogic 10.3
1) create a new weblogic domain from scratch using 
<weblogic home>/common/bin/config.cmd

make sure to select a jdk version that matches what you built riches with. I used SUN jdk 1.6.

2) JDBC resource setup
2a) create a mysql database named riches.  Use riches.sql to seed it. I created a user root, password passw0rd

2b)
-go into the weblogic admin console services->jdbc->data sources
-create a new datasource 
   name: jdbc/riches
   URL: jdbc:mysql://localhost:3306/riches
   Driver: com.mysql.jdbc.Driver
   properties: user=root
   password: passw0rd
   test table: AUTH
-go to the targets tab, and associate the AdminServer with the datasource
   
3) Security Realm
create a new security realm.  You can do this through the admin console Security Realms->myrealm->Providers
Name: richesSQLAuth
type: SQLAuthenticator

click 'ok'. It'll take you to the list of Authenticators.  Select 'richesSQLAuth'
-set 'Control Flag' to 'SUFFICIENT'  -> click 'save'
- go to the Provider Specific tab
  -check 'Plaintext Passwords Enabled'
  -datasource name: jdbc/riches
  - all the sql statements need to be changed to match the riches schema
  -deselect 'Descriptions Supported'

Go back to providers and select 'DefaultAuthenticator'.  Set it's 'Control Flag' to 'SUFFICIENT'  
  
Once you've created and saved this, you'll see some info in the config.xml (ie - C:\bea\user_projects\domains\riches\config\config.xml)
Make sure it looks approximately the same

  <security-configuration>
    <name>riches</name>
    <realm>
      <sec:authentication-provider xsi:type="wls:sql-authenticatorType">
        <sec:name>richesSQLAuth</sec:name>
        <sec:control-flag>SUFFICIENT</sec:control-flag>
        <wls:enable-group-membership-lookup-hierarchy-caching>false</wls:enable-group-membership-lookup-hierarchy-caching>
        <wls:data-source-name>jdbc/riches</wls:data-source-name>
        <wls:plaintext-passwords-enabled>true</wls:plaintext-passwords-enabled>
        <wls:descriptions-supported>false</wls:descriptions-supported>
        <wls:sql-get-users-password>SELECT PASSWORD FROM PROFILE WHERE USERNAME = ?</wls:sql-get-users-password>
        <wls:sql-user-exists>SELECT USERNAME FROM PROFILE WHERE USERNAME = ?</wls:sql-user-exists>
        <wls:sql-list-member-groups>SELECT ROLENAME FROM ROLE WHERE USERNAME = ?</wls:sql-list-member-groups>
        <wls:sql-list-users>SELECT USERNAME FROM PROFILE WHERE USERNAME LIKE ?</wls:sql-list-users>
        <wls:sql-list-groups>SELECT ROLENAME FROM AUTH WHERE ROLENAME LIKE ?</wls:sql-list-groups>
        <wls:sql-group-exists>SELECT ROLENAME FROM AUTH WHERE ROLENAME = ?</wls:sql-group-exists>
        <wls:sql-is-member>SELECT USERNAME FROM ROLE WHERE ROLENAME = ? AND USERNAME = ?</wls:sql-is-member>
        <wls:sql-create-user>INSERT INTO PROFILE (USERNAME, PASSWORD) VALUES ( ? , ? )</wls:sql-create-user>
        <wls:sql-remove-user>DELETE FROM PROFILE WHERE USERNAME = ?</wls:sql-remove-user>
        <wls:sql-remove-group-memberships>DELETE FROM ROLE WHERE USERNAME = ? OR ROLENAME = ?</wls:sql-remove-group-memberships>
        <wls:sql-set-user-password>UPDATE PROFILE SET PASSWORD = ? WHERE USERNAME = ?</wls:sql-set-user-password>
        <wls:sql-create-group>INSERT INTO AUTH (ROLENAME) VALUES ( ? )</wls:sql-create-group>
        <wls:sql-add-member-to-group>INSERT INTO ROLE (USERNAME, ROLENAME) VALUES( ?, ?)</wls:sql-add-member-to-group>
        <wls:sql-remove-member-from-group>DELETE FROM ROLE WHERE ROLENAME = ? AND USERNAME = ?</wls:sql-remove-member-from-group>
        <wls:sql-remove-group>DELETE FROM AUTH WHERE ROLENAME = ?</wls:sql-remove-group>
        <wls:sql-remove-group-member>DELETE FROM ROLE WHERE ROLENAME = ?</wls:sql-remove-group-member>
        <wls:sql-list-group-members>SELECT USERNAME FROM ROLE WHERE ROLENAME = ? AND USERNAME LIKE ?</wls:sql-list-group-members>
      </sec:authentication-provider>
      <sec:authentication-provider xsi:type="wls:default-authenticatorType">
        <sec:control-flag>SUFFICIENT</sec:control-flag>
        <wls:propagate-cause-for-login-exception>true</wls:propagate-cause-for-login-exception>
      </sec:authentication-provider>
      <sec:authentication-provider xsi:type="wls:default-identity-asserterType">
        <sec:active-type>AuthenticatedUser</sec:active-type>
      </sec:authentication-provider>
      <sec:role-mapper xmlns:xac="http://www.bea.com/ns/weblogic/90/security/xacml" xsi:type="xac:xacml-role-mapperType"></sec:role-mapper>
      <sec:authorizer xmlns:xac="http://www.bea.com/ns/weblogic/90/security/xacml" xsi:type="xac:xacml-authorizerType"></sec:authorizer>
      <sec:adjudicator xsi:type="wls:default-adjudicatorType"></sec:adjudicator>
      <sec:credential-mapper xsi:type="wls:default-credential-mapperType"></sec:credential-mapper>
      <sec:cert-path-provider xsi:type="wls:web-logic-cert-path-providerType"></sec:cert-path-provider>
      <sec:cert-path-builder>WebLogicCertPathProvider</sec:cert-path-builder>
      <sec:name>myrealm</sec:name>
    </realm>
    <default-realm>myrealm</default-realm>
    <credential-encrypted>{3DES}lKI0NI7vU+pX6NWBG3jhzI28INtD7ud5aBdjJSxql5vl3NpuYWOkxK0Gz9oD/oltI7hfo0uEFskAJNYpzI5xCMwqgQcn/NL9</credential-encrypted>
    <node-manager-username>weblogic</node-manager-username>
    <node-manager-password-encrypted>{3DES}YvdT2GJL63y9rF3lKS8GwQ==</node-manager-password-encrypted>
  </security-configuration>

4) Once you've configured this, stick riches.war into the autodeploy directory (ie - C:\bea\user_projects\domains\riches\autodeploy)
**NOTE, this doesn't seem to work for weblogic 9.2, so deploy via the admin console
Riches will be deployed and you can browse to it at:

http://localhost:7001/riches
   