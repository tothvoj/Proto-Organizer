package com.globallogic.protoorganizer.model;

public enum DeviceUsageActionEnum {

	Added(0),
	
	FromVault(1),
	ToVault(2),
	
	MovingToSomeone(3),
	MovingToMe(4),
	MovedTo(5),
	
	TakenOffSite(6),
	TakenHome(7),
	
	ReturnedToCustomer(8),
	
	Lost(9),
	Stolen(10),
	LocationUnknown(11);
	
	private int numVal;
	
	DeviceUsageActionEnum() {
        this.numVal = -1;
    }
	
	DeviceUsageActionEnum(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
	
}
