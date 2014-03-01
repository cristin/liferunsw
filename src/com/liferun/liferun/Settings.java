package com.liferun.liferun;

public class Settings {
	
	private static Settings instance;
	private String apiHost						= "";//"sms.api.klsolution.com:3001";
	private String apiProtocol					= "http";
	private String apiSecureProtocol			= "https";
	private String apiKey						= "test";
	private long refreshRate					= 0;	//millis = 60 s
	private int delayInt						= 0;
	private int operator;
	public static int APP_STATE_IDLE			= 1;
	public static int APP_STATE_REFRESH			= 2;
	public static int APP_STATE_SEND_SMS		= 3;
	public static final String PREF_APPLICATION = "PREF_LIFERUN";
	
	private Settings(){
		
	}
	public static Settings getInstance(){
		if(instance == null )
			instance = new Settings();
		return instance;
	}
	
	public String getApiProtocol() {
		return apiProtocol;
	}
	public String getApiHost() {
		return apiHost;
	}
	public String getApiSecureProtocol() {
		return apiSecureProtocol;
	}
	public long getRefreshRate() {
		return refreshRate;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public void setRefreshRate(long refreshRate) {
		this.refreshRate = refreshRate;
	}
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	public int getDelayInt() {
		return delayInt;
	}
	public void setDelayInt(int delayInt) {
		this.delayInt = delayInt;
	}
}
