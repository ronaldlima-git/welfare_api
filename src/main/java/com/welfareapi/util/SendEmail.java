package com.welfareapi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class SendEmail {
	
	
	/**
	 * Passos para configurar o uma conta do gmail para funcionar a autenticação
	 * acesse no email gmail, as configurações de encaminhamento POP/IMAP
	 * Ative Acesso IMAP
	 * Acesse a sua conta google: https://myaccount.google.com/?utm_source=OGB&tab=mk&utm_medium=app
	 * Menu a esquerda selecione > Segurança > Como fazer login no Google 
	 * Ative a verificação em duas etapas
	 * Quando ativado a configuração em duas etapas
	 * vai apresentar uma opção > "Senhas de app"
	 * Crie uma nova senha de app, como um app Outro e Dispositivo Outro também
	 * Salve a senha que será gerada em tela, caso não salve será necessária a criação de outra senha
	 * Com a senha salva acesse a application.properties nos "resources" deste projeto
	 * inclua o email e a senha gerada
	 * 
	 * altere o email na função setFrom("Mail") --Linha 43
	 * 
	 * E pronto! agora basta utilizar o email para envio!
	 * */
	
	private JavaMailSender javaMailSender;
	
	
	@Autowired
	public SendEmail (JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendEmailPerson (String mail, String subject, String message) throws MailException{
		SimpleMailMessage messageMail = new SimpleMailMessage();
		messageMail.setTo(mail);
		messageMail.setFrom("danieltoledo1004@gmail.com");
		messageMail.setSubject(subject);
		messageMail.setText(message);
		
		
		javaMailSender.send(messageMail);
	}
	
	public SendEmail(String subject, String message) {
		super();
	}
}


/*
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired private JavaMailSender mailSender;

    @RequestMapping(path = "/email-send", method = RequestMethod.GET)
    public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Hello from Spring Boot Application");
        message.setTo("wolmirgarbin@gmail.com");
        message.setFrom("wolmirgarbin@gmail.com");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }
}
 * 
 * 
 * 
 * **/
