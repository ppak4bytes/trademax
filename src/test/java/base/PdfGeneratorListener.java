package base;

import com.itextpdf.text.DocumentException;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import utils.PdfGenerator;

import java.io.FileNotFoundException;
import java.util.List;

public class PdfGeneratorListener extends BaseTest implements IReporter {

    private PdfGenerator pdfGenerator;

    @Override
    public void generateReport(List<XmlSuite> list, List<ISuite> list1, String s) {
        pdfGenerator = new PdfGenerator();
        try{
            pdfGenerator.createPdf(TEST_CONTAINER, PARAMS_CONTAINER);
        }
        catch (FileNotFoundException| DocumentException ex2){
            ex2.printStackTrace();
        }

        try {
            pdfGenerator.createImagePdf();
        }
        catch (Exception e){e.printStackTrace();}
    }
}
