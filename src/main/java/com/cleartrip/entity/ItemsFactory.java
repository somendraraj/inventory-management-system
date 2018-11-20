package com.cleartrip.entity;

public class ItemsFactory {
	
	public static Class<? extends Items> getClazz(String name) {

		try {
			return (Class<? extends Items>) Class.forName("com.cleartrip.entity." + name);

		} catch (ClassNotFoundException e) {
			return null;
		}

	}
}
