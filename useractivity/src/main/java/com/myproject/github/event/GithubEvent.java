package com.myproject.github.event;

public class GithubEvent {
	
	private String id;
	private String type;
	private Actor actor;
	private Repository repo;
	private Payload payload;
	private boolean isPublic;
	private String created_at;
	
	public String getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public Actor getActor() {
		return actor;
	}
	public Repository getRepo() {
		return repo;
	}
	public Payload getPayload() {
		return payload;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public String getCreated_at() {
		return created_at;
	}
	
	
}
