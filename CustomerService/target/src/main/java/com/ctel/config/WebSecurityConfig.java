package com.ctel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.ctel.filter.SecurityFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private InvalidUserAuthEntryPoint authenticationEntryPoint;

	@Autowired
	private SecurityFilter securityFilter;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

//	authenticate() method ki db lo vunna values ni loginRequest lo vunna values tho elaa check cheskovaaalo theliyadhu	
//	appn db tho 3 ways lo connect avudhi 1) in memory(H2DB) 2) JDBC 3)UserDetailsService 
//	kaabatti yee mooditi lo denini use cheskovalo ani maname raasthamu
//	ee particular appn lo manam UserDetailsService ni use chesukuntaamu
//	kinda method lo UserDetailsService lo vunna oke okka method loadUserByUsername(). 
//	loadUserByUsername() use chesi "LoginRequest" lo vunna Username tho db lo ade username vundo ledo check chestham.
//	okavelaa lekapothe username/password "notFound exception"(no such user exists).
//	okavelaa vunte  passwordEncoder tho password kooda match authundo/ledo check chesthaaam
//	username/password match ayithe login successful kaabatti
//	aa Customer yokka roles and authorities kaavaali.
//	kaabatti db nundi Customer/user object ni get cheskuntaamu.
//	aa customer(UserDetails) object ni securityContext(securityContext means customerService loni loadUserByUsername method lo vunna "org.springframework.security.core.userdetails.User") lo unna Authentication object lo save chesthaaam

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/*").allowedOrigins("*").allowedMethods("GET", "POST", "OPTIONS", "PUT")
				.allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
						"Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
				
//				.allowCredentials(true)
//				.maxAge(3600)
		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// disabling Cross-Origin Resource Server
		// http.cors().disable();
		http.cors();
		// disabling Cross-Site Request Forgery
		
		http.csrf().disable().
		// no authentication required
				authorizeRequests().
				
				antMatchers("/customerRegister", "/login", "/updateDetails/{cid}", "/viewCustDetails/{cid}", "/viewAllCusts", "/deleteCust/{cid}").permitAll()

				// authority required

//				.antMatchers("/updateCust/{cid}", "/viewCust/{cid}").access("hasAuthority('admin') OR @userSecurity.hasUserId(authentication,#cid)")

//				.antMatchers("/saveInv", "/deleteInvItem/{iid}", "/updateInvItem/{iid}").hasAnyAuthority("seller", "admin")
//				.antMatchers("/viewInvItems","/viewInvItems/{iid}").hasAnyAuthority("customer","seller", "admin")
//				
//				.antMatchers("/viewProds", "/viewProd/{pid}").hasAnyAuthority("seller", "customer", "admin")
//				
//				.antMatchers( "/viewSlr/{sid}", "/updateSlr/{sid}").hasAnyAuthority("seller", "admin")
				
				.antMatchers("/saveOrd/{cid}","/viewOrd/{cid}", "/orderInvoice/pdf/{oid}").hasAnyAuthority("customer", "admin")
//				
//				.antMatchers("/saveOrd/{cid}","/viewOrd/{cid}", "/orderInvoice/pdf/{oid}").hasAnyAuthority("customer", "admin")
//				.antMatchers("/saveOrd/{cid}","/viewOrd/{cid}").access("@userSecurity.hasUserId(authentication,#cid)")

				
//			authenticationEntryPoint is used to handle the exceptionHandling[yedaina ecxception vasthe application lo error choopinchakundaaa aa error msg ni display cheyyadaaniki use chesthaamu].
				.anyRequest().hasAuthority("admin").and()
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and().
				sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				// filter from 2nd request onwards
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
