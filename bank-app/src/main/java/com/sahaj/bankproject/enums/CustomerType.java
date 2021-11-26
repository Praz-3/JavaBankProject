package com.sahaj.bankproject.enums;

import com.sahaj.bankproject.exceptions.InvalidCustomerTypeException;

public enum CustomerType {
	SILVER, GOLD, DIAMOND;
	
	public static CustomerType intToType(int input) throws InvalidCustomerTypeException{
		switch(input) {
		case 1: return SILVER;
		case 2: return GOLD;
		case 3: return DIAMOND;
		default: throw new InvalidCustomerTypeException(input + " is not a valid Customer Type, must be among 1, 2 or 3");
		}
	}

    public static int typeToInt(CustomerType c) {
    	switch(c) {
    	case SILVER: return 1;
    	case GOLD: return 2;
    	case DIAMOND: return 3;
    	}
    	return 0;
    }


}
