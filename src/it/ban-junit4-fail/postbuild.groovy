// Build failed
def hpi = new File(basedir, 'target/ban-junit4-fail-it.jar')
assert !hpi.exists()

// Because of banned imports
def log = new File(basedir, 'build.log')
assert log.text.contains('Reason: Use JUnit Jupiter (org.junit.jupiter.*)')
assert log.text.contains('org.junit.Test')
assert log.text.contains('(Line: 3, Matched by: org.junit.**)')
assert log.text.contains('static org.junit.Assert.assertTrue')
assert log.text.contains('(Line: 5, Matched by: org.junit.**)')
return true
