# Incrementals

See [JENKINS-50686](https://issues.jenkins-ci.org/browse/JENKINS-50686) for status and context.

## Enabling consumption of incrementals

If your plugin has (or may have) dependencies on incremental versions, run:

```bash
mkdir -p .mvn && echo -Pconsume-incrementals >> .mvn/maven.config && git add .mvn
```

(See [this guide](https://maven.apache.org/docs/3.3.1/release-notes.html#JVM_and_Command_Line_Options) for details on the `.mvn` directory.)

This profile merely activates access to the [Incrementals repository](https://repo.jenkins-ci.org/incrementals/).
If you wish to test usage offline, run

```bash
docker run --rm --name nexus -p 8081:8081 -v nexus-data:/nexus-data sonatype/nexus3
```

add to your `~/.m2/settings.xml`:

```xml
<servers>
  <server>
    <id>incrementals</id>
    <username>admin</username>
    <password>admin123</password>
  </server>
</servers>
```

and then add to command lines:

```
-Dincrementals.url=http://localhost:8081/repository/maven-releases/
```

## Enabling production of incrementals

To produce incremental artifacts _from_ your plugin, first edit your `pom.xml`.
If your plugin declares

```xml
<version>1.23-SNAPSHOT</version>
```

then replace that with

```xml
<version>${revision}${changelist}</version>
```

and then in the `<properties>` section add

```xml
<revision>1.23</revision>
<changelist>-SNAPSHOT</changelist>
```

Now run

```bash
echo .flattened-pom.xml >> .gitignore
```

Finally, configure [git-changelist-maven-extension](https://github.com/jglick/git-changelist-maven-extension) in `.mvn/extensions.xml`:

```xml
<extensions xmlns="http://maven.apache.org/EXTENSIONS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/EXTENSIONS/1.0.0 http://maven.apache.org/xsd/core-extensions-1.0.0.xsd">
  <extension>
    <groupId>io.jenkins.tools</groupId>
    <artifactId>git-changelist-maven-extension</artifactId>
    <version>1.0-SNAPSHOT</version>
  </extension>
</extensions>
```

Now if you are authorized to deploy to the Incrementals repository you could run:

```bash
mvn -Dset.changelist clean deploy
```

(See above for local testing; the same `incrementals.url` property may be used to override the deployment destination.)

To produce equivalent artifacts in your local repository while working offline:

```bash
mvn -Dset.changelist -DskipTests clean install
```

If you do not select the `-Dset.changelist` option, you will create a regular `*-SNAPSHOT` artifact.
(And that is what you _must_ do if you have any local modifications or untracked files.)
