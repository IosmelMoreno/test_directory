package com.iosm.app.testMVCController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.iosm.app.testServ.TestServInt;
import com.iosm.app.vo.UserContact;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
public class TestControllerMVC {
	
	@Autowired
	TestServInt serv;
		
	@GetMapping(value="/list")
    public String listUsers(Model mo) {
		getJWTToken();
		mo.addAttribute("listUser", serv.getListUsert());
        return "list";
    }
	
	@PostMapping("/insert")
	public String createContact(@Valid @RequestBody UserContact contact) {
		getJWTToken();
	    serv.save(contact);
	    return "Save";
	}
	 
	@PutMapping("/update")
	public String updateContact(@Valid @RequestBody UserContact contact) {
		getJWTToken();
	    serv.save(contact);
	    return "update";
	}
	
	@DeleteMapping("/delete/{id}")
	public String updateContact(@RequestParam("id") Long id) {
		getJWTToken();
	    serv.delete(id);
	    return "update";
	}
	
	
	private String getJWTToken() {
		String secretKey = "mySecretKey";
		List<GrantedAuthority>  grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(init())
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
	
	@PostConstruct
	public String  init() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    String usuario = userDetail.getUsername();
	    return usuario;
	}

}
