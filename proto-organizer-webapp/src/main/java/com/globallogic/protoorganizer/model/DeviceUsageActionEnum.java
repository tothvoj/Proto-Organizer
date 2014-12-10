package com.globallogic.protoorganizer.model;

public enum DeviceUsageActionEnum {

	Added(0),
	
	FromVault(1),
	ToVault(2),
	
	MovingToSomeone(3),
	MovingToMe(4),
	
	TakenOffSite(5),
	TakenHome(6),
	
	ReturnedToCustomer(7),
	
	Lost(8),
	Stolen(9),
	LocationUnknown(10);
	
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
