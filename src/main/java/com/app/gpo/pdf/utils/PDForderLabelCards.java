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
import com.app.gpo.model.OrderItem;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.nio.charset.Charset;
import java.util.Iterator;

 
/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * @author www.codejava.net
 *
 */
public class PDForderLabelCards extends AbstractITextPdfView {
 
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        List<OrderItem> orderItemList = (List<OrderItem>) model.get("orderItemList");
        Font font = FontFactory.getFont(FontFactory.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
        font.setSize(18);
        
        Iterator<OrderItem> it = orderItemList.iterator();
        while (it.hasNext()) {
            OrderItem orderItem = it.next();
            String utf = orderItem.getorderItemName();
            byte[] data = utf.getBytes("CP1250");
            String ascii = new String(data);
            String code = orderItem.getorderNumber()+"#"+ascii;
            Paragraph preface = new Paragraph(code,font);
            preface.setAlignment(Element.ALIGN_CENTER);
            doc.add(new Phrase("\n"));
            doc.add(new Phrase("\n"));
            doc.add(new Phrase("\n"));
            doc.add(new Phrase("\n"));
            doc.add(new Phrase("\n"));
            doc.add(new Phrase("\n"));
            
            /*BarcodeEAN barcode = new BarcodeEAN();
            barcode.setCodeType(Barcode.CODE128);
            barcode.setCode(code);
            Rectangle barcodeRect = new Rectangle(400,200);
            barcode.placeBarcode(barcodeRect, BaseColor.BLACK, BaseColor.YELLOW);
            doc.add(barcode.createImageWithBarcode(writer.getDirectContent(), BaseColor.BLACK, BaseColor.GRAY));*/
            doc.add(preface);
            doc.newPage();
        }
         
    }
 
}
