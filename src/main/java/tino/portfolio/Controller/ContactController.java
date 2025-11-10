package tino.portfolio.Controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tino.portfolio.dto.ContactRequest;
import tino.portfolio.dto.ContactService;

@CrossOrigin(origins = "http://localhost:5173")   // consente il frontend Vite
@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<Void> submit(@Valid @RequestBody ContactRequest request) {
        contactService.handle(request);
        return ResponseEntity.accepted().build();
    }
}