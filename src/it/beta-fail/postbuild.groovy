assert new File(basedir, 'build.log').text.contains('[ERROR] downstream/Caller:5 upstream/Api.experimental()V is still in beta')

return true
