/*
 * The MIT License
 *
 * Copyright 2017 Artur Czartoryski <artur at czartoryski.wroclaw.pl>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.app.gpo.pdf.utils;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.web.servlet.view.AbstractView;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
 
/**
 * This class is a work around for working with iText 5.x in Spring.
 * The code here is almost identical to the AbstractPdfView class.
 *
 */
public abstract class AbstractITextPdfView extends AbstractView {
 
    public AbstractITextPdfView() {
        setContentType("application/pdf");
    }
 
    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }
         
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // IE workaround: write into byte array first.
        ByteArrayOutputStream baos = createTemporaryOutputStream();
 
        // Apply preferences and build metadata.
        Document document = newDocument();
        PdfWriter writer = newWriter(document, baos);
        prepareWriter(model, writer, request);
        buildPdfMetadata(model, document, request);
 
        // Build PDF document.
        document.open();
        buildPdfDocument(model, document, writer, request, response);
        document.close();
 
        // Flush to HTTP response.
        writeToResponse(response, baos);
    }
 
    protected Document newDocument() {
        
        Document document = new Document();

        Rectangle pageSize = new Rectangle(420,297);
        document.setPageSize(pageSize);
        document.setMargins(2, 2, 2, 2);
        
        return document;
        
    }
     
    protected PdfWriter newWriter(Document document, OutputStream os) throws DocumentException {
        return PdfWriter.getInstance(document, os);
    }
     
    protected void prepareWriter(Map<String, Object> model, PdfWriter writer, HttpServletRequest request)
            throws DocumentException {
 
        writer.setViewerPreferences(getViewerPreferences());
    }
     
    protected int getViewerPreferences() {
        return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
    }
     
    protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
    }
     
    protected abstract void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
}