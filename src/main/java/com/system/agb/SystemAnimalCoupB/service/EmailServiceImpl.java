package com.system.agb.SystemAnimalCoupB.service;

import com.system.agb.SystemAnimalCoupB.model.Cuenta;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import com.system.agb.SystemAnimalCoupB.repository.CuentaRepository;
import com.system.agb.SystemAnimalCoupB.repository.PersonaRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    private PersonaRepository personarepository;
    @Autowired
    private CuentaRepository cuentarepository;


    public boolean sendEmail(Persona p){
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            List<Cuenta> c= cuentarepository.findByPersona(p);
            helper.setFrom("soporteanimalscouple@gmail.com");
            helper.setTo(p.getCorreo());
            helper.setSubject("RECUPERACION DE CONTRASEÑA");
            helper.setText("<html><body style='background-color: rgb(240, 248, 255); margin-left: 10px; margin-right: 10px; text-align: center; border-radius: 20px; padding: 20px;' >" +
                    "<p style=' color: black ; margin-left: 5px; text-align: center;'>Hemos recibido una solicitud de recuperación.</p>" +
                    "<p><font size= 5 ;  style=' color: black ;margin-left: 5px; text-align: center;'>"+"<b> Su contraseña es: " +c.get(0).getContrasenia() + "</b></font></p>" +
                    "<p style=' color: black ; margin-left: 5px; text-align: center;'>Esperamos que encuentres lo que estás buscando y si tienes alguna duda no dudes en contactarnos.</p>" +
                    "<a href='http://localhost:4200/home'><img src='https://i.ibb.co/bzP2PqY/logo.png'  width='200px' height='150px'></a> "+
                    "</body></html>", true);


            javaMailSender.send(message);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean sendEmailWelcome(String correo, String nombres) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("soporteanimalscouple@gmail.com");
            helper.setTo(correo);
            helper.setSubject("WELCOME TO ANIMALSCOUPLE");
            helper.setText("<html><body style='background-color: rgb(240, 248, 255); margin-left: 10px; margin-right: 10px; text-align: center; border-radius: 20px; padding: 20px;' >" +
                    "<h1 style='color: blue; text-align: center;'>" + "Bienvenido " + nombres + "</h1>" +
                    "<p style='color: black ; font-family: cursive;  margin-left: 5px; text-align: center;'><b>Te damos la bienvenida a Animals Coup un sistema de búsquedas de animales</b>.</p>" +
                    "<p style='color: black ; margin-left: 5px; text-align: center;'>Esperamos que encuentres lo que estás buscando y si tienes alguna duda no dudes en contactarnos.</p>" +
                    "<a href='http://localhost:4200/home'><img src='https://i.ibb.co/bzP2PqY/logo.png'  width='200px' height='150px'></a> "+
                    "</body></html>", true);


            javaMailSender.send(message);
            return true;

        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean sendEmailNotificaUsernewAniaml(String correo_notifica, String tipo_animal, String raza_notifica, String name_emisor, String mame_receptor) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("soporteanimalscouple@gmail.com");
            helper.setTo(correo_notifica);
            helper.setSubject("PREFERENCIAS DE TUS ANIMALES");
            helper.setText("<html><body style='background-color: rgb(240, 248, 255); margin-left: 10px; margin-right: 10px; text-align: center; border-radius: 20px; padding: 20px;' >" +
                    "<h1 style='color: blue; text-align: center;'>" + "Hola " + mame_receptor + "</h1>" +
                    "<p style='color: black ; margin-left: 5px; text-align: center;'> <b>Te notificamos que " + name_emisor + " acaba de publicar la raza " + raza_notifica + " perteneciente a " + tipo_animal + " perteneciente a tus preferencias.</b></p>" +
                    "<p style='color: black ; margin-left: 5px; text-align: center;'>Esperamos que encuentres lo que estás buscando y si tienes alguna duda no dudes en contactarnos.</p>" +
                    "<a href='http://localhost:4200/home'><img src='https://i.ibb.co/bzP2PqY/logo.png'  width='200px' height='150px'></a> "+
                    "</body></html>", true);


            javaMailSender.send(message);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean sendEmailActivationAccount(String correo) {
        //  Optional<Persona> p = personarepository.findById(id_persona);
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("soporteanimalscouple@gmail.com");
            helper.setTo(correo);
            helper.setSubject("ACTIVACION DE CUENTA");
            helper.setText("<html><body style='background-color: rgb(240, 248, 255); margin-left: 10px; margin-right: 10px; text-align: center; border-radius: 20px; padding: 20px;' >" +
                    "<p style=' color: black ; margin-left: 5px; text-align: center;'>Hemos recibido una solicitud de Activación.</p>" +
                    "<p><font size= 5 ;  style=' color: black ;margin-left: 5px; text-align: center;'>"+"<b>Su cuenta a sido activada satisfactoriamente. </b></font></p>" +
                    "<p style=' color: black ; margin-left: 5px; text-align: center;'>Esperamos que encuentres lo que estás buscando y si tienes alguna duda no dudes en contactarnos.</p>" +
                    "<a href='http://localhost:4200/login'><img src='https://i.ibb.co/bzP2PqY/logo.png'  width='200px' height='150px'></a> "+
                    "</body></html>", true);

            javaMailSender.send(message);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
