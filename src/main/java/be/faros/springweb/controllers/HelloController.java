package be.faros.springweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    /*
        By default, Spring Boot provides an /error mapping that handles all errors in a sensible way, and it is registered as a "global" error page
        in the servlet container.

        If you want to display a custom HTML error page for a given status code, you can add a file to an /error folder. Error pages can either be
        static HTML (that is, added under any of the static resource folders) or be built by using templates. The name of the file should be the
        exact status code or a series mask. Ex. to map 404 to a static HTML file

        To map all 5xx errors use a file called: 5xx.html

        https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-web-applications.html#boot-features-error-handling
     */
}
