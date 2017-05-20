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
import com.app.gpo.model.FieldOnMainScreenForm;
import com.app.gpo.model.FileUploadForm;
import com.app.gpo.model.OrderItem;
import com.app.gpo.model.OrderItemField;
import com.app.gpo.model.OrderStatus;
import com.app.gpo.model.ProductionSlot;
import com.app.gpo.services.FieldMappingService;
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
        model.addAttribute("user", getPrincipal());
        return "/shared/menu";
        
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "menu")
    public String getMenuGet(Model model) {
        model.addAttribute("user", getPrincipal());
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
        ModelAndView mv = new ModelAndView("index");
        List<OrderItem> orderItemList = orderItemService.findAll();
        mv.addObject("orderItemList", orderItemList);
        List<Field> fieldsForTable = fieldService.findAllForMainScreen();
        mv.addObject("fieldsForTable", fieldsForTable);
        return mv;
    } 
    
    @RequestMapping(value="/index", method = RequestMethod.POST)
    public ModelAndView showIndexPost() {
        ModelAndView mv = new ModelAndView("index");
        List<OrderItem> orderItemList = orderItemService.findAll();
        mv.addObject("orderItemList", orderItemList);
        List<Field> fieldsForTable = fieldService.findAllForMainScreen();
        mv.addObject("fieldsForTable", fieldsForTable);
        return mv;
    } 
    
    @RequestMapping(value="/index_1", method = RequestMethod.GET)
    public ModelAndView showIndextest() {
        ModelAndView mv = new ModelAndView("index_1");
        List<OrderItem> orderItemList = orderItemService.findAll();
        mv.addObject("orderItemList", orderItemList);
        List<Field> fieldsForTable = fieldService.findAllForMainScreen();
        mv.addObject("fieldsForTable", fieldsForTable);
        return mv;
    } 
    
    @RequestMapping(value = "/selectProductionSlotMultiple", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public ModelAndView selectProductionSlotMultiple(Model model,@RequestParam(value="orderItemID")Integer[] checkboxList) {
        ModelAndView mv = new ModelAndView("selectProductionSlotMultiple");
        List<ProductionSlot> productionSlotList = productionSlotService.findAll(); 
        List<OrderItem> orderItemList = new ArrayList<>(0);

       for (Integer checkboxList1 : checkboxList) {
           logger.info("Selected to print orderID : " + checkboxList1);
           orderItemList.add(orderItemService.find(checkboxList1));
       }
        mv.addObject("productionSlotList", productionSlotList);
        mv.addObject("ordersList", orderItemList);
        return mv;
    } 
    
    @RequestMapping(value = "/importOrderItems", method = RequestMethod.GET)
	public String showImportOrderItems() {
		return "importOrderItems";
	}

    
    @RequestMapping(value={ "/editOrderItem-{orderItemID}" }, method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView editOrderItem (Model model, @PathVariable Integer orderItemID) {
        OrderItem orderItem = orderItemService.find(orderItemID);
        List<OrderStatus> orderStatusList = orderStatusService.findAll();
        ModelAndView mv = new ModelAndView("editOrderItem");
        mv.addObject("orderItem", orderItem);
        mv.addObject("orderStatusList", orderStatusList);
        return mv;
    }
    
    @RequestMapping(value={ "/viewOrderItem-{orderItemID}" }, method = RequestMethod.GET)
    public ModelAndView viewOrderItem (Model model, @PathVariable Integer orderItemID) {
        OrderItem orderItem = orderItemService.find(orderItemID);
        List<OrderStatus> orderStatusList = orderStatusService.findAll();
        ModelAndView mv = new ModelAndView("viewOrderItem");
        mv.addObject("orderItem", orderItem);
        mv.addObject("orderStatusList", orderStatusList);
        return mv;
    }
    
    @RequestMapping(value = { "/deleteOrderItem-{orderItemID}" }, method = RequestMethod.GET)
    public String deleteOrderItem(@PathVariable Integer orderItemID,Model model,RedirectAttributes redirectAttributes) {
        String message;
        orderItemService.delete(orderItemID);
        message = "Pozycja zamówienia została poprawnie usunięta." ;
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/index";
    }
    
    @RequestMapping(value="/deleteOrderItems", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String deleteOrderItems(Model model, @RequestParam(value="orderItemID")Integer[] orderItemIDs,RedirectAttributes redirectAttributes) {

       String message = "Usunięto pozycje zamówień :: "+orderItemIDs.length+"sztuk(i).";
       
       for (Integer id : orderItemIDs) {
            logger.info("Sended to delete orderID : " + id);
            orderItemService.delete(id);
       }
       
       redirectAttributes.addFlashAttribute("message", message);
       return "redirect:/index";
    }
    
    @RequestMapping(value="/statusChangeForOrderItems", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public ModelAndView statusChangeForOrderItems(Model model, @RequestParam(value="orderItemID")Integer[] orderItemIDs) {
       ModelAndView mv = new ModelAndView("statusChangeForOrderItems");
       
       List<OrderItem> orderItemList = new ArrayList<>(0);
       for (Integer orderItemID : orderItemIDs) {
           logger.info("Sended to change status orderID : " + orderItemID);
           orderItemList.add(orderItemService.find(orderItemID));
       }
       mv.addObject("orderStatuses", orderStatusService.findAll());
       mv.addObject("orderItemList", orderItemList);

       return mv;
    }
    
    @RequestMapping(value="saveStatusChangeForOrderItems", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String saveStatusChangeForOrderItems(Model model, @RequestParam(value="orderItemID")Integer[] orderItemIDs, @RequestParam(value="orderStatus")Integer orderStatusID, RedirectAttributes redirectAttributes) {
       
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
       return "redirect:/index";
    }
    
    @RequestMapping(value="/updateOrderItem", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
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
                message = "Pozycja zamówienia ("+orderItem.getorderNumber()+")została poprawnie dodana." ;
            } else {
                orderItem.setorderStatusDate(today);
                orderItemService.update(orderItem);
                message = "Pozycja zamówienia ("+orderItem.getorderNumber()+") została poprawnie zaktualizowana." ;
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
    
    @RequestMapping(value="/newProductionSlot", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView newProductionSlot() {
        ModelAndView mv = new ModelAndView("newProductionSlot");
        return mv;
    }
    
    @RequestMapping(value = { "/editProductionSlot-{productionSlotID}" }, method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView editProductionSlot(Model model, @PathVariable Integer productionSlotID) {
        ProductionSlot productionSlot = productionSlotService.find(productionSlotID);
        ModelAndView mv = new ModelAndView("editProductionSlot");
        mv.addObject("productionSlot", productionSlot);
        return mv;
    }
            
    @RequestMapping(value="/updateProductionSlot", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String updateProductionSlot(@ModelAttribute ProductionSlot productionSlot,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
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
        return "redirect:/productionSlots";
    }
    
    @RequestMapping(value = { "/deleteProductionSlot-{productionSlotID}" }, method = RequestMethod.GET)
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
    
    @RequestMapping(value="/fieldOnMainScreen", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView showFields() {
        ModelAndView mv = new ModelAndView("fieldOnMainScreen");
        FieldOnMainScreenForm fieldOnMainScreenForm = new FieldOnMainScreenForm(); 
        fieldOnMainScreenForm.setfieldList(fieldService.findAll());
        mv.addObject("fieldOnMainScreenForm", fieldOnMainScreenForm);
        return mv;
    } 
    
    @RequestMapping(value="/fieldOnMainScreen", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public ModelAndView saveFields(@ModelAttribute("fieldOnMainScreenForm") FieldOnMainScreenForm fieldOnMainScreenForm) {
        
        List<Field> fieldListToSave = fieldOnMainScreenForm.getfieldList();
        logger.info(fieldOnMainScreenForm);
        logger.info(fieldOnMainScreenForm.getfieldList());
        for (Field field : fieldListToSave) {
            logger.info(field.getFieldID()+" "+field.getFieldOriginID()+" "+field.getFieldValueID()+" "+field.getFieldLabel());
            fieldService.update(field);
        }
        
        ModelAndView mv = new ModelAndView("fieldOnMainScreen");
        mv.addObject("fieldOnMainScreenForm", fieldService.findAll());
        return mv;
    } 
    
    
    @RequestMapping(value="/editFieldMapping-{productionSlotID}", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
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
    
    @RequestMapping(value="/deleteFieldMapping-{productionSlotID}", method = RequestMethod.GET)
    public String deletefieldMapping(RedirectAttributes redirectAttributes, @PathVariable Integer productionSlotID) {
        fieldMappingService.deleteByProductionSlotID(productionSlotID);
        String message = "Mapowanie pól zostało wyczyszczone prawidłowo.";
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/fieldMappings";
    } 
    
    @RequestMapping(value={ "saveFieldMapping" }, method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String saveFieldMapping (@RequestParam("fieldMappingString") String getFormString,@RequestParam("productionSlotID") Integer productionSlotID, RedirectAttributes redirectAttributes) throws JSONException {
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
        return "redirect:/fieldMappings";
    }
    
    @RequestMapping(value = "/selectProductionSlot-{orderItemID}", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView selectProductionSlot(Model model, @PathVariable Integer orderItemID) {
        ModelAndView mv = new ModelAndView("selectProductionSlot");
        List<ProductionSlot> productionSlotList = productionSlotService.findAll();
        mv.addObject("productionSlotList", productionSlotList);
        mv.addObject("orderItemID", orderItemID);
        return mv;
    } 
    
    
    @RequestMapping(value="/printOrderItemCard-{orderItemID}", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView printOrderCard(Model model, @PathVariable Integer orderItemID, @RequestParam("productionSlotID") Integer productionSlotID) {
       ModelAndView mv = new ModelAndView("printOrderItemCard");
       OrderItem orderItem = orderItemService.find(orderItemID);
       
       OrderStatus orderStatus = orderStatusService.findByName("W toku");
       orderItem.setorderStatus(orderStatus);
       orderItemService.update(orderItem);

       
       mv.addObject("orderItem", orderItem);
       ProductionSlot productionSlot = productionSlotService.find(productionSlotID);
       mv.addObject("productionSlot", productionSlot);
       return mv;
    }
    
    @RequestMapping(value="/printOrderItemCards", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public ModelAndView printOrderCards(Model model, @RequestParam("productionSlotID") Integer productionSlotID, @RequestParam(value="orderItem")Integer[] orderItemIDs) {
       ModelAndView mv = new ModelAndView("printOrderItemCards");
       
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
           orderItemService.update(orderItem);
       }
       
       
       
       mv.addObject("selectedOrders", orderItemList);
       ProductionSlot productionSlot = productionSlotService.find(productionSlotID);
       mv.addObject("productionSlot", productionSlot);
       return mv;
    }
    
    @RequestMapping(value = "/printProductionLabel-{orderItemID}", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public ModelAndView printProductionLabel(Model model, @PathVariable Integer orderItemID) {
        ModelAndView mv = new ModelAndView("pdforderLabelCard");
        // create some sample data
        OrderItem orderItem = orderItemService.find(orderItemID);
        logger.info("Sended to print label orderID : " + orderItemID);
        mv.addObject("orderItem", orderItem);
        // return a view which will be resolved by an PDF view resolver
        return mv;
    }
    
    @RequestMapping(value = "/printProductionLabels", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public ModelAndView printProductionLabels(Model model, @RequestParam(value="orderItemID")Integer[] orderItemIDs) {
        ModelAndView mv = new ModelAndView("pdforderLabelCards");
        // create some sample data
        List<OrderItem> orderItemList = new ArrayList<>(0);
        for (Integer orderItemID : orderItemIDs) {
           logger.info("Sended to print label orderID : " + orderItemID);
           orderItemList.add(orderItemService.find(orderItemID));
        }
        mv.addObject("orderItemList", orderItemList);
        // return a view which will be resolved by an PDF view resolver
        return mv;
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        ModelAndView mv = new ModelAndView("logout");
        return mv;
    } 
    
    @RequestMapping(value = "/importOrderItemsSuccess", method = RequestMethod.POST,produces = "text/html; charset=utf-8")
    public String save(@ModelAttribute("uploadForm") FileUploadForm uploadForm,Model map) throws JSONException, IOException, ParseException {
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
                // Loop in JSON for each Order
                Iterator<?> keys = importedJSON.keys();
                while( keys.hasNext() ) {
                    String keyOrderNumber = (String)keys.next();
                    if (!orderItemService.isInDbByOrderNumber(keyOrderNumber)) {
                    if ( importedJSON.get(keyOrderNumber) instanceof JSONArray) {
                            JSONArray importedJSONArray = importedJSON.getJSONArray(keyOrderNumber);
                            logger.info("For order number "+keyOrderNumber+" QTY items founded : "+importedJSONArray.length());
                            // Loop for each orderItem in one Order
                            for (int item=0; item<importedJSONArray.length(); item++) {
                                JSONObject jsonObject = importedJSONArray.getJSONObject(item);
                                // Setup new imported Order and save into DB
                                int j = item+1;
                                OrderItem orderItem = new OrderItem();
                                orderItem.setorderStatusDate(today);
                                orderItem.setorderNumber(keyOrderNumber+"#"+j+"#"+importedJSONArray.length());
                                logger.info("Importing order number "+keyOrderNumber+"#"+j+"#"+importedJSONArray.length()+" item");
                                orderItem.setorderStatus(orderStatus);
                                JSONObject j1 = jsonObject.getJSONObject("0");
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
                                    if ("0".equals(innerKey) || "1013".equals(innerKey) || "1000".equals(innerKey))
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
