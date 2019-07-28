package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import daos.impl.TaxRecordDAO;
import entities.TaxRecord;
import entities.Zones;
import service.interfaces.TaxRecordHandlerInterface;

@Service
public class TaxRecordHandler implements TaxRecordHandlerInterface{
	
	@Autowired(required = true)
	public TaxRecordDAO taxRecordDao;
	
	public TaxRecordDAO getTaxRecordDao() {
		return taxRecordDao;
	}

	public void setTaxRecordDao(TaxRecordDAO taxRecordDao) {
		this.taxRecordDao = taxRecordDao;
	}
	
	@Override
	@Transactional
	public Zones getZoneById(int i){
		return taxRecordDao.getZoneById(i);
	}

	@Override
	@Transactional
	public void saveTaxRecord(TaxRecord t) {
		taxRecordDao.saveTaxRecord(t);		
	}

	@Override
	@Transactional
	public TaxRecord getById(int id) {
		return taxRecordDao.getById(id);
	}

	@Override
	@Transactional
	public List<TaxRecord> getAllTaxRecords() {
		return taxRecordDao.getAllTaxRecords();
	}

	@Override
	@Transactional
	public float getUav(String desc, String status, String zone) {
		return taxRecordDao.getUav(desc, status, zone);
	}

	@Override
	@Transactional
	public String calculateTotalTax(float uav, Integer assessyear, Integer constructedyear, Integer area) {
		double Total_5 = 0;
		int yearDiff = assessyear - constructedyear;
		int deprec = 0;
		if (yearDiff >= 60) {
			deprec = 60;
		} else {
			deprec = yearDiff;
		}
		double Total_1 = area * uav * 10;
		double Total_2 = (100 - deprec) * Total_1;
		double Total_3 = Total_2 * 0.2;
		double Total_4 = Total_3 * 0.24;
		Total_5 = Total_3 + Total_4;
		return Total_5 + "";
	}

}
