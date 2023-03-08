package com.system.agb.SystemAnimalCoupB.service;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperReportServiceImpl implements JasperReportService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void generateReportNA(HttpServletResponse response) throws Exception {

        InputStream reportStream = this.getClass().getResourceAsStream("/reports/personas.jasper");

        // Crea un mapa de parámetros para pasar a la plantilla del informe
        Map<String, Object> params = new HashMap<>();
        // params.put("param1", "valor1");
        //params.put("param2", "valor2");

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, params, jdbcTemplate.getDataSource().getConnection());
        byte[] reportContent = JasperExportManager.exportReportToPdf(jasperPrint);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
        response.setContentLength(reportContent.length);

        OutputStream outStream = response.getOutputStream();
        outStream.write(reportContent);
        outStream.flush();
        outStream.close();
    }

    @Override
    public void generateReportUser(HttpServletResponse response){

        try {
            InputStream reportStream = this.getClass().getResourceAsStream("/reports/ReporteUsuarios.jasper");

            // Crea un mapa de parámetros para pasar a la plantilla del informe
            Map<String, Object> params = new HashMap<>();
            params.put("logo", "logo.png");
            params.put("ck", "check.png");

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, params, jdbcTemplate.getDataSource().getConnection());
            byte[] reportContent = JasperExportManager.exportReportToPdf(jasperPrint);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
            response.setContentLength(reportContent.length);

            OutputStream outStream = response.getOutputStream();
            outStream.write(reportContent);
            outStream.flush();
            outStream.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void generateReporOftUserRequest(HttpServletResponse response, Integer id_persona, String name) {
        try {
            InputStream reportStream = this.getClass().getResourceAsStream("/reports/reportanimalsofUser.jasper");

            // Crea un mapa de parámetros para pasar a la plantilla del informe
            Map<String, Object> params = new HashMap<>();
            params.put("logo", "logo.png");
            params.put("ck", "check.png");
            params.put("id_us", id_persona);
            params.put("name", name);

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, params, jdbcTemplate.getDataSource().getConnection());
            byte[] reportContent = JasperExportManager.exportReportToPdf(jasperPrint);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
            response.setContentLength(reportContent.length);

            OutputStream outStream = response.getOutputStream();
            outStream.write(reportContent);
            outStream.flush();
            outStream.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void generateReporRazaOfTipoAnimal(HttpServletResponse response, Integer id_tipo, String name) {
        try {
            InputStream reportStream = this.getClass().getResourceAsStream("/reports/reportrazaofTipo.jasper");

            // Crea un mapa de parámetros para pasar a la plantilla del informe
            Map<String, Object> params = new HashMap<>();
            params.put("logo", "logo.png");
            params.put("ck", "check.png");
            params.put("id_t", id_tipo);
            params.put("name", name);

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, params, jdbcTemplate.getDataSource().getConnection());
            byte[] reportContent = JasperExportManager.exportReportToPdf(jasperPrint);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
            response.setContentLength(reportContent.length);

            OutputStream outStream = response.getOutputStream();
            outStream.write(reportContent);
            outStream.flush();
            outStream.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void generateReporRazaOfBestfilter3(HttpServletResponse response, Integer id_raza, String name) {
        try {
            InputStream reportStream = this.getClass().getResourceAsStream("/reports/reportanimalThebest3.jasper");

            // Crea un mapa de parámetros para pasar a la plantilla del informe
            Map<String, Object> params = new HashMap<>();
            params.put("logo", "logo.png");
            params.put("ck", "check.png");
            params.put("id_r", id_raza);
            params.put("name", name);

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, params, jdbcTemplate.getDataSource().getConnection());
            byte[] reportContent = JasperExportManager.exportReportToPdf(jasperPrint);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
            response.setContentLength(reportContent.length);

            OutputStream outStream = response.getOutputStream();
            outStream.write(reportContent);
            outStream.flush();
            outStream.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void generateReporRazaOfBestfilter5(HttpServletResponse response, Integer id_raza, String name) {
        try {
            InputStream reportStream = this.getClass().getResourceAsStream("/reports/reportanimalThebest5.jasper");

            // Crea un mapa de parámetros para pasar a la plantilla del informe
            Map<String, Object> params = new HashMap<>();
            params.put("logo", "logo.png");
            params.put("ck", "check.png");
            params.put("id_r", id_raza);
            params.put("name", name);

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, params, jdbcTemplate.getDataSource().getConnection());
            byte[] reportContent = JasperExportManager.exportReportToPdf(jasperPrint);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
            response.setContentLength(reportContent.length);

            OutputStream outStream = response.getOutputStream();
            outStream.write(reportContent);
            outStream.flush();
            outStream.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void generateReporRazaOfBestfilter10(HttpServletResponse response, Integer id_raza, String name) {
        try {
            InputStream reportStream = this.getClass().getResourceAsStream("/reports/reportanimalThebest1.jasper");

            // Crea un mapa de parámetros para pasar a la plantilla del informe
            Map<String, Object> params = new HashMap<>();
            params.put("logo", "logo.png");
            params.put("ck", "check.png");
            params.put("id_r", id_raza);
            params.put("name", name);

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, params, jdbcTemplate.getDataSource().getConnection());
            byte[] reportContent = JasperExportManager.exportReportToPdf(jasperPrint);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
            response.setContentLength(reportContent.length);

            OutputStream outStream = response.getOutputStream();
            outStream.write(reportContent);
            outStream.flush();
            outStream.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
