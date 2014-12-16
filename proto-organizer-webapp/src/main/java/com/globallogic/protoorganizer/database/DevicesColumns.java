package com.globallogic.protoorganizer.database;

public interface DevicesColumns {
    String ID = "id";
    String DEVICE = "device";
    String IMEI = "imei";
    String STATUS = "status";
    String PROJECT_ID = "project_id";
    String OWNER_ID = "owner_id";
    String REASON = "reason"; 
    String LAST_MODIFIED_BY = "last_modified_by";  
    String EMAIL = "email";
    String PLATFORM_ID = "platform_id";
    String DATE = "date";
    String ORIGIN = "origin";    
    
    // additional in DevicesView
    String PROJECT_NAME = "project_name";
    String PLATFORM_NAME = "platform_name";
    String PLATFORM_VERSION = "platform_version";
    
    String OWNER_FIRST_NAME = "owner_first_name";
    String OWNER_LAST_NAME = "owner_last_name";
    String MODIFIERS_FIRST_NAME = "modifiers_first_name";
    String MODIFIERS_LAST_NAME = "modifiers_last_name";
    String ISOWNERAPERSON = "is_owner_person";
}
