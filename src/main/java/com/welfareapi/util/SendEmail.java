package com.welfareapi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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


	@Service // Indica que esta classe contém lógica de serviço (negócio).
	public class SendEmail {

		@Autowired // Injeta o motor de envio de e-mails do Spring.
		private JavaMailSender javaMailSender;

		@Value("${spring.mail.username}") // Pega o e-mail configurado no seu application.properties automaticamente.
		private String emailRemetente;

		public void send(String mail, String subject, String message) throws MailException {
			SimpleMailMessage messageMail = new SimpleMailMessage();

			messageMail.setTo(mail);           // E-mail do destinatário.
			messageMail.setFrom(emailRemetente); // Usa o seu e-mail configurado como remetente.
			messageMail.setSubject(subject);    // Assunto da mensagem.
			messageMail.setText(message);       // Conteúdo do texto.

			javaMailSender.send(messageMail);   // Executa o envio real.
		}
	}