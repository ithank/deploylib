def call(body) {
	// transfer parameters
	def config = [:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()
	
	def data = readYaml file: 'build.yml'
    def date = new Date()
	
	return "${data.build.imageName}:${data.build.version.prefix}.${date.format('MMdd')}.${config.buildNumber}";
}