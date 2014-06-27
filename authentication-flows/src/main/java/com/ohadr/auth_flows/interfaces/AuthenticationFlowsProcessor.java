package com.ohadr.auth_flows.interfaces;

import java.util.Date;

import com.ohadr.auth_flows.types.AccountState;
import com.ohadr.auth_flows.types.AuthenticationPolicy;
import com.ohadr.auth_flows.web.AuthenticationFlowsException;


/**
 * manages the data like user's account lock, or number of login-retries, etc.
 * @author OhadR
 *
 */
public interface AuthenticationFlowsProcessor
{
	/**
	 * @param email
	 * @param password
	 * @param retypedPassword
	 * @param firstName: first name of the registered user.
	 * @param lastName: second (last) name of the registered user.
	 * @param path: the server-path. used for building the link in the email.
	 * @throws AuthenticationFlowsException
	 */
	public void createAccount(
			String email,
			String password,
			String retypedPassword,
			String firstName, 
			String lastName, 
			String path) throws AuthenticationFlowsException;


	/**
	 * 
	 * @param email
	 * @param serverPath - the server-path. used for building the link in the email
	 * @throws AuthenticationFlowsException
	 */
	public void handleForgotPassword( String email, String serverPath ) 
			throws AuthenticationFlowsException;
	
	public void handleSetNewPassword( 
			String encUserAndTimestamp,
			String password,
			String retypedPassword) throws AuthenticationFlowsException;
	

	/**
	 * changes the user password. user must be logged in when he calls this method. 
	 * from security reasons, user must provide with the old password.
	 * @param currentPassword
	 * @param newPassword
	 * @param retypedPassword
	 * @param encUser
	 * @throws AuthenticationFlowsException
	 */
	public void handleChangePassword( 
			String currentPassword,
			String newPassword,
			String retypedPassword,
			String encUser) throws AuthenticationFlowsException;

	
	/**
	 * 
	 * @param username
	 * @return boolean, passChangeRequired. true if change password is required.
	 */
	public boolean setLoginSuccessForUser(String username);


	public AuthenticationPolicy getAuthenticationSettings();

	public AccountState getAccountState(String email);


	public String getSecretAnswer(String email);

	public void setLoginFailureForUser(String email);

	public void sendUnlockAccountMail(String email, 
			String serverPath);

	public void setPassword(String email, String encodedPassword);
	
	Date getPasswordLastChangeDate(String email);


	public void setEnabled(String userEmail);

	/**
	 * NOT IMPLEMENTED:
	 * @param email
	 * @return
	 * 
	public String getSecretQuestion(String email);
		 */

	public void validatePassword(String password,
			AuthenticationPolicy settings) throws AuthenticationFlowsException;
	
	public void validateRetypedPassword(String password, String retypedPassword) throws AuthenticationFlowsException;

}
