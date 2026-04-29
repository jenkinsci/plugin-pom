def log = new File(basedir, 'build.log').getText('UTF-8')
// Verify the rule actually executed and detected the obsolete override
assert log.contains('[RequireNonObsoleteDependencyManagement] Found')
assert log.contains('Found obsolete dependency version overrides')
assert log.contains('org.jenkins-ci.plugins:credentials')
assert log.contains('declared 1.200')
return true
