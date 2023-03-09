assert new File(basedir, '../../local-repo/io/jenkins/plugins/incrementals-and-plugin-bom/1.0-SNAPSHOT/incrementals-and-plugin-bom-1.0-SNAPSHOT.hpi').file
assert new File(basedir, '../../local-repo/io/jenkins/plugins/incrementals-and-plugin-bom/1.0-rc1234.deadbeef5678/incrementals-and-plugin-bom-1.0-rc1234.deadbeef5678.hpi').file
String pomXml = new File(basedir, '../../local-repo/io/jenkins/plugins/incrementals-and-plugin-bom/1.0-rc1234.deadbeef5678/incrementals-and-plugin-bom-1.0-rc1234.deadbeef5678.pom').text
assert pomXml.contains('version>1.0-rc1234.deadbeef5678</version>')
// https://github.com/jenkinsci/plugin-pom/issues/705
// line endings need normalising
assert pomXml.replace("\r\n", "\n").contains('''
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>io.jenkins.tools.bom</groupId>
        <artifactId>bom-2.361.x</artifactId>
        <version>1580.v47b_429a_c853a</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>'''.replace("\r\n", "\n"))
return true
