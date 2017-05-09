package ru.bmn.web.hsdb.site.controller.domain;

public class UserRegistrationForm {
	private String name;
	private String password;
	private String passwordConfirm;
	private String hearthpwnName;
	private String email;

	public String getName() {
		return name;
	}

	public UserRegistrationForm setName(String name) {
		this.name = name;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserRegistrationForm setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public UserRegistrationForm setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
		return this;
	}

	public String getHearthpwnName() {
		return hearthpwnName;
	}

	public UserRegistrationForm setHearthpwnName(String hearthpwnName) {
		this.hearthpwnName = hearthpwnName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserRegistrationForm setEmail(String email) {
		this.email = email;
		return this;
	}
}
