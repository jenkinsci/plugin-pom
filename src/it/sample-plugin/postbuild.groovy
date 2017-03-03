assert new File(basedir, 'target/sample-plugin.hpi').exists()
assert new File(basedir, 'target/sample-plugin.jar').exists()

File installed = new File(basedir, '../../local-repo/org/jenkins-ci/plugins/its/sample-plugin/1.0-SNAPSHOT/')
assert installed.directory

// TODO check no-test-jar=false (cf. maven-hpi-plugin/src/it/parent-2x)
// TODO check npm usage

return true
