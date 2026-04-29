def log = new File(basedir, 'build.log').getText('UTF-8')
// Verify the rule actually executed (not skipped) and found no violations
assert log.contains('[RequireNonObsoleteDependencyManagement] No obsolete overrides found')
return true
