package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


@Entity
public class TaxRecord implements Comparable<TaxRecord>{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	private int yearOfAssessment;
	@NotBlank
	private String ownerName;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String propertyAddress;
//	@NotNull
	@ManyToOne(cascade=CascadeType.ALL)
    private Zones zones;
//	@NotNull
	@ManyToOne(cascade=CascadeType.ALL)
    private DescriptionOfProperty  descriptionOfProperty;
    
	@NotBlank
	private String status;
	@NotNull
	@Min(value = 1)
    @Max(value = 2019)
	private int buildingConstructedYear;
	@NotNull
	private int builtUpArea;
	@NotNull
	private double totalTaxPayable;

	public int getYearOfAssessment() {
		return yearOfAssessment;
	}

	public void setYearOfAssessment(int yearOfAssessment) {
		this.yearOfAssessment = yearOfAssessment;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public Zones getZones() {
		return zones;
	}

	public void setZones(Zones zones) {
		this.zones = zones;
	}
	
	public DescriptionOfProperty getDescriptionOfProperty() {
		return descriptionOfProperty;
	}

	public void setDescriptionOfProperty(DescriptionOfProperty descriptionOfProperty) {
		this.descriptionOfProperty = descriptionOfProperty;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBuildingConstructedYear() {
		return buildingConstructedYear;
	}

	public void setBuildingConstructedYear(int buildingConstructedYear) {
		this.buildingConstructedYear = buildingConstructedYear;
	}

	public int getBuiltUpArea() {
		return builtUpArea;
	}

	public void setBuiltUpArea(int builtUpArea) {
		this.builtUpArea = builtUpArea;
	}

	public double getTotalTaxPayable() {
		return totalTaxPayable;
	}

	public void setTotalTaxPayable(double totalTaxPayable) {
		this.totalTaxPayable = totalTaxPayable;
	}

	@Override
	public String toString() {
		return "TaxRecord [id=" + id + ", yearOfAssesment=" + yearOfAssessment + ", ownerName=" + ownerName + ", email="
				+ email + ", address=" + propertyAddress + ", zone=" + zones + ", descriptionOfProperty="
				+ descriptionOfProperty + ", status=" + status + ", buildingConstructedYear=" + buildingConstructedYear
				+ ", builtUpArea=" + builtUpArea + ", totalTaxPayable=" + totalTaxPayable + "]";
	}

	 @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result
	                + ((id == null) ? 0 : id.hashCode());
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
	        final TaxRecord other = (TaxRecord) obj;
	        if (id == null) {
	            if (other.id != null)
	                return false;
	        } else if (!id.equals(other.id))
	            return false;
	        return true;
	    }

	@Override
	public int compareTo(TaxRecord o) {
		return o.id-id;
	}
}
