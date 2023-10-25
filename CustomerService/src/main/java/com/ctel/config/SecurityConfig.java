//package com.ctel.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
////import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//import com.ctel.filter.SecurityFilter;
//
//@Configuration
//@EnableMethodSecurity
//// (securedEnabled = true,
//// jsr250Enabled = true,
//// prePostEnabled = true) // by default
//public class SecurityConfig { // extends WebSecurityConfigurerAdapter {
//  @Autowired
//  UserDetailsService userDetailsService;
//
//  @Autowired
//  private InvalidUserAuthEntryPoint unauthorizedHandler;
//  
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//	
//	@Autowired
//	private SecurityFilter securityfilter;
//
//
//  @Bean
//  public SecurityFilter authenticationJwtTokenFilter() {
//    return new SecurityFilter();
//  }
//
//  @Bean
//  public DaoAuthenticationProvider authenticationProvider() {
//      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//       
//      authProvider.setUserDetailsService(userDetailsService);
//      authProvider.setPasswordEncoder(passwordEncoder);
//   
//      return authProvider;
//  }
//
//  @Bean
//  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//    return authConfig.getAuthenticationManager();
//  }
//  
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	  http.cors();
//		// disabling Cross-Site Request Forgery
//
//		http.authenticationProvider(authenticationProvider());
//		
//		http.csrf().disable().
//		// no authentication required
//				authorizeRequests().
//
//				requestMatchers("/custmerRegister", "/login", "/updateCust/{cid}", "/viewCust/{cid}", "/viewCusts",
//						"/deleteCust/{cid}")
//				.permitAll()
//
//				// authority required
//
////			.antMatchers("/updateCust/{cid}", "/viewCust/{cid}").access("hasAuthority('admin') OR @userSecurity.hasUserId(authentication,#cid)")
//
////			.antMatchers("/saveInv", "/deleteInvItem/{iid}", "/updateInvItem/{iid}").hasAnyAuthority("seller", "admin")
////			.antMatchers("/viewInvItems","/viewInvItems/{iid}").hasAnyAuthority("customer","seller", "admin")
////			
////			.antMatchers("/viewProds", "/viewProd/{pid}").hasAnyAuthority("seller", "customer", "admin")
////			
////			.antMatchers( "/viewSlr/{sid}", "/updateSlr/{sid}").hasAnyAuthority("seller", "admin")
////			
////			.antMatchers("/saveOrd/{cid}","/viewOrd/{cid}", "/orderInvoice/pdf/{oid}").hasAnyAuthority("customer", "admin")
////			.antMatchers("/saveOrd/{cid}","/viewOrd/{cid}").access("@userSecurity.hasUserId(authentication,#cid)")
//
////		authenticationEntryPoint is used to handle the exceptionHandling[yedaina ecxception vasthe application lo error choopinchakundaaa aa error msg ni display cheyyadaaniki use chesthaamu].
//				.anyRequest().hasAuthority("admin").and().exceptionHandling()
//				.authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//			    
//				// filter from 2nd request onwards
//				.addFilterBefore(securityfilter, UsernamePasswordAuthenticationFilter.class);
//	    return http.build();
//  }
//}