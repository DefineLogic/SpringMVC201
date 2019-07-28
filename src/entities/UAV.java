package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unitareavaluetable")
public class UAV implements Comparable<UAV>{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String description;
	private String status;
	private String zone;
	private float uav;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public float getUav() {
		return uav;
	}
	public void setUav(float uav) {
		this.uav = uav;
	}
	
	 @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result
	                + ((description == null) ? 0 : description.hashCode());
	        result = prime * result
	                + ((status == null) ? 0 : status.hashCode());
	        result = prime * result
	                + ((zone == null) ? 0 : zone.hashCode());
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
	        final UAV other = (UAV) obj;
	        if (description == null) {
	            if (other.description != null)
	                return false;
	        } else if (!description.equals(other.description))
	            return false;
	        if (status == null) {
	            if (other.status != null)
	                return false;
	        } else if (!status.equals(other.status))
	            return false;
	        if (zone == null) {
	            if (other.zone != null)
	                return false;
	        } else if (!zone.equals(other.zone))
	            return false;
	        return true;
	    }
	@Override
	public int compareTo(UAV o) {
		return o.id-id;
	}
	
	

}
