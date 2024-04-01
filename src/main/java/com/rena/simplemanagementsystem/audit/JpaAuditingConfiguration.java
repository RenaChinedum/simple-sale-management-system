package com.rena.simplemanagementsystem.audit;

import com.rena.simplemanagementsystem.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditingConfiguration {


    @Bean
    public AuditorAware<String> auditorAware(){
        return new JpaAuditingImpl();
    }

    private static class JpaAuditingImpl implements AuditorAware<String> {
        @Override
        public Optional<String> getCurrentAuditor() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User userLoggedIn = (User) authentication.getPrincipal();
                return Optional.ofNullable(userLoggedIn.getFirstName() + " " + userLoggedIn.getLastName());
            } else {
                return Optional.empty();
            }
        }
    }
}
