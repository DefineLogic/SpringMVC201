package controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import daos.impl.TaxRecordDAO;
import entities.TaxRecord;

@Controller
public class ViewController {
	
	@Autowired(required=true)
	public TaxRecordDAO taxRecordDAO;

	@RequestMapping("/view")
	public ModelAndView viewZonalReport(){
		List<TaxRecord> list=taxRecordDAO.getAllTaxRecords();
		for(TaxRecord taxrecord:list)
			System.out.println(taxrecord);
		return new ModelAndView("viewzonalwisereportpage","list",list);
	}
	
}