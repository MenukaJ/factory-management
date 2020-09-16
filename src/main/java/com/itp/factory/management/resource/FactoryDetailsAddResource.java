package com.itp.factory.management.resource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itp.factory.management.enums.CommonStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class FactoryDetailsAddResource {

    @NotBlank(message = "{common.not-null}")
//    @Size(max = 20, message = "{common-name.size}")
    private String company_name;

    @NotBlank(message = "{common.not-null}")
//    @Size(max = 70, message = "{common-name.size}")
    private String address;

    private String tenantId;

    public String getName() {
        return company_name;
    }

    public void setName(String name) {
        this.company_name = name;
    }

    public String getStatus() {
        return address;
    }

    public void setStatus(String status) {
        this.address = status;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
