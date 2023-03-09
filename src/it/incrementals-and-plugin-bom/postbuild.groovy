assert new File(basedir, '../../local-repo/io/jenkins/plugins/incrementals-and-plugin-bom/1.0-SNAPSHOT/incrementals-and-plugin-bom-1.0-SNAPSHOT.hpi').file
assert new File(basedir, '../../local-repo/io/jenkins/plugins/incrementals-and-plugin-bom/1.0-rc1234.deadbeef5678/incrementals-and-plugin-bom-1.0-rc1234.deadbeef5678.hpi').file
String pomXml = new File(basedir, '../../local-repo/io/jenkins/plugins/incrementals-and-plugin-bom/1.0-rc1234.deadbeef5678/incrementals-and-plugin-bom-1.0-rc1234.deadbeef5678.pom').text
assert pomXml.contains('version>1.0-rc1234.deadbeef5678</version>')
// https://github.com/jenkinsci/plugin-pom/issues/705
// line endings need normalising
assert pomXml.matches('(?s).*<dependencyManagement>\\s*<dependencies>'
// something from the parent pom dependencyManagement
+ '.*<dependency>\\s*<groupId>javax.servlet</groupId>\\s*<artifactId>javax.servlet-api</artifactId>\\s*<version>3.1.0</version>\\s*</dependency>'
// something from the bom
+ '.*<dependency>\\s*<groupId>io.jenkins.plugins</groupId>\\s*<artifactId>caffeine-api</artifactId>\\s*<version>2.9.3-65.v6a_47d0f4d1fe</version>\\s*</dependency>'
+ '.*</dependencies>\\s*</dependencyManagement>.*')
return true
