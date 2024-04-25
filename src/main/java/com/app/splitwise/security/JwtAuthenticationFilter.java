package com.app.splitwise.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    private Logger logger= LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Override
    protected void doFilterInternal( HttpServletRequest request,HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String reqHeader = request.getHeader("Authorization");
        logger.info("Header : {}",reqHeader);
        String username=null;
        String token=null;
        if(reqHeader!=null && reqHeader.startsWith("Bearer")){

            token = reqHeader.substring(7);
            logger.info("Token :: {}",token);
            try{
                username=this.jwtHelper.getUsernameFromToken(token);
            }catch (IllegalArgumentException e){
                logger.info("Illegal Argument while fetching the username !");
                e.printStackTrace();
            }catch (ExpiredJwtException e){
                logger.info("Given token is Expired!");
                e.printStackTrace();
            }catch (MalformedJwtException e){
                logger.info("Some changes has done in token !!");
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            logger.info("Invalid Header value !");
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            logger.info("Username inside  : {}",username);
            //fetch user details from username
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            Boolean validateToken = null;
            try {
                validateToken = this.jwtHelper.validateToken(token,userDetails);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if(validateToken){
                UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                //   authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("Token validation success");
            }else{
                logger.info("Validation of token failed");
            }
        }
        filterChain.doFilter(request,response);
    }
}
