/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newFolder;

/**
 *
 * @author Onward
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
 
 
public class ReportGenerator {
    public static void main(String[] args) throws Exception {
        List list = new ArrayList();
        list.add("நித்ய");
        
 
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
 
        FileOutputStream fos = null;
        try {
            byte[] bytes=new ReportGenerator().generateJasperReportPDF("studentReport", outputStream, list);
            if(bytes.length>1){
                File someFile = new File("D:/Temp/testReport.pdf");
                fos = new FileOutputStream(someFile);
                fos.write(bytes);
                fos.flush();
                fos.close();
                System.out.println("<<<<<<<<<<<<Report Created>>>>>>>>");
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
 
    public byte[]  generateJasperReportPDF(String jasperReportName, ByteArrayOutputStream outputStream, List reportList) {
        JRPdfExporter exporter = new JRPdfExporter();
        try {
            String reportLocation = "D://Temp//"+jasperReportName+".jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportLocation);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, new JRBeanCollectionDataSource(reportList));
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);   
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
            exporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in generate Report..."+e);
        } finally {
        }
        return outputStream.toByteArray();
    }
}
