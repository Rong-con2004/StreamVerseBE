package com.example.streamversebe.Service.JWT;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);

        try {
            // Trích xuất email từ JWT
            String userEmail = jwtService.extractUsername(jwt);

            // Kiểm tra nếu email chưa được authenticate
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

                // Xác thực JWT
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }catch (ExpiredJwtException e) {
            throw new JwtException(String.format("JWT token has expired: %s", e.getMessage()));
        }
        catch (UnsupportedJwtException e) {
            throw new JwtException(String.format("JWT token is unsupported: %s", e.getMessage()));

        } catch (IllegalArgumentException e) {
            throw new JwtException(String.format("JWT token is invalid: %s", e.getMessage()));
        } catch (Exception e) {
            throw new JwtException(String.format("Unexpected error during JWT processing: %s", e.getMessage()));
        }

        // Tiếp tục chuỗi filter
        filterChain.doFilter(request, response);
    }

    public class JwtException extends RuntimeException {
        public JwtException(String message) {
            super(message);
        }
    }

}
