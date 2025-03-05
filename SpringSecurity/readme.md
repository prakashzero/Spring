# Spring Security

## What is Spring Security?

Spring Security is a powerful framework that provides authentication, authorization, and other security features for Java applications. It is widely used to secure web applications and REST APIs.

## Why is Spring Security Required?

Security is essential in any application to protect sensitive data and resources from unauthorized access. Spring Security helps in:

- Authentication (verifying user identity)
- Authorization (granting or denying access)
- CSRF Protection
- Session Management
- Password Encoding

## How Does Spring Security Work?

Spring Security works as a filter chain that intercepts incoming HTTP requests. It checks each request using multiple filters to determine whether the request should be allowed or denied.

## Default Login Form

When Spring Security is added to the application, it automatically provides a default login form without any additional code.

### Code Example

```java
@SpringBootApplication
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
```

### Default Username and Password

By default, the username is `user`, and the password is auto-generated and printed in the console:

```plaintext
Using generated security password: abc123xyz
```

## Custom Username and Password

To set your own username and password, add these lines in `application.properties`:

```properties
spring.security.user.name=telusko
spring.security.user.password=1234
```

## Authentication with Postman

If you try to access a secured endpoint without proper credentials, Postman will return a `401 Unauthorized` error. Set the username and password under the Authorization tab in Postman.

## Cross-Site Request Forgery (CSRF)

CSRF is an attack where a malicious website tricks a user into performing actions on another website where the user is authenticated.

Spring Security protects against CSRF by default. However, for POST requests, you must pass the CSRF token.

### CSRF Token Generation

```java
@GetMapping("/csrf-token")
public CsrfToken getCsrfToken(HttpServletRequest request) {
    return (CsrfToken) request.getAttribute("_csrf");
}
```

## Disabling CSRF Protection

For stateless REST APIs, CSRF protection can be disabled.

```properties
server.servlet.session.cookie.same-site-strict
```

## Stateless vs Stateful APIs

- **Stateful APIs:** Store session IDs and require CSRF tokens.
- **Stateless APIs:** Do not store session IDs and do not require CSRF tokens.

### Disabling CSRF in Stateless APIs

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
```

## Authentication Provider

Spring Security uses `AuthenticationProvider` to verify user credentials.

```java
@Bean
public AuthenticationProvider authProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(new BCryptPasswordEncoder());
    return provider;
}
```

## Password Encoding

Passwords should always be stored in an encoded format using `BCryptPasswordEncoder`.

### Register User with Encoded Password

```java
@Service
public class UserService {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
```

## Conclusion

Spring Security provides a comprehensive solution for securing Java applications. It helps in protecting sensitive data, verifying user identities, and preventing attacks like CSRF. By using proper configurations and password encoders, you can make your applications more secure.
 
