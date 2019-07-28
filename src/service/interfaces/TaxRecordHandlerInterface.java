package service.interfaces;

import java.util.List;

import entities.TaxRecord;
import entities.Zones;

public interface TaxRecordHandlerInterface {

	public void saveTaxRecord(TaxRecord t);

	public TaxRecord getById(int id);

	public List<TaxRecord> getAllTaxRecords();

	public float getUav(String desc, String status, String zone);
	
	public String calculateTotalTax(float uav, Integer assessyear, Integer constructedyear, Integer area);

	Zones getZoneById(int i);
}
