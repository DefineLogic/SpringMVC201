package controllers;


import java.util.HashSet;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entities.TaxRecord;
import entities.Zones;
import service.impl.TaxRecordHandler;

@Controller
public class SelfAssessmentController {
	
	 private Logger logger = Logger.getLogger(SelfAssessmentController.class);

	@Autowired(required = true)
	public TaxRecordHandler taxRecordHandler;

	@RequestMapping("/newtaxrecord")
	public ModelAndView openTaxRecordPage() {
		logger.debug("Entering openTaxRecordPage method.");
		ModelAndView mav = new ModelAndView("propertytaxformpage");
		mav.addObject("command", new TaxRecord());
		logger.info("Exiting openTaxRecordPage method.");
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveTaxRecord(@Valid @ModelAttribute("taxRecord1") TaxRecord taxRecord, BindingResult result,@RequestParam String action
			) {
		logger.info("Entering saveTaxRecord method.");
		ModelAndView mav = new ModelAndView("savesuccesspage");
		if (action.equals("Cancel")) {
			logger.info("Tax details is cancelled.");
			mav.addObject("saveSuccessMessage", "Cancelled.You can fill form Again.");
			return mav;
		} 
		else if (result.hasErrors()) {
			ModelAndView mav1 = new ModelAndView("propertytaxformpage");
			mav1.addObject("command", new TaxRecord());
			return mav1;
		} else if (action.equals("Pay Tax")) {
			/*HashSet<TaxRecord> hs1=new HashSet<TaxRecord>();
			hs1.add(taxRecord);
			taxRecord.getZones().setTaxRecord(hs1);
			
			taxRecord.getDescriptionOfProperty().setTaxRecord(hs1);*/
//			Zones zoneById = taxRecordHandler.getZoneById(taxRecord.getZones().getZ_id());
//			taxRecord.setZones(zoneById);
			taxRecordHandler.saveTaxRecord(taxRecord);
			logger.info("Tax details is saved.");
			mav.addObject("saveSuccessMessage", "Tax details are saved successfully.");
			return mav;
		}else
			return mav;
	}
	
	@ResponseBody 
	@RequestMapping(value="/calculate",method = RequestMethod.POST)
	public String calculateTaxTotal(@RequestParam String zone,@RequestParam String status,
			@RequestParam String desc,@RequestParam Integer assessyear,
			@RequestParam Integer constructedyear, @RequestParam Integer area){
		logger.info("Entering calculateTaxTotal method.");
		float uav = 0;
		try{
		uav = taxRecordHandler.getUav(desc, status, zone);
		}catch(RuntimeException e){
			throw e;
		}
		
		logger.info("Exiting calculateTaxTotal method.");
		return taxRecordHandler.calculateTotalTax(uav, assessyear, constructedyear, area);
	}
}