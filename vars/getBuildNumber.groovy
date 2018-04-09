def call() {
    def newBuildNumber = 0
	lock('buildFile') {
		def buildFile = '${env.workspace}/BuildVersion.json'
		def buildFileData
		
		if(!fileExist(buildFile) {
			touch buildFile
			def seed = '{"version":"1"}'
			buildFileData = readJSON text: seed
		} else {
			buildFileData = readJSON file: buildFile
		}
		
		newBuildNumber = buildFileData.version as Integer
		newBuildNumber = newBuildNumber	+ 1
		buildFileData.version = newBuildNumber
		writeJSON file: buildFile, json: buildFileData
	}
	
	return newBuildNumber;
}