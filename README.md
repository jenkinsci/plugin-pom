# plugin-pom
Parent POM for Jenkins Plugins (experimental)

Proposed POM to substitute current plugin parent POM.

This parent POM is decoupled from the core Jenkins project, both from the Maven and repository perspectives.
The current proposed Maven coordinates are temporal.

The main changes are:
* Reduced number of overridable properties. All references (e.g. dependencies and plugin versions) not
thought to be overridden are no longer based on properties. The main remaining overridable properties are:
  * `jenkins.version`: The Jenkins version required by the plugin. Current default is `1.625.2`
  * `jenkins-test-harness.version`: The JTH version used to test plugin. The default is `jenkins.version`.
  * `hpi-plugin.version`: The HPI Maven Plugin version used by the plugin. Current default is `1.115`
  * `stapler-plugin.version`: The Stapler Maven plugin version required by the plugin. Current default is `1.17`
  * `java.level`: The Java version to use to build the plugin. Current default is `7`
  * `java.level.test`: The Java version to use to build the plugin tests. Current default is `7`.
  * In order to make their versions the same as the used core version, `slf4jVersion`, `node.version` and `npm.version`
  properties are provided.
* Tests are skipped during the `perform` phase of a release.
* General clean up.

Being able to specify the `jenkins.version` simplifies testing the plugin with different core versions, which is
important, among others, for the Plugin Compatibility Testing.

In order to use the new (experimental) POM:
* Clone this repository and build it using `mvn clean install`
* Change the parent POM of your plugin:
```
  <parent>
    <groupId>org.jenkins-ci.plugins</groupId>
    <artifactId>plugin-pom</artifactId>
    <version>0.1-SNAPSHOT</version>
  </parent>
```
* Override the needed properties, e.g.:
```
  <properties>
    <jenkins.version>1.609.1</jenkins.version>
    <hpi-plugin.version>1.106</hpi-plugin.version>
    <maven.findbugs.failure.strict>true</maven.findbugs.failure.strict>
  </properties>
```