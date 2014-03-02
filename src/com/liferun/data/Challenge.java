package com.liferun.data;

public class Challenge {

	private String name;
	private String description;
	private String difficulty;
	private String point;
	private int interestId;
	
	public Challenge(String name, String description, String difficulty,
			String point, int interestId) {
		super();
		this.name = name;
		this.description = description;
		this.difficulty = difficulty;
		this.point = point;
		this.interestId = interestId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public String getPoint() {
		return point;
	}

	public int getInterestId() {
		return interestId;
	}
	
	
	
}
