# plugin-pom
Parent POM for Jenkins Plugins

## Introduction

This new parent POM is decoupled from the core Jenkins project, both from the Maven and repository perspectives.

The main changes are:
* Reduced number of overridable properties. All references (e.g. dependencies and plugin versions) not
thought to be overridden are no longer based on properties. The main remaining overridable properties are:
  * `jenkins.version`: The Jenkins version required by the plugin.
  * `jenkins-test-harness.version`: The JTH version used to test plugin.
  Uses split test-harness (see [JENKINS-32478](https://issues.jenkins-ci.org/browse/JENKINS-32478)).
  If the required Jenkins version is 1.580.1 or higher, JTH 2.1+ is recommended.
  * `hpi-plugin.version`: The HPI Maven Plugin version used by the plugin.
  * `stapler-plugin.version`: The Stapler Maven plugin version required by the plugin.
  * `java.level`: The Java version to use to build the plugin.
  * `java.level.test`: The Java version to use to build the plugin tests.
  * In order to make their versions the same as the used core version, `slf4jVersion`, `node.version` and `npm.version`
  properties are provided.
* Tests are skipped during the `perform` phase of a release (can be overridden by setting `release.skipTests` to false).
* General clean up.

Being able to specify the `jenkins.version` simplifies testing the plugin with different core versions, which is
important, among others, for the Plugin Compatibility Testing.

## Usage

In order to use the new POM:
* Change the parent POM of your plugin:
```xml
  <parent>
    <groupId>org.jenkins-ci.plugins</groupId>
    <artifactId>plugin</artifactId>
    <version>2.3</version> <!-- or later -->
  </parent>
```
* Override the needed properties, e.g.:
```xml
  <properties>
    <jenkins.version>1.609.1</jenkins.version>
    <hpi-plugin.version>1.106</hpi-plugin.version>
  </properties>
```

If you had a `jar:test-jar` execution, delete it and add to `properties`:

```xml
<no-test-jar>false</no-test-jar>
```

## Baselines

It is handy to be able to select different Jenkins baselines with a Maven profile.
To set this up, you must edit your `~/.m2/settings.xml` to include some new entries in the `<profiles>` section.
For example:

```xml
<profile>
    <id>jenkins-219</id>
    <properties>
        <jenkins.version>2.19.2</jenkins.version>
        <hpi-plugin.version>1.120</hpi-plugin.version>
        <stapler-plugin.version>1.17</stapler-plugin.version>
        <java.level>7</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-27</id>
    <properties>
        <jenkins.version>2.7.3</jenkins.version>
        <hpi-plugin.version>1.115</hpi-plugin.version>
        <stapler-plugin.version>1.17</stapler-plugin.version>
        <java.level>7</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-651</id>
    <properties>
        <jenkins.version>1.651.3</jenkins.version>
        <hpi-plugin.version>1.115</hpi-plugin.version>
        <stapler-plugin.version>1.17</stapler-plugin.version>
        <java.level>7</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-642</id>
    <properties>
        <jenkins.version>1.642.4</jenkins.version>
        <hpi-plugin.version>1.115</hpi-plugin.version>
        <stapler-plugin.version>1.17</stapler-plugin.version>
        <java.level>7</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-625</id>
    <properties>
        <jenkins.version>1.625.3</jenkins.version>
        <hpi-plugin.version>1.106</hpi-plugin.version>
        <stapler-plugin.version>1.17</stapler-plugin.version>
        <java.level>7</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-609</id>
    <properties>
        <jenkins.version>1.609.3</jenkins.version>
        <hpi-plugin.version>1.106</hpi-plugin.version>
        <stapler-plugin.version>1.17</stapler-plugin.version>
        <java.level>6</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-596</id>
    <properties>
        <jenkins.version>1.596.3</jenkins.version>
        <hpi-plugin.version>1.106</hpi-plugin.version>
        <stapler-plugin.version>1.17</stapler-plugin.version>
        <java.level>6</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-580</id>
    <properties>
        <jenkins.version>1.580.3</jenkins.version>
        <hpi-plugin.version>1.106</hpi-plugin.version>
        <stapler-plugin.version>1.17</stapler-plugin.version>
        <java.level>6</java.level>
    </properties>
</profile>
<profile>
    <id>jenkins-565</id>
    <properties>
        <jenkins.version>1.565.3</jenkins.version>
        <jenkins-test-harness.version>1.565.3</jenkins-test-harness.version>
        <hpi-plugin.version>1.106</hpi-plugin.version>
        <stapler-plugin.version>1.17</stapler-plugin.version>
        <java.level>6</java.level>
    </properties>
</profile>
```

Now for example if your plugin normally builds against 1.625.x, but you wish to test compatibility with 1.651.x,
there is no need to edit your POM. Just run:

    mvn -Pjenkins-651 clean test

or

    mvn -Pjenkins-651 hpi:run

## For maintainers

Before releasing changes, try the [integration test instructions for the `maven-hpi-plugin`](https://github.com/jenkinsci/maven-hpi-plugin/blob/master/README.md).
