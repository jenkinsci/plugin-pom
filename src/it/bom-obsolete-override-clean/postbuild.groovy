def log = new File(basedir, 'build.log').getText('UTF-8')
// Verify the rule actually executed (not skipped) and found no violations
assert log.contains('io.jenkins.tools.maven.jenkins_enforcer_rules.BanObsoleteDependencyOverrides passed')
return true
