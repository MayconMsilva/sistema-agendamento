package com.sistema_empresa.demo.service;

import com.sistema_empresa.demo.config.JwtService;
import com.sistema_empresa.demo.domain.entity.Empresa;
import com.sistema_empresa.demo.domain.entity.Usuario;
import com.sistema_empresa.demo.dto.request.LoginRequest;
import com.sistema_empresa.demo.dto.request.RegisterRequest;
import com.sistema_empresa.demo.dto.response.AuthResponse;
import com.sistema_empresa.demo.repository.EmpresaRepository;
import com.sistema_empresa.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public AuthResponse register(RegisterRequest request) {

        if (usuarioRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuario.setRole(request.role());

        if (request.empresaId() != null) {
            Empresa empresa = empresaRepository.findById(request.empresaId())
                    .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada"));
            usuario.setEmpresa(empresa);
        }

        Usuario salvo = usuarioRepository.save(usuario);
        String token = jwtService.gerarToken(salvo.getId(), salvo.getRole().name());

        return new AuthResponse(token, salvo.getId(), salvo.getNome(), salvo.getRole().name());
    }

    public AuthResponse login(LoginRequest request) {

        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas"));

        if (!passwordEncoder.matches(request.senha(), usuario.getSenha())) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        String token = jwtService.gerarToken(usuario.getId(), usuario.getRole().name());

        return new AuthResponse(token, usuario.getId(), usuario.getNome(), usuario.getRole().name());
    }
}
