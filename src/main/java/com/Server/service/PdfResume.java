package com.Server.service;

import com.Server.dto.Request.ReservationRequest;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.io.InputStream;
/**
 * Interface Service PDFResume to create resume on reservation.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */

public interface PdfResume {
    /**
     * Method create pdf file.
     * @param reservation
     * @return InputStream - pdf file.
     * @throws IOException
     * @throws DocumentException
     */
    InputStream generatePdf(Long id) throws IOException, DocumentException;
}
