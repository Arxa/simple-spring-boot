package arxa;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class EmailController {

    @RequestMapping(value="/email", method=RequestMethod.POST)
    public String sendEmail(@RequestParam String hostPassword, @RequestParam String host,
                            @RequestParam String destination, @RequestParam String subject,
                            @RequestParam String content) {
        sendSimpleMail(hostPassword,host,destination,subject,content);
        return "Email sent!";
    }

    private void sendSimpleMail(String hostPassword, String host, String destination, String subject, String content) {
        Email email = EmailBuilder.startingBlank()
                .from("nikiforos1", host)
                .to("nikiforos2", destination)
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
