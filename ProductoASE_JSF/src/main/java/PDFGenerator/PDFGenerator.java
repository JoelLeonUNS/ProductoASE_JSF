package PDFGenerator;

import java.awt.Color;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFGenerator {

    private static final Color HEADER_COLOR = new Color(0xEF, 0x62, 0x62);

    public void generarInformePDF(List<Object[]> datosInforme, String[] headers, String nombre) throws IOException {
        try ( PDDocument document = new PDDocument()) {
            float margin = 50;
            float yStart = PDRectangle.A4.getHeight() - margin;
            float yPosition = yStart;
            int rowsPerPage = 10;

            float[] columnWidths = calcularAnchosColumnas(datosInforme, headers);

            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            agregarEncabezado(document, page, "UNS - UNIDAD MEDICA");
            
            try ( PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true)) {
                contentStream.setNonStrokingColor(Color.BLACK);
                contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);

                for (int i = 0; i < headers.length; i++) {
                    contentStream.showText(headers[i]);
                    contentStream.newLineAtOffset(columnWidths[i], 0);
                }

                contentStream.endText();

                // Contenido de la tabla
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                for (Object[] datos : datosInforme) {
                    yPosition -= 20;
                    if (yPosition < margin) {
                        contentStream.close();
                        page = new PDPage(PDRectangle.A4);
                        document.addPage(page);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                        yPosition = yStart;

                        contentStream.beginText();
                        contentStream.newLineAtOffset(margin, yPosition);

                        for (int i = 0; i < headers.length; i++) {
                            contentStream.showText(headers[i]);
                            contentStream.newLineAtOffset(columnWidths[i], 0);
                        }
                        contentStream.endText();
                    }
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition);
                    for (int i = 0; i < columnWidths.length; i++) {
                        if (i < datos.length && datos[i] != null) {
                            contentStream.showText(datos[i].toString());
                        } else {
                            contentStream.showText("N/A");
                        }
                        contentStream.newLineAtOffset(columnWidths[i], 0);
                    }
                    contentStream.endText();
                }
                contentStream.close();
            }

            document.save(nombre + ".pdf");
            mostrarMensaje("PDF generado exitosamente: " + nombre + ".pdf");
        }
    }

    private float[] calcularAnchosColumnas(List<Object[]> datosInforme, String[] headers) throws IOException {
        float[] columnWidths = new float[headers.length];
        for (int i = 0; i < headers.length; i++) {
            columnWidths[i] = PDType1Font.TIMES_ROMAN.getStringWidth(headers[i]) / 1000 * 12 + 10; // Ancho mínimo
        }
        for (Object[] datos : datosInforme) {
            for (int i = 0; i < columnWidths.length; i++) {
                if (i < datos.length && datos[i] != null) {
                    float contentWidth = PDType1Font.TIMES_ROMAN.getStringWidth(datos[i].toString()) / 1000 * 12 + 10;
                    if (contentWidth > columnWidths[i]) {
                        columnWidths[i] = contentWidth;
                    }
                }
            }
        }

        return columnWidths;
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Generación de PDF", JOptionPane.INFORMATION_MESSAGE);
    }

    private void agregarEncabezado(PDDocument document, PDPage page, String headerText) throws IOException {
        try ( PDPageContentStream headerStream = new PDPageContentStream(document, page, AppendMode.PREPEND, true, true)) {
            float pageWidth = page.getMediaBox().getWidth();
            float headerHeight = 30;

            // Fondo del encabezado
            headerStream.setNonStrokingColor(HEADER_COLOR);
            headerStream.addRect(0, page.getMediaBox().getHeight() - headerHeight, pageWidth, headerHeight);
            headerStream.fill();

            // Texto del encabezado
            headerStream.setNonStrokingColor(Color.WHITE);
            headerStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            headerStream.beginText();
            headerStream.newLineAtOffset(10, page.getMediaBox().getHeight() - 15);
            headerStream.showText(headerText);
            headerStream.endText();
        }
    }
}
