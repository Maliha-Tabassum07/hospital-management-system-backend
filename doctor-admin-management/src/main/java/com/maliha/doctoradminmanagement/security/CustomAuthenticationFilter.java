package com.maliha.doctoradminmanagement.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.maliha.doctoradminmanagement.SpringApplicationContext;
import com.maliha.doctoradminmanagement.constants.AppConstants;
import com.maliha.doctoradminmanagement.entity.DoctorEntity;
import com.maliha.doctoradminmanagement.model.DoctorDTO;
import com.maliha.doctoradminmanagement.model.DoctorLoginDTO;
import com.maliha.doctoradminmanagement.repository.DoctorRepository;
import com.maliha.doctoradminmanagement.service.DoctorService;
import com.maliha.doctoradminmanagement.utilities.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    @Autowired
    private DoctorRepository userRepository;
    @Autowired
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/doctor/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            DoctorLoginDTO creds = new ObjectMapper().readValue(request.getInputStream(), DoctorLoginDTO.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword())
            );
        } catch (IOException e) {
            log.info("Exception occurred at attemptAuthentication method: {}", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String user = ((User) authResult.getPrincipal()).getUsername();
        DoctorService doctorServiceRole = (DoctorService) SpringApplicationContext.getBean("doctorService");
        DoctorEntity doctorEntity = doctorServiceRole.readByEmail(user);
        List<String> roles = new ArrayList<>();
        roles.add(doctorEntity.getRole());
        String accessToken = JWTUtils.generateToken(user, roles);
        DoctorService doctorService = (DoctorService) SpringApplicationContext.getBean("doctorService");
        DoctorDTO doctorDto = doctorService.getDoctor(user);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("Message", "Successfully logged in");
        responseBody.put(AppConstants.HEADER_STRING, AppConstants.TOKEN_PREFIX + accessToken);
        responseBody.put("role",doctorDto.getRole());
        responseBody.put("id",doctorDto.getId());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(responseBody);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(jsonResponse);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        try {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            String errorMessage = "Authentication failed: ";
            if (failed instanceof BadCredentialsException) {
                errorMessage += "Invalid password.";
            } else if (failed instanceof UsernameNotFoundException) {
                errorMessage += "Invalid email.";
            } else {
                errorMessage += failed.getMessage();
            }
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"" + errorMessage + "\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}