package com.Server.service.impl;

import com.Server.dto.Request.ReservationRequest;
import com.Server.entiy.Car;
import com.Server.repository.CarRepository;
import com.Server.service.PdfResume;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Class Service implements interface PDFResume.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */

@Service
public class PdfResumeImpl implements PdfResume {

    private final Font TIMES_ROMAN_32 = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.NORMAL);
    private final Font TIMES_ROMAN_14 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.NORMAL);
    private final Font TIMES_ROMAN_12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    private final Font TIMES_ROMAN_8 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
    private PdfPTable table;
    private Document document;
    private ByteArrayOutputStream out;
    private ReservationRequest reservation;
    private final CarRepository carRepository;

    @Autowired
    public PdfResumeImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Method creaete pdf file
     * @param reservation data on reservation
     * @return pdf file on InputStream class
     * @throws IOException
     * @throws DocumentException
     */
    @Override
    public InputStream generatePdf(ReservationRequest reservation) throws IOException, DocumentException {
        table = new PdfPTable(4);
        table.setWidthPercentage(100);
        document = new Document();
        out = new ByteArrayOutputStream();
        document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();
        addTitlePage(document);
        this.reservation = reservation;
        return createTable(document);
    }

    /**
     * Add title Page on pdf.
     * @param document
     * @throws DocumentException
     * @throws IOException
     */
    private void addTitlePage(Document document) throws DocumentException, IOException {
        Paragraph preface = new Paragraph();
        preface.add(new Paragraph("FAKTURA VAT", TIMES_ROMAN_32));
        preface.setAlignment(Element.ALIGN_CENTER);
        Image image = Image.getInstance("https://www.car-rentals-chania.gr/wp-content/uploads/2018/07/mar-car-rental-logo_fb.jpg");


        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        PdfPCell pdfCell = new PdfPCell();
        pdfCell.setBorder(Rectangle.NO_BORDER);
        pdfCell.setPhrase(new Phrase(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.now()) + "\n\nUslugo dawca \n" +
                "Wypożyczalnia samochodów Car-Rental sp. z o.o\n" +
                "31-153 Kraków ul.Lubicz 242\n" +
                "Rachunek ING Bank Slaski S.A. \nnr 22 2222 2222 2222 2222 2222 2222\n\n\n" +
                "Kontakt:\n" +
                "tel: 012 222 222 222\n" +
                "email: car-rental@gmail.com\n"
                , TIMES_ROMAN_12));
        table.addCell(pdfCell);

        pdfCell.setImage(image);
        pdfCell.setFixedHeight(60);
        pdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(pdfCell);

        Paragraph paragraph = new Paragraph();

        for (int i = 0; i < 10; i++) {
            paragraph.add(new Paragraph(""));
        }

        document.add(preface);
        document.add(table);
        document.add(paragraph);


    }

    /**
     * Create table on document pdf.
     * @param document
     * @return pdf file.
     * @throws DocumentException
     */
    private InputStream createTable(Document document) throws DocumentException {


        PdfPCell pdfPCell = new PdfPCell(new Phrase("Nazwa uslugi", TIMES_ROMAN_12));
        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("Liczba dni wynajmu", TIMES_ROMAN_12));
        table.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("Nazwa towaru", TIMES_ROMAN_12));
        table.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("Cena za dobe", TIMES_ROMAN_12));
        table.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase("Wynajem samochodu", TIMES_ROMAN_8));
        table.addCell(pdfPCell);

        LocalDate dateBefore = LocalDate.parse(reservation.getDateFrom());
        LocalDate dateAfter = LocalDate.parse(reservation.getDateTo());
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);

        pdfPCell.setPhrase(new Phrase(Long.toString(noOfDaysBetween), TIMES_ROMAN_8));
        table.addCell(pdfPCell);
        Car car = carRepository.findByIdcar(reservation.getIdCar()).get();
        pdfPCell.setPhrase(new Phrase(car.getMark() +" "+car.getModel(), TIMES_ROMAN_8));
        table.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase(Float.toString(car.getMoney()), TIMES_ROMAN_8));
        table.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase("Suma", TIMES_ROMAN_12));
        pdfPCell.setColspan(3);
        table.addCell(pdfPCell);
        pdfPCell.setColspan(0);
        pdfPCell.setPhrase(new Phrase(Float.toString(noOfDaysBetween * car.getMoney()), TIMES_ROMAN_8));

        table.addCell(pdfPCell);

        document.add(table);

        Paragraph p = new Paragraph();
        for (int i = 0; i < 19; i++) {
            p.add(new Paragraph(""));
        }

        document.add(p);
        Paragraph preface = new Paragraph();
        preface.add(new Paragraph("W przypadku sprzedaży towaru w obrocie międzyprzedsiębiorcami na podstawie art. 558§1k.c. strony wyłączają odpowiedzialność sprzedającego z tytułu rękojmi za wady fizycznei prawne rzeczy,wszczególności ustalają iż nie ponosi on żadnej odpowiedzialności także za wady ukryte przedmiotu sprzedaży.", TIMES_ROMAN_8));
        preface.setAlignment(Element.ALIGN_LEFT);
        document.add(preface);
        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
