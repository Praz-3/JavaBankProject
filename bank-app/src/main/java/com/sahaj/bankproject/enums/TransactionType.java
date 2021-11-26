package com.sahaj.bankproject.enums;

import com.sahaj.bankproject.exceptions.InvalidTransactionTypeException;

public enum TransactionType {
	DEPOSIT, WITHDRAW, TRANSFER;
	
	public static TransactionType intToType(int input) throws InvalidTransactionTypeException{
		switch(input) {
		case 1: return DEPOSIT;
		case 2: return WITHDRAW;
		case 3: return TRANSFER;
		default: throw new InvalidTransactionTypeException(input + " is not a valid Transaction Type, must be among 1, 2 or 3");
		}
	}

    public static int typeToInt(TransactionType t) {
    	switch(t) {
    	case DEPOSIT: return 1;
    	case WITHDRAW: return 2;
    	case TRANSFER: return 3;
    	}
    	return 0;
    }

}