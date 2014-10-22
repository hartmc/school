school
======

Discovery education interview problem - 

This sample web application uses MVC:
	model: com.lindauer.model package
		simple classes representing student, teacher, and classroom
	controller: com.lindauer.controller package
		servlets respond to web requests, lookup models, and 
		set state for the view to use
	view: web directory
		JSP pages control display of web pages using stylesheets 
		and dynamic attributes set by controller(s)

The model is made persistent by various DAOs, which cache database query results for scalability and performance.  
