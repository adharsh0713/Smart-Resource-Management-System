package com.smartresourcemanagement.model;

public class DigitalResource extends Resource {

    private String licenseKey;
    private String version;

    public DigitalResource(String id, String name, String licenseKey, String version) {
        super(id, name);
        this.licenseKey = licenseKey;
        this.version = version;
    }

    public String getLicenseKey() { return licenseKey; }
    public String getVersion() { return version; }

    public void setLicenseKey(String licenseKey) { this.licenseKey = licenseKey; }
    public void setVersion(String version) { this.version = version; }

    @Override
    public String getResourceDetails() {
        return "DigitalResource: ID=" + getId() +
               ", Name=" + getName() +
               ", LicenseKey=" + licenseKey +
               ", Version=" + version +
               ", CreatedOn=" + getCreatedOn();
    }
}
