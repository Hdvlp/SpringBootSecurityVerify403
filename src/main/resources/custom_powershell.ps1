

$env:processAppDebugging="true";
$env:processAppDataSourceDriverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
$env:processAppDatabasePlatform="org.hibernate.dialect.SQLServer2012Dialect";
$env:processAppDataSourceUrl="jdbc:sqlserver://localhost;databaseName=springbootsecurityverify403;encrypt=false;trustServerCertificate=false;";
$env:processAppDataSourceUsername="sa";
$env:processAppDataSourcePassword="YourPassword";
./mvnw spring-boot:run;

