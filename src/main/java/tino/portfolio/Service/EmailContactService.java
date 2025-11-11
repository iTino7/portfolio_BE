package tino.portfolio.Service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tino.portfolio.dto.ContactRequest;
import tino.portfolio.dto.ContactService;

@Service
public class EmailContactService implements ContactService {

    private final JavaMailSender mailSender;

    public EmailContactService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void handle(ContactRequest request) {
        
        mailSender.send(mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setTo("tua-destinazione@example.com");
            helper.setSubject("Nuovo messaggio dal portfolio");
            helper.setText(buildBody(request), true);
        });
    }

    private String buildBody(ContactRequest request) {
        return """
                    <h2>Nuovo messaggio</h2>
                    <p><strong>Nome:</strong> %s</p>
                    <p><strong>Email:</strong> %s</p>
                    <p><strong>Telefono:</strong> %s</p>
                    <p><strong>Messaggio:</strong><br/>%s</p>
                """.formatted(
                request.name(),
                request.email(),
                request.phone() == null ? "N/D" : request.phone(),
                request.message().replace("\n", "<br/>")
        );
    }
}