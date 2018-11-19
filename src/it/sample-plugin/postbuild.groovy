import java.util.jar.JarFile

assert new File(basedir, 'target/sample-plugin.hpi').exists()
assert new File(basedir, 'target/sample-plugin.jar').exists()

File installed = new File(basedir, '../../local-repo/org/jenkins-ci/plugins/its/sample-plugin/1.0-SNAPSHOT/')
File f = new File(installed, 'sample-plugin-1.0-SNAPSHOT.hpi')
assert f.file
JarFile j = new JarFile(f)
try {
    assert j.entries().toList().collect {it.name}.grep {it =~ 'WEB-INF/lib/.+[.]jar'} == ['WEB-INF/lib/sample-plugin.jar']
} finally {
    j.close()
}

assert !new File(basedir, 'target/surefire-reports/test.SampleRootActionTest-output.txt').text.contains('http://www.slf4j.org/codes.html#release')

// TODO check no-test-jar=false (cf. maven-hpi-plugin/src/it/parent-2x)
// TODO check npm usage

return true
