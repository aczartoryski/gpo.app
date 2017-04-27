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
package com.app.gpo.controller;

import com.app.gpo.model.Field;
import com.app.gpo.model.FieldMapping;
import com.app.gpo.model.OrderItem;
import com.app.gpo.model.OrderStatus;
import com.app.gpo.model.ProductionSlot;
import com.app.gpo.services.FieldMappingService;
import com.app.gpo.services.FieldService;
import com.app.gpo.services.OrderItemFieldService;
import com.app.gpo.services.OrderItemService;
import com.app.gpo.services.OrderStatusService;
import com.app.gpo.services.ProductionSlotService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */
@Controller
@Transactional
@RequestMapping("/")
public class AppController {
   
   @Autowired
   FieldMappingService fieldMappingService;
   @Autowired
   FieldService fieldService;
   @Autowired
   OrderItemService orderItemService;
   @Autowired
   OrderItemFieldService orderItemFieldService;
   @Autowired
   OrderStatusService orderStatusService;
   @Autowired
   ProductionSlotService productionSlotService;
    
    @RequestMapping(method = RequestMethod.GET, value = "header")
    public String getHeader(Model model) {
        return "/shared/header";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "header")
    public String getHeaderPost (Model model) {
        return "/shared/header";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "footer")
    public String getFooter(Model model) {
        return "/shared/footer";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "footer")
    public String getFooterPost(Model model) {
        return "/shared/footer";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "menu")
    public String getMenuPost(Model model) {
        return "/shared/menu";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "menu")
    public String getMenuGet(Model model) {
        return "/shared/menu";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getHome(Model model) {
        return "redirect:/index";
    }
    
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public ModelAndView showIndex() {
        ModelAndView mv = new ModelAndView("index");
        List<OrderItem> orderItemList = orderItemService.findAll();
        mv.addObject("orderItemList", orderItemList);
        return mv;
    } 
    
    @RequestMapping(value={ "editOrderItem-{orderItemID}" }, method = RequestMethod.GET)
    public ModelAndView ediOrderItem (Model model, @PathVariable Integer orderItemID) {
        OrderItem orderItem = orderItemService.find(orderItemID);
        List<OrderStatus> orderStatusList = orderStatusService.findAll();
        ModelAndView mv = new ModelAndView("editOrderItem");
        mv.addObject("orderItem", orderItem);
        mv.addObject("orderStatusList", orderStatusList);
        return mv;
    }
    
    @RequestMapping(value = { "deleteOrderItem-{orderItemID}" }, method = RequestMethod.GET)
    public String deleteOrderItem(@PathVariable Integer orderItemID,Model model,RedirectAttributes redirectAttributes) {
        String message;
        orderItemService.delete(orderItemID);
        message = "Pozycja zamówienia została poprawnie usunięta." ;
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/index";
    }
    
    @RequestMapping(value="/updateOrderItem", method = RequestMethod.POST)
    public String updateOrderItem(@ModelAttribute OrderItem orderItem,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        String message;
        Date today = new Date();
        if (bindingResult.hasErrors()) {
            message = "W trakcie aktualizacji wystąpiły błędy !";
            message = message + bindingResult.getAllErrors().toString();
        } else {
            if (orderItem.getorderItemID() == 0) {
                orderItem.setorderStatusDate(today);
                orderItemService.save(orderItem);
                message = "Pozycja zamówienia ("+orderItem.getorderItemName()+")została poprawnie dodana." ;
            } else {
                orderItem.setorderStatusDate(today);
                orderItemService.update(orderItem);
                message = "Pozycja zamówienia ("+orderItem.getorderItemName()+") została poprawnie zaktualizowana." ;
            }
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/index";
    }
    
    @RequestMapping(value="/productionSlots", method = RequestMethod.GET)
    public ModelAndView showProductionSlots() {
        ModelAndView mv = new ModelAndView("productionSlots");
        List<ProductionSlot> productionSlotList = productionSlotService.findAll();
        mv.addObject("productionSlotList", productionSlotList);
        return mv;
    }
    
    @RequestMapping(value="/newProductionSlot", method = RequestMethod.GET)
    public ModelAndView newProductionSlot() {
        ModelAndView mv = new ModelAndView("newProductionSlot");
        return mv;
    }
    
    @RequestMapping(value = { "editProductionSlot-{productionSlotID}" }, method = RequestMethod.GET)
    public ModelAndView editProductionSlot(Model model, @PathVariable Integer productionSlotID) {
        ProductionSlot productionSlot = productionSlotService.find(productionSlotID);
        ModelAndView mv = new ModelAndView("editProductionSlot");
        mv.addObject("productionSlot", productionSlot);
        return mv;
    }
    
    
    
        
    @RequestMapping(value="/updateProductionSlot", method = RequestMethod.POST)
    public String updateProductionSlot(@ModelAttribute ProductionSlot productionSlot,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        String message;
        if (bindingResult.hasErrors()) {
            message = "W trakcie aktualizacji wystąpiły błędy !";
            message = message + bindingResult.getAllErrors().toString();
        } else {
            if (productionSlot.getproductionSlotID() == 0) {
                productionSlotService.save(productionSlot);
                message = "Gniazdo produkcyjne zostało poprawnie dodane." ;
            } else {
                productionSlotService.update(productionSlot);
                message = "Gniazdo produkcyjne zostało poprawnie zaktualizowane." ;
            }
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/productionSlots";
    }
    
    @RequestMapping(value = { "deleteProductionSlot-{productionSlotID}" }, method = RequestMethod.GET)
    public String deleteProductionSlot(@PathVariable Integer productionSlotID,Model model,RedirectAttributes redirectAttributes) {
        String message;
        productionSlotService.delete(productionSlotID);
            message = "Gniazdo produkcyjne zostało poprawnie usunięte." ;
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/productionSlots";
    }
    
    @RequestMapping(value="/fieldMappings", method = RequestMethod.GET)
    public ModelAndView showfieldMappings() {
        ModelAndView mv = new ModelAndView("fieldMappings");
        List<ProductionSlot> productionSlotList = productionSlotService.findAll();
        mv.addObject("productionSlotList", productionSlotList);
        return mv;
    } 
    
    @RequestMapping(value="/editFieldMapping-{productionSlotID}", method = RequestMethod.GET)
    public ModelAndView editfieldMapping(Model model, @PathVariable Integer productionSlotID) {
        ModelAndView mv = new ModelAndView("editFieldMapping");
        ProductionSlot productionSlot = productionSlotService.find(productionSlotID);
        mv.addObject("productionSlot", productionSlot);
        
        List<FieldMapping> fieldMappingList = fieldMappingService.findByProductionSlotID(productionSlotID);
        mv.addObject("fieldMappingList", fieldMappingList);
        
        List<Field> fieldList = fieldService.findAllNotAssignToProductSlot(productionSlotID);
        mv.addObject("fieldList",fieldList);
        return mv;
    } 
    
    @RequestMapping(value="/newFieldMapping", method = RequestMethod.GET)
    public ModelAndView newfieldMapping() {
        ModelAndView mv = new ModelAndView("newFieldMapping");
        //List<OrderItem> orderItemList = orderItemService.findAll();
        //mv.addObject("orderItemList", orderItemList);
        return mv;
    } 
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        ModelAndView mv = new ModelAndView("logout");
        //List<OrderItem> orderItemList = orderItemService.findAll();
        //mv.addObject("orderItemList", orderItemList);
        return mv;
    } 
}
