assert new File(basedir, 'target/cd-plugin.hpi').file
assert new File(basedir, 'target/cd-plugin.jar').file

File installed = new File(basedir, '../../local-repo/org/jenkins-ci/plugins/its/cd-plugin/1234.deadbeef5678/')
assert new File(installed, 'cd-plugin-1234.deadbeef5678.hpi').file

def targetPom = new File(basedir, 'target/cd-plugin-1234.deadbeef5678.pom')
assert targetPom.file

def installedPom = new File(installed, 'cd-plugin-1234.deadbeef5678.pom')
assert installedPom.file

assert installedPom.text.contains("<name>")
assert installedPom.text.contains("<description>")
assert installedPom.text.contains("<dependencies>")
assert installedPom.text.contains("<build>")
assert installedPom.text.contains("<scm>")
assert !installedPom.text.contains("<properties>")
assert !installedPom.text.contains("<dependencyManagement>")
assert !installedPom.text.contains("<repositories>")
assert !installedPom.text.contains("<pluginRepositories>")

return true
