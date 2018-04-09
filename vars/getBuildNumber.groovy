def call() {
    def newBuildNumber = 0
	lock('buildFile') {
		def buildFile = '${env.workspace}/BuildVersion.json'
		def buildFileData
		
		if(fileExists(buildFile)) {		
			buildFileData = readJSON file: buildFile
		} 
		else {
			touch buildFile
			def seed = '{"version":"1"}'
			buildFileData = readJSON text: seed
		}
		
		newBuildNumber = buildFileData.version as Integer
		newBuildNumber = newBuildNumber	+ 1
		buildFileData.version = newBuildNumber
		writeJSON file: buildFile, json: buildFileData
	}
	
	return newBuildNumber;
}