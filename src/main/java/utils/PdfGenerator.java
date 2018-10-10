package utils;

import com.itextpdf.text.*;
import com.itextpdf.text.List;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public final class PdfGenerator {

    private Chunk headerText;
    private java.util.List<String> imageFiles;
    private com.itextpdf.text.List methodsList;
    private final Paragraph spacing = new Paragraph();

    private static final Font HEADER = new Font(Font.FontFamily.COURIER, 16, Font.BOLD);
    private static final LineSeparator LINE_SEPARATOR = new LineSeparator();


    private static final java.util.List<Map.Entry<String, java.util.List<String>>> TRADEMAX = new ArrayList<>();
    private static final java.util.List<Map.Entry<String, java.util.List<String>>> WEGOT = new ArrayList<>();
    private static final java.util.List<Map.Entry<String, java.util.List<String>>> CHILLI = new ArrayList<>();
    private static final java.util.List<Map.Entry<String, java.util.List<String>>> FURNITUREBOX = new ArrayList<>();
    private static final java.util.List<Map.Entry<String, java.util.List<String>>> KODIN = new ArrayList<>();
    private static final Map<String, String> TRADEMAX_P = new HashMap<>();
    private static final Map<String, String> WEGOT_P = new HashMap<>();
    private static final Map<String, String> CHILLI_P = new HashMap<>();
    private static final Map<String, String> FURNITUREBOX_P = new HashMap<>();
    private static final Map<String, String> KODIN_P = new HashMap<>();


    private static final String DEST = "target/test_text.pdf";
    private static final String SCREENSHOT_FILEPATH = "target/test_images.pdf";
    private static final String DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());

    public void createPdf(java.util.List<Map<String, java.util.List<String>>> testContainer,
                          Map<String, Map<String, String>> paramsContainer) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();

        headerText = new Chunk("Test run : " + DATE, HEADER);
        document.add(headerText);
        spacing.setSpacingAfter(25f);
        document.add(spacing);
        document.add(LINE_SEPARATOR);

        extractTests(testContainer);
        extractParams(paramsContainer);
        composeSuiteSection(document, WEGOT, WEGOT_P);
        composeSuiteSection(document, CHILLI, CHILLI_P);
        composeSuiteSection(document, TRADEMAX, TRADEMAX_P);
        composeSuiteSection(document, FURNITUREBOX, FURNITUREBOX_P);
        composeSuiteSection(document, KODIN, KODIN_P);
        document.close();

    }

    private static void extractTests(java.util.List<Map<String, java.util.List<String>>> testContainer) {
        for (Map<String, java.util.List<String>> test : testContainer) {
            for (Map.Entry<String, java.util.List<String>> pair : test.entrySet()) {
                if (pair.getKey().contains("WeGot")) WEGOT.add(pair);
                else if (pair.getKey().contains("TradeMax")) TRADEMAX.add(pair);
                else if (pair.getKey().contains("Chilli")) CHILLI.add(pair);
                else if (pair.getKey().contains("FurnitureBox")) FURNITUREBOX.add(pair);
                else KODIN.add(pair);
            }
        }
    }

    private static void extractParams(Map<String, Map<String, String>> paramsContainer) {
        Iterator<Map.Entry<String, Map<String, String>>> iterator = paramsContainer.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Map<String, String>> pair = iterator.next();
            if (pair.getKey().contains("WeGot")) WEGOT_P.putAll(pair.getValue());
            else if (pair.getKey().contains("TradeMax")) TRADEMAX_P.putAll(pair.getValue());
            else if (pair.getKey().contains("Chilli")) CHILLI_P.putAll(pair.getValue());
            else if (pair.getKey().contains("FurnitureBox")) FURNITUREBOX_P.putAll(pair.getValue());
            else KODIN_P.putAll(pair.getValue());
            System.out.println(pair.toString());
        }
    }

    private void composeSuiteSection(Document document, java.util.List<Map.Entry<String, java.util.List<String>>> container,
                                     Map<String, String> parameters) throws DocumentException {
        final String channelName = Optional.ofNullable(parameters.get("url")).orElseGet(() -> "No data provided");
        final String itemName = Optional.ofNullable(parameters.get("item")).orElseGet(() -> "No data provided");
        final String address = Optional.ofNullable(parameters.get("address")).orElseGet(() -> "No data provided");
        final String postCode = Optional.ofNullable(parameters.get("postcode")).orElseGet(() -> "No data provided");
        final String ssn = Optional.ofNullable(parameters.get("ssn")).orElseGet(() -> "No data provided");

        document.add(new Paragraph("Channel : " + channelName));
        document.add(new Paragraph("Item : " + itemName));
        document.add(new Paragraph("Address : " + address));
        document.add(new Paragraph("Postcode : " + postCode));
        document.add(new Paragraph("Ssn : " + ssn));
        methodsList = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
        methodsList.setAutoindent(true);
        for (Map.Entry<String, java.util.List<String>> pair : container) {
            final String key = Optional.ofNullable(pair.getKey()).orElseGet(() -> "No data provided");
            final java.util.List<String> value = Optional.ofNullable(pair.getValue()).orElseGet(() -> Collections.emptyList());
            methodsList.add(" " + key + " " + value);
        }
        document.add(methodsList);
        document.add(spacing);
        document.add(LINE_SEPARATOR);
    }

    public void createImagePdf() throws IOException, DocumentException {
        try {
            imageFiles = Files.walk(Paths.get("target/pdf_screenshots"))
                    .filter(Files::isRegularFile).map(Path::toFile).map(String::valueOf)
                    .collect(Collectors.toList());
            imageFiles.forEach(System.out::println);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        Image img = Image.getInstance(imageFiles.get(0));
        Document document = new Document(img);
        PdfWriter.getInstance(document, new FileOutputStream(SCREENSHOT_FILEPATH));
        document.open();
        for (String image : imageFiles) {
            img = Image.getInstance(image);
            document.setPageSize(img);
            document.newPage();
            img.setAbsolutePosition(0, 0);
            document.add(img);
        }
        document.close();
    }
}
