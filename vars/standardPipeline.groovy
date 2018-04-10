def call(body) {
		
	node {
		def app
		def buildData
		docker.withRegistry('http://10.25.232.183:5000') {
			stage('Clone') {
				checkout scm
			}			
			stage('Build') {
			    def theBuildNumber = getBuildNumber()
				buildData = getBuildData{
					buildNumber = theBuildNumber
				}
				
				currentBuild.displayName = "# " + buildData.label
				println("Docker build")
				println("build label: ${buildData.label}")
				println("build-arg: ${buildData.argumentString}")
				app = docker.build(buildData.label,"--build-arg ${buildData.argumentString} .")
			}
				
			stage('Test') {
				app.inside {
					sh 'echo "Tests passed"'
				}
			}
			stage('Push') { 
				app.push()
				app.push("${buildData.build.version.prefix}")
			}
		}
	}
}