package com.gamertx.config.securtity.filter;

import com.gamertx.domain.service.JwtService;
import com.gamertx.persistence.UsuarioRepository;
import com.gamertx.persistence.entity.users_view.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1. Obtener el Header del Jwt
        String authHeader = request.getHeader("Authorization"); // Bearer jwt

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        //2. Sacar el JWT
        String jwt = authHeader.split(" ")[1];

        //3. Obtener el subject/email desde el jwt
        String email = jwtService.extractEmail(jwt);

        //4. Setear un objeto de autenticacion dentro del security context

        Usuario usuario = usuarioRepository.findByUsername(email).get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                email,null,usuario.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        //5. Ejecutar el resto de filtros
        filterChain.doFilter(request, response);
    }
}
