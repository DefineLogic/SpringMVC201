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

@Entity
public class DescriptionOfProperty implements Comparable<DescriptionOfProperty>{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int d_id;
    private String description;
    @OneToMany(mappedBy="descriptionOfProperty")
    Set<TaxRecord> taxRecord = new HashSet<TaxRecord>();
    
    public Set<TaxRecord> getTaxRecord() {
		return taxRecord;
	}

	public void setTaxRecord(Set<TaxRecord> taxRecord) {
		this.taxRecord = taxRecord;
	}
	
	public DescriptionOfProperty(){}
	
	public DescriptionOfProperty(String description) {
        this.description = description;
    }
    
	public int getD_id() {
		return d_id;
	}

	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
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
        final DescriptionOfProperty other = (DescriptionOfProperty) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
    }

@Override
public int compareTo(DescriptionOfProperty o) {
	return o.d_id-d_id;
}
    
    
}
