def call(body) {		
	// transfer parameters
	def config = [:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()
	
	def buildData = readYaml file: 'build.yml'
    def date = new Date()

	buildData.argumentString = ""
	if(buildData.build.argument != null) {
		println("arguments:")
		for(element in buildData.build.argument) {
			println("element: ${element}")
			buildData.argumentString = "${element}" + " "		
		}
	}
	buildData.label = "${buildData.build.imageName}:${buildData.build.version.prefix}.${date.format('MMdd')}.${config.buildNumber}"
	
	return buildData;
}