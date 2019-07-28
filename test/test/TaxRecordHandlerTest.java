package test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import daos.impl.TaxRecordDAO;
import entities.TaxRecord;
import service.impl.TaxRecordHandler;

import org.springframework.orm.hibernate4.HibernateTemplate;


public class TaxRecordHandlerTest {
	
	@SuppressWarnings("serial")
	public class Sobj implements Serializable{
		
	}
	
	TaxRecordDAO taxRecordDao;
	HibernateTemplate template;
	TaxRecordHandler taxRecordHandler;
	
	@Before
	public void initialize(){
		taxRecordHandler=new TaxRecordHandler();
		 taxRecordDao=new TaxRecordDAO();
		 template=Mockito.mock(HibernateTemplate.class);
		taxRecordDao.setTemplate(template);
		taxRecordHandler.setTaxRecordDao(taxRecordDao);
	}
	
	@Test
	public void testCalculateTotalTax(){
		TaxRecordHandler trh=new TaxRecordHandler();
		float uav=(float) 2.5;
		String result= trh.calculateTotalTax(uav,1111,2111,11);
		Assert.assertEquals("75020.0",result);
	}
	
	@Test
	public void testSaveTaxRecord(){
		TaxRecord taxRecord=new TaxRecord();
		Mockito.when(template.save(Mockito.any(TaxRecord.class))).thenReturn(new Sobj());
		taxRecordHandler.saveTaxRecord(taxRecord);
		Mockito.verify(template, Mockito.times(1)).save(
                Mockito.any(TaxRecord.class));
	}
	
	@Test
	public void testGetById(){
		TaxRecord taxRecord=new TaxRecord();
		Mockito.when(template.get(TaxRecord.class,1)).thenReturn(taxRecord);
		taxRecordHandler.getById(1);
		Mockito.verify(template, Mockito.times(1)).get(
				TaxRecord.class,1);
	}
	@Test
	public void testGetAllTaxRecords(){
		List<TaxRecord> al=new ArrayList<TaxRecord>();
		TaxRecord taxRecord=new TaxRecord();
		Mockito.when(template.loadAll(TaxRecord.class)).thenReturn(al);
		taxRecordHandler.getAllTaxRecords();
		Mockito.verify(template, Mockito.times(1)).loadAll(TaxRecord.class);
	}
	
	@Test
	public void testGetUAVException(){
		try{
		TaxRecordDAO taxRecordDao1=Mockito.mock(TaxRecordDAO.class);
		Mockito.doThrow(RuntimeException.class).when(taxRecordDao1.getUav(Mockito.anyString(),Mockito.anyString(),Mockito.anyString()));
		Mockito.verify(taxRecordDao1, Mockito.times(1)).getUav(Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
		Assert.fail();
		}
		catch(RuntimeException re){
			 Assert.assertTrue(re instanceof RuntimeException);
		}
	}
	
}
