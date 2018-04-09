def call() {
	def temp = new File('${env.workspace}/BuildVersion.txt')
	def temp1 = temp as Integer
	
	temp1 = temp1 + 1
	
	return temp1;
}