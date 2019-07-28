package daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import daos.interfaces.TaxRecordDAOInterface;
import entities.DescriptionOfProperty;
import entities.TaxRecord;
import entities.Zones;

@Repository
public class TaxRecordDAO implements TaxRecordDAOInterface{  
	
	@Autowired(required=true)
	HibernateTemplate template;  
	
	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	}  
	
	//method to save tax record  
	@Override
	public void saveTaxRecord(TaxRecord t){ 
		Zones z=(Zones) template.get(Zones.class,Integer.valueOf(t.getZones().getZoneType()));
		t.setZones(z);
		DescriptionOfProperty dop=(DescriptionOfProperty)template.get(DescriptionOfProperty.class, Integer.valueOf(t.getDescriptionOfProperty().getDescription()));
		t.setDescriptionOfProperty(dop);
	    template.save(t);  
	}  
	   
	//method to return one tax record of given id 
	@Override
	public TaxRecord getById(int id){  
		TaxRecord t=(TaxRecord)template.get(TaxRecord.class,id);  
	    return t;  
	} 
	
	@Override
	public Zones getZoneById(int id){
		Zones z=(Zones) template.get(Zones.class,id);
		return z;
	}
	
	//method to return all tax record  
	@Override
	public List<TaxRecord> getAllTaxRecords(){  
	    List<TaxRecord> list=new ArrayList<TaxRecord>();  
	    list=template.loadAll(TaxRecord.class);  
	    return list;  
	}  
	
	//method returning uav
	@Override
	public float getUav(String desc, String status, String zone) {
		Session session;

		try {
			session = template.getSessionFactory().getCurrentSession();
		} catch (HibernateException e) {
			session = template.getSessionFactory().openSession();
		}
		Query q = session.createQuery("select uav from UAV"
				+ " where description=:description and status=:status and zone =:zone");
		q.setParameter("description", desc);
		q.setParameter("status", status);
		q.setParameter("zone", zone);
		Object obj=q.uniqueResult();
		if(obj==null)
			throw new RuntimeException("UAV not available for description:"+desc+" ,status:"+status+"zone:"+zone+".");
		float uav = (float) obj;
		return uav;

	}
}
