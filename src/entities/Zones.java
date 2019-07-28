package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

@Entity
public class Zones implements Comparable<Zones>{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int z_id;
    private String zoneType;
    @OneToMany(mappedBy="zones")
    Set<TaxRecord> taxRecord = new HashSet<TaxRecord>();
      
    public Set<TaxRecord> getTaxRecord() {
		return taxRecord;
	}

	public void setTaxRecord(Set<TaxRecord> taxRecord) {
		this.taxRecord = taxRecord;
	}
	
	public Zones(){}
	
    public Zones(String zoneType) {
        this.zoneType = zoneType;
    }
    
	public int getZ_id() {
		return z_id;
	}

	public void setZ_id(int z_id) {
		this.z_id = z_id;
	}
	
	public String getZoneType() {
		return zoneType;
	}
	    
    public void setZoneType(String zoneType) {
		this.zoneType = zoneType;
	}
	
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((zoneType == null) ? 0 : zoneType.hashCode());
        return result;
    }
 @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Zones other = (Zones) obj;
        if (zoneType == null) {
            if (other.zoneType != null)
                return false;
        } else if (!zoneType.equals(other.zoneType))
            return false;
        return true;
    }

@Override
public int compareTo(Zones o) {
	return o.z_id-z_id;
}
}
