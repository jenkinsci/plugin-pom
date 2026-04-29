def log = new File(basedir, 'build.log').getText('UTF-8')
// Verify the rule was skipped via property
assert log.contains('[RequireNonObsoleteDependencyManagement] Skipping rule')
// Should NOT contain the error message
assert !log.contains('Found obsolete dependency version overrides')
return true
