package com.system.agb.SystemAnimalCoupB.service;

import com.system.agb.SystemAnimalCoupB.model.Persona;

public interface EmailService {
    boolean sendEmail(Persona p);

    boolean sendEmailWelcome(String correo, String nombres);

    boolean sendEmailNotificaUsernewAniaml(String correo_notifica, String tipo_animal, String raza_notifica, String name_emisor, String mame_receptor);

    boolean sendEmailActivationAccount(String correo);
}
