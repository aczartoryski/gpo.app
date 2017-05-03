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
import com.app.gpo.model.FieldMapping;
import com.app.gpo.model.OrderItem;
import com.app.gpo.model.OrderItemField;
import com.app.gpo.model.ProductionSlot;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
 
/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * @author www.codejava.net
 *
 */
public class PDFOrderItem extends AbstractITextPdfView {
 
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        OrderItem orderItem = (OrderItem) model.get("orderItem");
        ProductionSlot productionSlot = (ProductionSlot) model.get("productionSlot");
        
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        
        String dateTitle = "Karta produkcji wygenerowana: "+dateFormat.format(today);
        String orderTitle = " Zamówienie numer: "+orderItem.getorderNumber();
        String productionSlotTitle = " Gniazdo produkcyjne: ("+productionSlot.getProductionSlotNumber()+") "+productionSlot.getProductionSlotDescription();
        
        doc.add(new Paragraph(dateTitle+" |"+orderTitle+" |"+productionSlotTitle));

        Set<OrderItemField> orderItemFields = orderItem.getorderItemFields();
        Set<FieldMapping> fieldMappings = productionSlot.getFieldMappings();
        
        Integer columnQTY = fieldMappings.size()+2;
        PdfPTable table = new PdfPTable(columnQTY);
        float[] columnWidth = new float[columnQTY];
        for (int i=0; i<fieldMappings.size(); i++) {
            columnWidth[i] = 0.5f;
        }
        table.setTotalWidth(doc.getPageSize().getWidth() - 80);
        table.setWidths(columnWidth);
        table.setLockedWidth(true);
        table.setSpacingBefore(5);
        // define font for table header row
        //Font font = FontFactory.getFont(FontFactory.HELVETICA);
        BaseFont bf = BaseFont.createFont("c:/windows/fonts/arial.ttf", 
        BaseFont.CP1250, BaseFont.EMBEDDED); 
        Font font = new Font(bf, 12); 
        font.setColor(BaseColor.BLACK);
        font.setSize(9);
         
        // define table header cell
        PdfPCell hcell = new PdfPCell();
        hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        hcell.setPadding(2);
        hcell.setRotation(90);
        hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        // write table header
        // Static add always field "Wyrób" and "Termin"
        hcell.setPhrase(new Phrase("Wyrób", font));
        table.addCell(hcell);
        hcell.setPhrase(new Phrase("Termin", font));
        table.addCell(hcell);
        // Iterate list of mapped fields for ProductionSlot
        Iterator<FieldMapping> fM = fieldMappings.iterator();
        while (fM.hasNext()) {
            hcell.setPhrase(new Phrase(fM.next().getfield().getFieldLabel(), font));
            table.addCell(hcell);
        }
        
        // define table cells
        PdfPCell cell = new PdfPCell();
        cell.setPadding(2);
        
        cell.setPhrase(new Phrase(orderItem.getorderItemName(), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(dateFormat2.format(orderItem.getorderItemDueDate()), font));
        table.addCell(cell);
        
        fM = fieldMappings.iterator();
        while (fM.hasNext()) {
            cell.setPhrase(new Phrase(fM.next().getfield().getFieldLabel(), font));
            table.addCell(cell);
        }
        
        doc.add(table);
         
    }
 
}
