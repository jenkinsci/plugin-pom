def log = new File(basedir, 'build.log').getText('UTF-8')
// Verify the rule is loaded and executed
assert log.contains('RequireNonObsoleteDependencyManagement')
// Verify it detects the obsolete override
assert log.contains('Found obsolete dependency version overrides')
assert log.contains('org.jenkins-ci.plugins:credentials')
assert log.contains('declared 1.200')
return true
