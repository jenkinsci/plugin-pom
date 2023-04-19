assert new File(basedir, 'build.log').text.replaceAll(/\e\[[\d;]*[^\d;]/, '').contains('[ERROR] downstream/Caller:7 upstream/Api.experimental()V is still in beta')

return true
