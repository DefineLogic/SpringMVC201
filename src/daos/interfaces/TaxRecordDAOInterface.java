package daos.interfaces;

import java.util.List;

import entities.TaxRecord;
import entities.Zones;

public interface TaxRecordDAOInterface {

	public void saveTaxRecord(TaxRecord t);

	public TaxRecord getById(int id);

	public List<TaxRecord> getAllTaxRecords();

	public float getUav(String desc, String status, String zone);

	Zones getZoneById(int id);

}
