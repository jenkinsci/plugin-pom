// check if the benchmark was run
def file = new File(basedir, 'target/benchmark-run')
assert file.exists()
file.deleteDir()
