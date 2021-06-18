package braces.server.core;

import braces.Exchanger.ClientExchanger;

public class Exchanger {
	public static ClientExchanger exchangeClass = new ClientExchanger();

	public static ClientExchanger getExchangeClass() {
		return exchangeClass;
	}

	public static void setExchangeClass(ClientExchanger exchangeClass) {
		Exchanger.exchangeClass = exchangeClass;
	}
}
