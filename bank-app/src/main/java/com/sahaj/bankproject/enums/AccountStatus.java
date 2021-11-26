package com.sahaj.bankproject.enums;

import com.sahaj.bankproject.exceptions.InvalidAccountStatusException;

public enum AccountStatus {
	ACTIVE, CLOSED, BLOCKED, BLACKLISTED;
	
	public static AccountStatus intToType(int input) throws InvalidAccountStatusException{
		switch(input) {
		case 1: return AccountStatus.ACTIVE;
		case 2: return AccountStatus.CLOSED;
		case 3: return AccountStatus.BLOCKED;
		case 4: return AccountStatus.BLACKLISTED;
		default: throw new InvalidAccountStatusException(input + " is not a valid Account Status, must be among 1, 2, 3 or 4");
		}
	}

    public static int typeToInt(AccountStatus s) {
    	switch(s) {
    	case ACTIVE: return 1;
    	case CLOSED: return 2;
    	case BLOCKED: return 3;
    	case BLACKLISTED: return 4;
    	}
    	return 0;
    }

}
