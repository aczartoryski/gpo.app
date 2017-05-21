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
package com.app.gpo.services;

import com.app.gpo.model.OrderItem;
import com.app.gpo.model.OrderStatus;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */
public class OrderItemAutoChangeStatus {
    private static final Logger logger = Logger.getLogger(OrderItemAutoChangeStatus.class);
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderStatusService orderStatusService;
    
    @Scheduled(cron="*/15 0* * * * ?")
    public void scheduleChangeStatus() {
        // something that should execute on weekdays only
        logger.info("Searching for order items to change status");
           
        LocalDate today = new LocalDate();
        LocalDate threDaysAgo = today.minusDays(3);

        OrderStatus orderStatus = orderStatusService.findIdByName("W toku");
        OrderStatus orderStatusNew = orderStatusService.findIdByName("Zakończone");
        
        List<OrderItem> orderItemList = orderItemService.findByOrderStatus(orderStatus);
        Iterator<OrderItem> orderItem = orderItemList.iterator();
        while( orderItem.hasNext()) {
            OrderItem oI = orderItem.next();
            LocalDate orderStatusDate = new LocalDate(oI.getorderStatusDate());
            boolean olderThen3 = threDaysAgo.isAfter(orderStatusDate);
            //logger.info("Order item "+oI.getorderNumber()+" has status change date: "+oI.getorderStatusDate());
            if (olderThen3) {
                logger.info("Order item "+oI.getorderNumber()+" status has been changed to 'Zakończone'.");
                oI.setorderStatus(orderStatusNew);
                orderItemService.update(oI);
            }
        }
    }
}
