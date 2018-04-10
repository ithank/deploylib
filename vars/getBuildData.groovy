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
		for(def element in buildData.build.argument) {
			buildData.argumentString = "${element.key}=${element.value}" + " "		
		}
	}
	buildData.label = "${buildData.build.imageName}:${buildData.build.version.prefix}.${date.format('MMdd')}.${config.buildNumber}"
	
	return buildData;
}