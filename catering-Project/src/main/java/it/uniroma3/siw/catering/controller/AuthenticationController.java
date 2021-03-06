package it.uniroma3.siw.catering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.catering.model.Credentials;
import it.uniroma3.siw.catering.service.CredentialsService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredentialsService credentialsService;
	
//	@Autowired
//	private UserValidator userValidator;
//	
//	@Autowired
//	private CredentialsValidator credentialsValidator;
	
//	@GetMapping(value = "/register") 
//	public String showRegisterForm (Model model) {
//		model.addAttribute("user", new User());
//		model.addAttribute("credentials", new Credentials());
//		return "registerUser";
//	}
	
	@GetMapping(value = "/login") 
	public String showLoginForm (Model model) {
		return "loginForm";
	}
	
	@GetMapping(value = "/logout") 
	public String logout(Model model) {
		return "index";
	}
	
    @GetMapping(value = "/default")
    public String defaultAfterLogin(Model model) {
    	
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "indexAmministratore";
        }
        return "index";	
    }
	
//    @PostMapping(value = { "/register" })
//    public String registerUser(@ModelAttribute("user") User user,
//                 BindingResult userBindingResult,
//                 @ModelAttribute("credentials") Credentials credentials,
//                 BindingResult credentialsBindingResult,
//                 Model model) {
//
//        // validate user and credentials fields
//        this.userValidator.validate(user, userBindingResult);
//        this.credentialsValidator.validate(credentials, credentialsBindingResult);
//
//        // if neither of them had invalid contents, store the User and the Credentials into the DB
//        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
//            // set the user and store the credentials;
//            // this also stores the User, thanks to Cascade.ALL policy
//            credentials.setUser(user);
//            credentialsService.saveCredentials(credentials);
//            
//            return "registrationSuccessful";
//        }
//        return "registerUser";
//    }
}
