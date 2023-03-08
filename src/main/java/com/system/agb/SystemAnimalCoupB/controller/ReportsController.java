package com.system.agb.SystemAnimalCoupB.controller;

import com.system.agb.SystemAnimalCoupB.service.JasperReportServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class ReportsController {
    @Autowired
    private JasperReportServiceImpl jasperReportService;

    @GetMapping("/sistemascouple/reports/download-report")
    public void downloadReport(HttpServletResponse response) throws Exception {
        jasperReportService.generateReportNA(response);
    }

    @GetMapping("/sistemascouple/reports/users/download-report-user")
    public void generateReportUser(HttpServletResponse response) {
        try {
            jasperReportService.generateReportUser(response);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    @GetMapping("/sistemascouple/reports/users/request/download-report-user/{id_persona}/{name}")
    public void generateReporOftUserRequest(HttpServletResponse response, @PathVariable("id_persona") Integer id_persona, @PathVariable("name") String name) {
        try {
            jasperReportService.generateReporOftUserRequest(response, id_persona, name);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    @GetMapping("/sistemascouple/reports/users/request/raza/download-report-animal/{id_tipo}/{name}")
    public void generateReporOftRazaDependenceTipo(HttpServletResponse response, @PathVariable("id_tipo") Integer id_tipo, @PathVariable("name") String name) {
        try {
            jasperReportService.generateReporRazaOfTipoAnimal(response, id_tipo, name);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    @GetMapping("/sistemascouple/reports/users/request/raza/best/download-report-animal/{id_r}/{limit}/{name}")
    public void generateReporOftRazaOfBest(HttpServletResponse response, @PathVariable("id_r") Integer id_r, @PathVariable("limit") Integer limit, @PathVariable("name") String name) {
        try {
            if(limit == 3){
                jasperReportService.generateReporRazaOfBestfilter3(response, id_r, name);
            } else if (limit == 5) {
                jasperReportService.generateReporRazaOfBestfilter5(response, id_r, name);
            }else {
                jasperReportService.generateReporRazaOfBestfilter10(response, id_r, name);
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
