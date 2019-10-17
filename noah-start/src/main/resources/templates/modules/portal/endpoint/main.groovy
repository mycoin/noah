import org.springframework.aop.config.AdviceEntry

def execute(request, response) {
	response.setContentType("application/json");	
}