package com.telstra.codechallenge.git.common;


public enum Since {
	DAILY("daily"), WEEKLY("weekly"), MONTHLY("monthly");
    
	private String value;
    
	Since(String value){
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
	public static void main(String[] args) {
	  System.out.println(Since.DAILY.value());
	}
}