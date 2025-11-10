package tino.portfolio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContactRequest(
        @NotBlank(message = "Il nome è obbligatorio")
        @Size(min = 2, max = 80, message = "Il nome deve essere tra 2 e 80 caratteri")
        String name,

        @NotBlank(message = "L'email è obbligatoria")
        @Email(message = "Email non valida")
        String email,

        @Size(max = 40, message = "Il numero di telefono è troppo lungo")
        String phone,

        @NotBlank(message = "Il messaggio è obbligatorio")
        @Size(min = 10, max = 2000, message = "Il messaggio deve essere tra 10 e 2000 caratteri")
        String message
) {
}