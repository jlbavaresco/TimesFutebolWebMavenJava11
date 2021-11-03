/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.util;

import static br.edu.ifsul.util.Util.getMensagemErro;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


public class UtilRelatorios {

    public static void imprimeRelatorio(String relatorioNome, HashMap parametros, List lista) {
        try {
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            String path = scontext.getRealPath("/WEB-INF/relatorios/");
            parametros.put("SUBREPORT_DIR", path + File.separator);
            JasperPrint jasperPrint
                    = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/relatorios/" + 
                            relatorioNome + ".jasper"), parametros, dataSource);
            byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse res = (HttpServletResponse) 
                    FacesContext.getCurrentInstance().getExternalContext().getResponse();
            res.setContentType("application/pdf");
            int codigo = (int) (Math.random() * 1000);
            //Código abaixo gerar o relatório e disponibiliza diretamente na página   
            res.setHeader("Content-disposition", "inline;filename=relatorio_" + codigo + ".pdf");
            //Código abaixo gerar o relatório e disponibiliza para o cliente baixar ou salvar                
            //res.setHeader("Content-disposition", "attachment;filename=relatorio_" + codigo + ".pdf");
            res.getOutputStream().write(b);
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao imprimir relatório: " + getMensagemErro(e), "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            e.printStackTrace();
        }
    }
}
