package com.allmsi.auth.model.po;

public class SystemId {
	
    private String id;

    private String systemName;

    private Integer del;
    
    public SystemId() {
    	
    }
   
    public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName == null ? null : systemName.trim();
    }
}