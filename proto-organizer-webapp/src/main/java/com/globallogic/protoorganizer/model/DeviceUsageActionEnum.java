package com.globallogic.protoorganizer.model;

public enum DeviceUsageActionEnum {

	Added(0),
	
	FromVault(1),
	ToVault(2),
	
	MovingToSomeone(3),
	MovingToMe(4),
	
	TakenOffSite(5),
	TakenHome,
	
	ReturnedToCustomer(6),
	
	Lost(7),
	Stolen(8),
	LocationUnknown(9);
	
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
