package arxa;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@ControllerAdvice
public class EmailController {

    /**
     * Global exception handling for this controller
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        return new ResponseEntity<>("Failed to send email!\n"
                +exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/email", method=RequestMethod.POST)
    public String sendEmail(@RequestParam String hostPassword, @RequestParam String host,
                            @RequestParam String destination, @RequestParam String subject,
                            @RequestParam String content) {
        sendSimpleMail(hostPassword,host,destination,subject,content);
        return "Email sent!";
    }

    /**
     * Sends an email using the SimpleJavaMail library
     * @param hostPassword the login password for host's email account
     * @param host Email address of the host
     * @param destination Email address to send
     * @param subject Email subject
     * @param content Email message
     */
    private void sendSimpleMail(String hostPassword, String host, String destination, String subject, String content) {
        Email email = EmailBuilder.startingBlank()
                .from("person1", host)
                .to("person2", destination)
                .withSubject(subject)
                .withPlainText(content)
                .buildEmail();
        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "nikarchakis@gmail.com", hostPassword)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer();
        mailer.sendMail(email);
    }

}
