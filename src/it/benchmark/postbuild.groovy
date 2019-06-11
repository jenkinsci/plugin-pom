// check if the benchmark was run
def file = new File(basedir, 'jmh-report.json')
assert file.exists()
