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

import com.app.gpo.model.ArrayView;
import com.app.gpo.model.Field;
import com.app.gpo.model.FieldMapping;
import com.app.gpo.model.FieldMappingToView;
import com.app.gpo.model.FileUploadForm;
import com.app.gpo.model.OrderItem;
import com.app.gpo.model.OrderItemField;
import com.app.gpo.model.OrderStatus;
import com.app.gpo.model.ProductionSlot;
import com.app.gpo.services.ArrayViewService;
import com.app.gpo.services.FieldMappingService;
import com.app.gpo.services.FieldMappingToViewService;
import com.app.gpo.services.FieldService;
import com.app.gpo.services.OrderItemFieldService;
import com.app.gpo.services.OrderItemService;
import com.app.gpo.services.OrderStatusService;
import com.app.gpo.services.ProductionSlotService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
   private static final Logger logger = Logger.getLogger(AppController.class);
   
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
   @Autowired
   FieldMappingToViewService fieldMappingToViewService; 
   @Autowired
   ArrayViewService arrayViewService;
   
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
    
    @RequestMapping(method = RequestMethod.GET, value = "scripts")
    public String getscripts(Model model) {
        return "/shared/scripts";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "scripts")
    public String getscriptsPost(Model model) {
        return "/shared/scripts";
    }
    
        
    @RequestMapping(method = RequestMethod.POST, value = "menu")
    public String getMenuPost(Model model) {
        logger.info("MVC started getMenuPost");
        model.addAttribute("user", getPrincipal());
        model.addAttribute("arrayViewList", arrayViewService.findAll());
        logger.info("MVC finished getMenuPost");
        return "/shared/menu";
        
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "menu")
    public String getMenuGet(Model model) {
        logger.info("MVC started getMenuGet");
        model.addAttribute("user", getPrincipal());
        model.addAttribute("arrayViewList", arrayViewService.findAll());
        logger.info("MVC finished getMenuGet");
        return "/shared/menu";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String getLogin(Model model) {
        return "/login";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String getLoginPost(Model model) {
        return "/login";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getHome(Model model) {
        return "redirect:/index";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public String getHomePost(Model model) {
        return "redirect:/index";
    }
    
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public ModelAndView showIndex() {
        logger.info("MVC started showIndex");
        ModelAndView mv = new ModelAndView("index");
        OrderStatus orderStatus = orderStatusService.findIdByName("Zakończone");
        List<OrderItem> orderItemList = orderItemService.findNotOrderStatus(orderStatus);
        mv.addObject("orderItemList", orderItemList);
        List<Field> fieldsForTable = fieldService.findAll();
        mv.addObject("fieldsForTable", fieldsForTable);
        logger.info("MVC finished showIndex");
        return mv;
    }

    
    @RequestMapping(value="/listOrders-{arrayViewID}", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView listOrders(Model model, @PathVariable Integer arrayViewID) {
        logger.info("MVC started listOrders");
        ModelAndView mv = new ModelAndView("listOrders");
        ArrayView arrayView = arrayViewService.find(arrayViewID);
        mv.addObject("arrayView", arrayView);
        OrderStatus orderStatus = orderStatusService.findIdByName("Zakończone");
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        if (arrayView.getArrayViewForClosed()) {
            orderItemList = orderItemService.findByOrderStatus(orderStatus); 
        } else {
            orderItemList = orderItemService.findNotOrderStatus(orderStatus);
        }
        mv.addObject("orderItemList", orderItemList);
        List<Field> fieldsForTable = fieldService.findAllAssignToArrayView(arrayViewID);
        mv.addObject("fieldsForTable", fieldsForTable);
        logger.info("MVC finished listOrders");
        return mv;
    } 
    
   
    @RequestMapping(value="/index", method = RequestMethod.POST)
    public ModelAndView showIndexPost() {
        logger.info("MVC started showIndexPost");
        ModelAndView mv = new ModelAndView("index");
        OrderStatus orderStatus = orderStatusService.findIdByName("Zakończone");
        List<OrderItem> orderItemList = orderItemService.findNotOrderStatus(orderStatus);
        mv.addObject("orderItemList", orderItemList);
        List<Field> fieldsForTable = fieldService.findAll();
        mv.addObject("fieldsForTable", fieldsForTable);
        logger.info("MVC finished showIndexPost");
        return mv;
    } 
    
    @RequestMapping(value="/listOrders-{arrayViewID}", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public ModelAndView listOrdersPost(Model model, @PathVariable Integer arrayViewID) {
        logger.info("MVC started listOrdersPost");
        ModelAndView mv = new ModelAndView("listOrders");
        ArrayView arrayView = arrayViewService.find(arrayViewID);
        mv.addObject("arrayView", arrayView);
        OrderStatus orderStatus = orderStatusService.findIdByName("Zakończone");
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        if (arrayView.getArrayViewForClosed()) {
            orderItemList = orderItemService.findByOrderStatus(orderStatus); 
        } else {
            orderItemList = orderItemService.findNotOrderStatus(orderStatus);
        }
        mv.addObject("orderItemList", orderItemList);
        List<Field> fieldsForTable = fieldService.findAllAssignToArrayView(arrayViewID);
        mv.addObject("fieldsForTable", fieldsForTable);
        logger.info("MVC finished listOrdersPost");
        return mv;
    } 
    
    
    @RequestMapping(value = "/selectProductionSlotMultiple", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public ModelAndView selectProductionSlotMultiple(Model model,@RequestParam(value="orderItemID")Integer[] checkboxList) {
        logger.info("MVC started selectProductionSlotMultiple");
        ModelAndView mv = new ModelAndView("selectProductionSlotMultiple");
        List<ProductionSlot> productionSlotList = productionSlotService.findAll(); 
        List<OrderItem> orderItemList = new ArrayList<>(0);

       for (Integer checkboxList1 : checkboxList) {
           logger.info("Selected to print orderID : " + checkboxList1);
           orderItemList.add(orderItemService.find(checkboxList1));
       }
        mv.addObject("productionSlotList", productionSlotList);
        mv.addObject("ordersList", orderItemList);
        logger.info("MVC finished selectProductionSlotMultiple");
        return mv;
    } 
    
    @RequestMapping(value = "/importOrderItems", method = RequestMethod.GET)
	public String showImportOrderItems() {
		return "importOrderItems";
	}

    
    @RequestMapping(value={ "/editOrderItem-{orderItemID}" }, method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView editOrderItem (Model model, @PathVariable Integer orderItemID) {
        logger.info("MVC started editOrderItem");
        OrderItem orderItem = orderItemService.find(orderItemID);
        List<OrderStatus> orderStatusList = orderStatusService.findAll();
        ModelAndView mv = new ModelAndView("editOrderItem");
        mv.addObject("orderItem", orderItem);
        mv.addObject("orderStatusList", orderStatusList);
        logger.info("MVC finished editOrderItem");
        return mv;
    }
    
    @RequestMapping(value={ "/viewOrderItem-{orderItemID}" }, method = RequestMethod.GET)
    public ModelAndView viewOrderItem (Model model, @PathVariable Integer orderItemID) {
        logger.info("MVC started viewOrderItem");
        OrderItem orderItem = orderItemService.find(orderItemID);
        List<OrderStatus> orderStatusList = orderStatusService.findAll();
        ModelAndView mv = new ModelAndView("viewOrderItem");
        mv.addObject("orderItem", orderItem);
        mv.addObject("orderStatusList", orderStatusList);
        logger.info("MVC finished viewOrderItem");
        return mv;
    }
    
    @RequestMapping(value = { "/deleteOrderItem-{orderItemID}" }, method = RequestMethod.GET)
    public String deleteOrderItem(@PathVariable Integer orderItemID,Model model,RedirectAttributes redirectAttributes) {
        logger.info("MVC started deleteOrderItem");
        String message;
        orderItemService.delete(orderItemID);
        message = "Pozycja zamówienia została poprawnie usunięta." ;
        redirectAttributes.addFlashAttribute("message", message);
        logger.info("MVC finished deleteOrderItem");
        return "redirect:/index";
    }
    
    @RequestMapping(value="/deleteOrderItems", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String deleteOrderItems(Model model, @RequestParam(value="orderItemID")Integer[] orderItemIDs,RedirectAttributes redirectAttributes) {
        logger.info("MVC started deleteOrderItems");
       String message = "Usunięto pozycje zamówień :: "+orderItemIDs.length+"sztuk(i).";
       
       for (Integer id : orderItemIDs) {
            logger.info("Sended to delete orderID : " + id);
            orderItemService.delete(id);
       }
       
       redirectAttributes.addFlashAttribute("message", message);
        logger.info("MVC finished deleteOrderItems");
       return "redirect:/index";
    }
    
    @RequestMapping(value="/statusChangeForOrderItems", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public ModelAndView statusChangeForOrderItems(Model model, @RequestParam(value="orderItemID")Integer[] orderItemIDs) {
        logger.info("MVC started statusChangeForOrderItems");
        ModelAndView mv = new ModelAndView("statusChangeForOrderItems");
       
       List<OrderItem> orderItemList = new ArrayList<>(0);
       for (Integer orderItemID : orderItemIDs) {
           logger.info("Sended to change status orderID : " + orderItemID);
           orderItemList.add(orderItemService.find(orderItemID));
       }
       mv.addObject("orderStatuses", orderStatusService.findAll());
       mv.addObject("orderItemList", orderItemList);
        logger.info("MVC finished statusChangeForOrderItems");
       return mv;
    }
    
    @RequestMapping(value="saveStatusChangeForOrderItems", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String saveStatusChangeForOrderItems(Model model, @RequestParam(value="orderItemID")Integer[] orderItemIDs, @RequestParam(value="orderStatus")Integer orderStatusID, RedirectAttributes redirectAttributes) {
        logger.info("MVC started saveStatusChangeForOrderItems");
       OrderStatus orderStatus = orderStatusService.find(orderStatusID);
       
       List<OrderItem> orderItemList = new ArrayList<>(0);
       for (Integer orderItemID : orderItemIDs) {
           logger.info("Change status for orderID : " + orderItemID);
           orderItemList.add(orderItemService.find(orderItemID));
       }
       Iterator<OrderItem> it = orderItemList.iterator();
       while (it.hasNext()) {
           OrderItem orderItem = it.next();
           orderItem.setorderStatus(orderStatus);
           orderItemService.update(orderItem);
       }
       
       String message = "Zmiana statusu na '"+orderStatus.getorderStatusName()+"' została zapisana dla "+orderItemList.size()+" zamówień.";
       redirectAttributes.addFlashAttribute("message", message);
        logger.info("MVC finished saveStatusChangeForOrderItems");
       return "redirect:/index";
    }
    
    @RequestMapping(value="/updateOrderItem", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String updateOrderItem(@ModelAttribute OrderItem orderItem,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        logger.info("MVC started updateOrderItem");
        String message;
        Date today = new Date();
        if (bindingResult.hasErrors()) {
            message = "W trakcie aktualizacji wystąpiły błędy !";
            message = message + bindingResult.getAllErrors().toString();
        } else {
            if (orderItem.getorderItemID() == 0) {
                orderItem.setorderStatusDate(today);
                orderItemService.save(orderItem);
                message = "Pozycja zamówienia ("+orderItem.getorderNumber()+")została poprawnie dodana." ;
            } else {
                orderItem.setorderStatusDate(today);
                orderItemService.update(orderItem);
                message = "Pozycja zamówienia ("+orderItem.getorderNumber()+") została poprawnie zaktualizowana." ;
            }
        }
        redirectAttributes.addFlashAttribute("message", message);
        logger.info("MVC finished updateOrderItem");
        return "redirect:/index";
    }
    
    @RequestMapping(value="/productionSlots", method = RequestMethod.GET)
    public ModelAndView showProductionSlots() {
        logger.info("MVC started showProductionSlots");
        ModelAndView mv = new ModelAndView("productionSlots");
        List<ProductionSlot> productionSlotList = productionSlotService.findAll();
        mv.addObject("productionSlotList", productionSlotList);
        logger.info("MVC finished showProductionSlots");
        return mv;
    }
    
    @RequestMapping(value="/arrayViews", method = RequestMethod.GET)
    public ModelAndView showArrayViews() {
        logger.info("MVC started showArrayViews");
        ModelAndView mv = new ModelAndView("arrayViews");
        List<ArrayView> arrayViewList = arrayViewService.findAll();
        mv.addObject("arrayViewList", arrayViewList);
        logger.info("MVC finished showArrayViews");
        return mv;
    }
    
    @RequestMapping(value="/newProductionSlot", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView newProductionSlot() {
        logger.info("MVC started newProductionSlot");
        ModelAndView mv = new ModelAndView("newProductionSlot");
        logger.info("MVC finished newProductionSlot");
        return mv;
    }
    
    @RequestMapping(value="/newArrayView", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView newArrayView() {
        logger.info("MVC started newArrayView");
        ModelAndView mv = new ModelAndView("newArrayView");
        logger.info("MVC finished newArrayView");
        return mv;
    }
    
    @RequestMapping(value = { "/editProductionSlot-{productionSlotID}" }, method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView editProductionSlot(Model model, @PathVariable Integer productionSlotID) {
        logger.info("MVC started editProductionSlot");
        ProductionSlot productionSlot = productionSlotService.find(productionSlotID);
        ModelAndView mv = new ModelAndView("editProductionSlot");
        mv.addObject("productionSlot", productionSlot);
        logger.info("MVC finished editProductionSlot");
        return mv;
    }
    
    @RequestMapping(value = { "/editArrayView-{arrayViewID}" }, method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView editArrayView(Model model, @PathVariable Integer arrayViewID) {
        logger.info("MVC started editArrayView");
        ArrayView arrayView = arrayViewService.find(arrayViewID);
        ModelAndView mv = new ModelAndView("editArrayView");
        mv.addObject("arrayView", arrayView);
        logger.info("MVC finished editArrayView");
        return mv;
    }
    
            
    @RequestMapping(value="/updateProductionSlot", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String updateProductionSlot(@ModelAttribute ProductionSlot productionSlot,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        logger.info("MVC started updateProductionSlot");
        String message;
        if (bindingResult.hasErrors()) {
            message = "W trakcie aktualizacji wystąpiły błędy !";
            message = message + bindingResult.getAllErrors().toString();
        } else {
            if (productionSlot.getproductionSlotID() == 0) {
                productionSlotService.save(productionSlot);
                message = "Gniazdo produkcyjne ("+productionSlot.getProductionSlotDescription()+") zostało poprawnie dodane." ;
            } else {
                productionSlotService.update(productionSlot);
                message = "Gniazdo produkcyjne ("+productionSlot.getProductionSlotDescription()+") zostało poprawnie zaktualizowane." ;
            }
        }
        redirectAttributes.addFlashAttribute("message", message);
        logger.info("MVC finished updateProductionSlot");
        return "redirect:/productionSlots";
    }
    
    @RequestMapping(value="/updateArrayView", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String updateArrayView(@ModelAttribute ArrayView arrayView,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        logger.info("MVC started updateArrayView");
        String message;
        if (bindingResult.hasErrors()) {
            message = "W trakcie aktualizacji wystąpiły błędy !";
            message = message + bindingResult.getAllErrors().toString();
        } else {
            if (arrayView.getArrayViewID() == 0) {
                arrayViewService.save(arrayView);
                message = "Widok tabeli ("+arrayView.getArrayViewName()+") został poprawnie zapisany." ;
            } else {
                arrayViewService.update(arrayView);
                message = "Widok tabeli ("+arrayView.getArrayViewName()+") został poprawnie zaktualizowany." ;
            }
        }
        redirectAttributes.addFlashAttribute("message", message);
        logger.info("MVC finished updateArrayView");
        return "redirect:/arrayViews";
    }
    
    
    @RequestMapping(value = { "/deleteProductionSlot-{productionSlotID}" }, method = RequestMethod.GET)
    public String deleteProductionSlot(@PathVariable Integer productionSlotID,Model model,RedirectAttributes redirectAttributes) {
        logger.info("MVC started deleteProductionSlot");
        String message;
        productionSlotService.delete(productionSlotID);
        message = "Gniazdo produkcyjne zostało poprawnie usunięte." ;
        redirectAttributes.addFlashAttribute("message", message);
        logger.info("MVC finished deleteProductionSlot");
        return "redirect:/productionSlots";
    }
    
    @RequestMapping(value = { "/deleteArrayView-{arrayViewID}" }, method = RequestMethod.GET)
    public String deleteArrayView(@PathVariable Integer arrayViewID,Model model,RedirectAttributes redirectAttributes) {
        logger.info("MVC started deleteArrayView");
        String message;
        arrayViewService.delete(arrayViewID);
        message = "Widok został poprawnie usunięte." ;
        redirectAttributes.addFlashAttribute("message", message);
        logger.info("MVC finished deleteArrayView");
        return "redirect:/arrayViews";
    }
    
    @RequestMapping(value="/fieldMappings", method = RequestMethod.GET)
    public ModelAndView showfieldMappings() {
        logger.info("MVC started showfieldMappings");
        ModelAndView mv = new ModelAndView("fieldMappings");
        List<ProductionSlot> productionSlotList = productionSlotService.findAll();
        mv.addObject("productionSlotList", productionSlotList);
        logger.info("MVC finished showfieldMappings");
        return mv;
    } 
    
    @RequestMapping(value="/editFieldMapping-{productionSlotID}", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView editfieldMapping(Model model, @PathVariable Integer productionSlotID) {
        logger.info("MVC started editfieldMapping");
        ModelAndView mv = new ModelAndView("editFieldMapping");
        ProductionSlot productionSlot = productionSlotService.find(productionSlotID);
        mv.addObject("productionSlot", productionSlot);
        List<FieldMapping> fieldMappingList = fieldMappingService.findByProductionSlotID(productionSlotID);
        mv.addObject("fieldMappingList", fieldMappingList);
        List<Field> fieldList = fieldService.findAllNotAssignToProductSlot(productionSlotID);
        mv.addObject("fieldList",fieldList);
        logger.info("MVC finished editfieldMapping");
        return mv;
    } 
    
    @RequestMapping(value="/editFieldMappingToView-{arrayViewID}", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView editFieldMappingToView(Model model, @PathVariable Integer arrayViewID) {
        logger.info("MVC started editFieldMappingToView");
        ModelAndView mv = new ModelAndView("editFieldMappingToView");
        ArrayView arrayView = arrayViewService.find(arrayViewID);
        mv.addObject("arrayView", arrayView);
        List<FieldMappingToView> fieldMappingToViewList = fieldMappingToViewService.findByArrayViewID(arrayViewID);
        mv.addObject("fieldMappingToViewList", fieldMappingToViewList);
        List<Field> fieldList = fieldService.findAllNotAssignToArrayView(arrayViewID);
        mv.addObject("fieldList",fieldList);
        logger.info("MVC finished editFieldMappingToView");
        return mv;
    } 
    
    @RequestMapping(value="/deleteFieldMapping-{productionSlotID}", method = RequestMethod.GET)
    public String deletefieldMapping(RedirectAttributes redirectAttributes, @PathVariable Integer productionSlotID) {
        logger.info("MVC started deletefieldMapping");
        fieldMappingService.deleteByProductionSlotID(productionSlotID);
        String message = "Mapowanie pól zostało wyczyszczone prawidłowo.";
        redirectAttributes.addFlashAttribute("message", message);
        logger.info("MVC finished deletefieldMapping");
        return "redirect:/fieldMappings";
    } 
    
    @RequestMapping(value={ "saveFieldMapping" }, method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String saveFieldMapping (@RequestParam("fieldMappingString") String getFormString,@RequestParam("productionSlotID") Integer productionSlotID, RedirectAttributes redirectAttributes) throws JSONException {
        logger.info("MVC started saveFieldMapping");
        logger.info("saveFieldMapping.RequestParam: "+getFormString);
        String fieldMappingString = getFormString.replaceAll("fieldMapping_", "{\"fieldmapping\":");
        fieldMappingString = fieldMappingString.replaceAll(",", "},");
        fieldMappingString = "{\"fieldmappings\":["+fieldMappingString + "}]}";
        fieldMappingService.deleteByProductionSlotID(productionSlotID);
        JSONObject fieldMappingOrderJSON = new JSONObject(fieldMappingString);
        JSONArray fieldMappingOrderArray = fieldMappingOrderJSON.getJSONArray("fieldmappings");
        for (int i=0; i<fieldMappingOrderArray.length(); i++) {
            JSONObject fieldMappingOrderItem = fieldMappingOrderArray.getJSONObject(i);
            Integer fieldID = fieldMappingOrderItem.getInt("fieldmapping");
            logger.info("saveFieldMapping.loop.fieldID: "+fieldID);
            Field field = fieldService.find(fieldID);
            ProductionSlot productionSlot = productionSlotService.find(productionSlotID);
            Integer fieldMappingOrder = i+1;
            FieldMapping fieldMapping = new FieldMapping ();
            fieldMapping.setProductionSlot(productionSlot);
            fieldMapping.setfield(field);
            fieldMapping.setfieldMappingOrder(fieldMappingOrder);
            fieldMappingService.save(fieldMapping);
        }
        String message = "Mapowanie pól zostało zapisane prawidłowo.";
        redirectAttributes.addFlashAttribute("message", message);
        logger.info("MVC finished saveFieldMapping");
        return "redirect:/fieldMappings";
    }
    
    
    @RequestMapping(value={ "saveFieldMappingToView" }, method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String saveFieldMappingToView (@RequestParam("fieldMappingString") String getFormString,@RequestParam("arrayViewID") Integer arrayViewID, RedirectAttributes redirectAttributes) throws JSONException {
        logger.info("MVC started saveFieldMappingToView");
        logger.info("saveFieldMappingToView.RequestParam: "+getFormString);
        String fieldMappingString = getFormString.replaceAll("fieldMapping_", "{\"fieldmapping\":");
        fieldMappingString = fieldMappingString.replaceAll(",", "},");
        fieldMappingString = "{\"fieldmappings\":["+fieldMappingString + "}]}";
        fieldMappingToViewService.deleteByArrayViewID(arrayViewID);
        JSONObject fieldMappingOrderJSON = new JSONObject(fieldMappingString);
        JSONArray fieldMappingOrderArray = fieldMappingOrderJSON.getJSONArray("fieldmappings");
        for (int i=0; i<fieldMappingOrderArray.length(); i++) {
            JSONObject fieldMappingOrderItem = fieldMappingOrderArray.getJSONObject(i);
            Integer fieldID = fieldMappingOrderItem.getInt("fieldmapping");
            logger.info("saveFieldMapping.loop.fieldID: "+fieldID);
            Field field = fieldService.find(fieldID);
            ArrayView arrayView = arrayViewService.find(arrayViewID);
            Integer fieldMappingOrder = i+1;
            FieldMappingToView fieldMappingToView = new FieldMappingToView ();
            fieldMappingToView.setArrayView(arrayView);
            fieldMappingToView.setfield(field);
            fieldMappingToView.setfieldMappingToViewOrder(fieldMappingOrder);
            fieldMappingToViewService.save(fieldMappingToView);
        }
        String message = "Mapowanie pól zostało zapisane prawidłowo.";
        redirectAttributes.addFlashAttribute("message", message);
        logger.info("MVC finished saveFieldMappingToView");
        return "redirect:/arrayViews";
    }
    
    @RequestMapping(value = "/selectProductionSlot-{orderItemID}", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView selectProductionSlot(Model model, @PathVariable Integer orderItemID) {
        logger.info("MVC started selectProductionSlot");
        ModelAndView mv = new ModelAndView("selectProductionSlot");
        List<ProductionSlot> productionSlotList = productionSlotService.findAll();
        mv.addObject("productionSlotList", productionSlotList);
        mv.addObject("orderItemID", orderItemID);
        logger.info("MVC finished selectProductionSlot");
        return mv;
    } 
    
    @RequestMapping(value="/printOrderItemCard-{orderItemID}", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView printOrderCard(Model model, @PathVariable Integer orderItemID, @RequestParam("productionSlotID") Integer productionSlotID) {
        logger.info("MVC started printOrderCard");
        Date today = new Date();
       ModelAndView mv = new ModelAndView("printOrderItemCard");
       OrderItem orderItem = orderItemService.find(orderItemID);
       
       OrderStatus orderStatus = orderStatusService.findByName("W toku");
       orderItem.setorderStatus(orderStatus);
       orderItem.setorderStatusDate(today);
       orderItemService.update(orderItem);

       mv.addObject("orderItem", orderItem);
       ProductionSlot productionSlot = productionSlotService.find(productionSlotID);
       mv.addObject("productionSlot", productionSlot);
        logger.info("MVC finished printOrderCard");
       return mv;
    }
    
    @RequestMapping(value="/printOrderItemCards", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public ModelAndView printOrderCards(Model model, @RequestParam("productionSlotID") Integer productionSlotID, @RequestParam(value="orderItem")Integer[] orderItemIDs) {
        logger.info("MVC started printOrderCards");
        ModelAndView mv = new ModelAndView("printOrderItemCards");
       Date today = new Date();
       List<OrderItem> orderItemList = new ArrayList<>(0);
       for (Integer orderItemID : orderItemIDs) {
           logger.info("Sended to print orderID : " + orderItemID);
           orderItemList.add(orderItemService.find(orderItemID));
       }
       OrderStatus orderStatus = orderStatusService.findByName("W toku");
       Iterator<OrderItem> it = orderItemList.iterator();
       while (it.hasNext()) {
           OrderItem orderItem = it.next();
           orderItem.setorderStatus(orderStatus);
           orderItem.setorderStatusDate(today);
           orderItemService.update(orderItem);
       }

       mv.addObject("selectedOrders", orderItemList);
       ProductionSlot productionSlot = productionSlotService.find(productionSlotID);
       mv.addObject("productionSlot", productionSlot);
        logger.info("MVC finished printOrderCards");
       return mv;
    }
    
    @RequestMapping(value = "/printProductionLabel-{orderItemID}", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView printProductionLabel(Model model, @PathVariable Integer orderItemID) {
        logger.info("MVC started printProductionLabel");
        ModelAndView mv = new ModelAndView("pdforderLabelCard");
        // create some sample data
        OrderItem orderItem = orderItemService.find(orderItemID);
        logger.info("Sended to print label orderID : " + orderItemID);
        mv.addObject("orderItem", orderItem);
        // return a view which will be resolved by an PDF view resolver
        logger.info("MVC finished printProductionLabel");
        return mv;
    }
    
    @RequestMapping(value = "/printProductionLabels", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public ModelAndView printProductionLabels(Model model, @RequestParam(value="orderItemID")Integer[] orderItemIDs) {
        logger.info("MVC started printProductionLabels");
        ModelAndView mv = new ModelAndView("pdforderLabelCards");
        // create some sample data
        List<OrderItem> orderItemList = new ArrayList<>(0);
        for (Integer orderItemID : orderItemIDs) {
           logger.info("Sended to print label orderID : " + orderItemID);
           orderItemList.add(orderItemService.find(orderItemID));
        }
        mv.addObject("orderItemList", orderItemList);
        // return a view which will be resolved by an PDF view resolver
        logger.info("MVC finished printProductionLabels");
        return mv;
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        ModelAndView mv = new ModelAndView("logout");
        return mv;
    } 
    
    @RequestMapping(value = "/importOrderItemsSuccess", method = RequestMethod.POST,produces = "text/html; charset=utf-8")
    public String save(@ModelAttribute("uploadForm") FileUploadForm uploadForm,Model map) throws JSONException, IOException, ParseException {

        logger.info("MVC started save (importOrderItemsSuccess)");
        List<MultipartFile> files = uploadForm.getFiles();
        List<String> fileNames = new ArrayList<>();
        String importedFileContent = new String();
        List<OrderItem> importedOrders = new ArrayList<>();
        List<Field> fieldList = new ArrayList<>();
        List<Field> newFieldList = new ArrayList<>();
        Date today = new Date();
        
        OrderStatus orderStatus = orderStatusService.findByName("Nowe");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        
	if(null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) {
                String fileName = multipartFile.getOriginalFilename();
                fileNames.add(fileName);
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(), "UTF-8")); 
                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;
                
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);
                byte ptext[] = responseStrBuilder.toString().getBytes("UTF-16");
                String value = new String(ptext, "UTF-16");
                
                
                JSONObject importedJSON = new JSONObject(value);
                importedFileContent = responseStrBuilder.toString();
                int j = 1;
                // Loop in JSON for each Order
                Iterator<?> keys = importedJSON.keys();
                while( keys.hasNext() ) {
                    String keyOrderNumber = (String)keys.next();
                    if (!orderItemService.isInDbByOrderNumber(keyOrderNumber)) {
                    if ( importedJSON.get(keyOrderNumber) instanceof JSONArray) {
                            JSONArray importedJSONArray = importedJSON.getJSONArray(keyOrderNumber);
                            int itemsPerOrderQTY = importedJSONArray.length();

                            // Loop for each orderItem in one Order
                            for (int item=0; item<importedJSONArray.length(); item++) {
                                JSONObject jsonObject = importedJSONArray.getJSONObject(item);
                                // Setup new imported Order and save into DB
                                
                                
                                JSONObject j1 = new JSONObject();
                                int orderItemsQTY = 1;
                                if (jsonObject.has("0")) {
                                    j1 = jsonObject.getJSONObject("0");
                                    JSONObject j2 = jsonObject.getJSONObject("0");
                                    String qty = j2.getString("TEXT");
                                    if (qty.contains(":")) {
                                        qty = qty.substring(qty.lastIndexOf(":")+2);
                                    }
                                    if (qty.isEmpty()) {
                                        orderItemsQTY = 1;
                                    } else {
                                        orderItemsQTY = Integer.parseInt(qty);
                                    }
                                } else 
                                if (jsonObject.has("2000.")) {
                                    j1 = jsonObject.getJSONObject("2000.");  
                                    JSONObject j2 = jsonObject.getJSONObject("2000.");
                                    String qty = j2.getString("TEXT");
                                    if (qty.contains(":")) {
                                        qty = qty.substring(qty.lastIndexOf(":"+2));
                                    }
                                    if (qty.isEmpty()) {
                                        orderItemsQTY = 1;
                                    } else {
                                        orderItemsQTY = Integer.parseInt(qty);
                                    }
                                }
                                itemsPerOrderQTY = itemsPerOrderQTY+orderItemsQTY-1;
                            }
                            logger.info("For order number "+keyOrderNumber+" QTY items founded : "+itemsPerOrderQTY);
                            for (int item=0; item<importedJSONArray.length(); item++) {
                                JSONObject jsonObject = importedJSONArray.getJSONObject(item);
                                // Setup new imported Order and save into DB  
                                JSONObject j1 = new JSONObject();
                                int orderItemsQTY = 1;
                                if (jsonObject.has("0")) {
                                    j1 = jsonObject.getJSONObject("0");
                                    JSONObject j2 = jsonObject.getJSONObject("0");
                                    String qty = j2.getString("TEXT");
                                    if (qty.contains(":")) {
                                        qty = qty.substring(qty.lastIndexOf(":")+2);
                                    }
                                    if (qty.isEmpty()) {
                                        orderItemsQTY = 1;
                                    } else {
                                        orderItemsQTY = Integer.parseInt(qty);
                                    }
                                } else 
                                if (jsonObject.has("2000.")) {
                                    j1 = jsonObject.getJSONObject("2000.");  
                                    JSONObject j2 = jsonObject.getJSONObject("2000.");
                                    String qty = j2.getString("TEXT");
                                    if (qty.contains(":")) {
                                        qty = qty.substring(qty.lastIndexOf(":")+2);
                                    }
                                    if (qty.isEmpty()) {
                                        orderItemsQTY = 1;
                                    } else {
                                        orderItemsQTY = Integer.parseInt(qty);
                                    }
                                }
                                
                                for (int k=0; k<orderItemsQTY; k++) {
                                    
                                    OrderItem orderItem = new OrderItem();
                                    orderItem.setorderStatusDate(today);
                                    orderItem.setorderNumber(keyOrderNumber+"#"+j+"#"+itemsPerOrderQTY);
                                    logger.info("Importing order number "+keyOrderNumber+"#"+j+"#"+itemsPerOrderQTY+" item");
                                    orderItem.setorderStatus(orderStatus);
                                    orderItem.setorderItemName(j1.getString("LABEL"));
 
                                    Date date = null;
                                    if (jsonObject.has("1013")) {
                                        JSONObject j2 = jsonObject.getJSONObject("1013");
                                        date = formatter.parse(j2.getString("TEXT"));
                                    }
                                
                                    orderItem.setorderItemDueDate(date);
                                    String addedOrderID = orderItemService.saveNew(orderItem);
                                    importedOrders.add(orderItem);
                                
                                    // Prepare list of fields from DB to compare
                                    fieldList = fieldService.findAll();
                                
                                    // Find saved Order
                                    OrderItem insertedOrderItem = orderItemService.find(Integer.parseInt(addedOrderID));
                                
                                    // Loop for other fields imported from JSON
                                    Iterator<?> innerKeys = jsonObject.keys();
                                    while (innerKeys.hasNext()) {
                                        String innerKey = (String) innerKeys.next();
                                        if ("0".equals(innerKey) || "2000.".equals(innerKey))
                                        {} 
                                        else {

                                            OrderItemField orderItemField = new OrderItemField();
                                            Boolean result = fieldList.stream()
                                                .filter(field -> innerKey.equals(field.getFieldOriginID()))
                                                .findFirst().isPresent();
                                            if (result) {
                                                logger.info(keyOrderNumber+"-->"+innerKey+"=="); 
                                                JSONObject jObject = jsonObject.getJSONObject(innerKey);

                                                // Find saved Field
                                                Field foundedField= fieldService.findByfieldOriginID(innerKey);
                                                orderItemField.setfield(foundedField);
                                                orderItemField.setfieldText(jObject.getString("TEXT"));
                                                orderItemField.setorderItem(insertedOrderItem);
                                                orderItemFieldService.save(orderItemField);
                                            } else {
                                                logger.info(keyOrderNumber+"-->"+innerKey+"<>");
                                                JSONObject jObject = jsonObject.getJSONObject(innerKey);
                                                Field impField = new Field();
                                                impField.setFieldID(0);
                                                impField.setFieldLabel(jObject.getString("LABEL"));
                                                impField.setFieldOriginID(innerKey);
                                                logger.info(keyOrderNumber+"-->"+innerKey+"-->"+jObject.getString("LABEL"));
                                                String fieldValueID = "";
                                                if (!innerKey.contains("100")) {
                                                    fieldValueID = jObject.getString("VALUEID");
                                                }
                                                impField.setFieldValueID(fieldValueID);
                                                String addedFieldID = fieldService.saveNew(impField);
                                                // Find saved Field
                                                Field insertedField= fieldService.find(Integer.parseInt(addedFieldID));
                                                orderItemField.setfield(insertedField);
                                                orderItemField.setfieldText(jObject.getString("TEXT"));
                                                orderItemField.setorderItem(insertedOrderItem);
                                                orderItemFieldService.save(orderItemField);
                                                newFieldList.add(impField);
                                            }                                       
                                        }
                                    }
                                    j = j+1;
                                }
                            } 
                        }
                    }
            }   }
	}
        if (!newFieldList.isEmpty()) {
            map.addAttribute("newFieldList", newFieldList);
            String message = "W trakcie importu znaleziono nowe pola. Pamietj o ich zmapowaniu na wydruki!";
            map.addAttribute("message", message);
        }
        map.addAttribute("importedFileContent",importedFileContent);
        map.addAttribute("importedOrders",importedOrders);
	map.addAttribute("files", fileNames);
        logger.info("MVC finished save (importOrderItemsSuccess)");
	return "importOrderItemsSuccess";
    }
    
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
