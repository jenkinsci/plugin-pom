assert new File(basedir, 'build.log').text.replaceAll(/\e\[[\d;]*[^\d;]/, '').contains('java.lang.IllegalArgumentException: Unknown JDK version given. Should be something like "1.7"')

return true
