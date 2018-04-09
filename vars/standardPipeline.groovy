def call(body) {
		
	node {
		def app
		docker.withRegistry('http://10.25.232.183:5000') {
			stage('Clone') {
				checkout scm
			}			
			stage('Build') {
			    def theBuildNumber = getBuildNumber()
				def buildLabel = getBuildLabel{
					buildNumber = theBuildNumber
				}
				currentBuild.displayName = "# " + buildLabel
				app = docker.build(buildLabel)
			}
				
			stage('Test') {
				app.inside {
					sh 'echo "Tests passed"'
				}
			}
			stage('Push') { 
				app.push()
			}
		}
	}
}