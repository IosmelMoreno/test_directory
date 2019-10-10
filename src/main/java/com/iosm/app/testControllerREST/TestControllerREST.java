package com.iosm.app.testControllerREST;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iosm.app.testServ.TestServInt;
import com.iosm.app.vo.UserContact;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/contact")
public class TestControllerREST {
	
	@Autowired
	TestServInt serv;
	
	@PostMapping
	public void createContact(@Valid @RequestBody UserContact contact) {
		getJWTToken();
	    serv.save(contact);
	}
	
	@PutMapping
	public void updateContact(@Valid @RequestBody UserContact contact) {
		getJWTToken();
	    serv.save(contact);
	}
	
	@GetMapping
    public void listUsers(Model mo) {
		getJWTToken();
		mo.addAttribute("listUser", serv.getListUsert());
    }
	
	@DeleteMapping("/{id}")
	public void updateContact(@RequestParam("id") Long id) {
		getJWTToken();
	    serv.delete(id);
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
