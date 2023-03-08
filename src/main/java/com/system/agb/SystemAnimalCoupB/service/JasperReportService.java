package com.system.agb.SystemAnimalCoupB.service;

import jakarta.servlet.http.HttpServletResponse;

public interface JasperReportService {
    public void generateReportUser(HttpServletResponse response);

    public void generateReporOftUserRequest(HttpServletResponse response, Integer id_persona, String name);

    public void generateReporRazaOfTipoAnimal(HttpServletResponse response, Integer id_tipo, String name);

    public void generateReporRazaOfBestfilter3(HttpServletResponse response, Integer id_raza, String name);

    public void generateReporRazaOfBestfilter5(HttpServletResponse response, Integer id_raza, String name);

    public void generateReporRazaOfBestfilter10(HttpServletResponse response, Integer id_raza, String name);
}
