// Build passes
def hpi = new File(basedir, 'target/ban-commons-lang-2-pass-it.jar')
assert hpi.exists()

def log = new File(basedir, 'build.log')
assert !log.text.contains('Use Commons Lang 2 (org.apache.commons.lang.*)')
assert !log.text.contains('org.apache.commons.lang.time.FastDateFormat')
assert !log.text.contains('(Line: 4, Matched by: org.apache.commons.lang.**)')
return true
