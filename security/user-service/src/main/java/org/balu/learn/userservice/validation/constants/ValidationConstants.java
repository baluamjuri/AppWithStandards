package org.balu.learn.userservice.validation.constants;

/**
 * Validations related constants.
 * 
 * @author amjuribv
 *
 */
public final class ValidationConstants {
	public static final String USERNAME = "[a-zA-Z]{3,25}";
	public static final String USERNAME_MSG = "{Pattern.username.message}";

	public static final String DISPLAYNAME = "[A-Za-z ]{3,25}";
	public static final String DISPLAYNAME_MSG = "{Pattern.displayName.message}";

	public static final String MOBILE_MSG = "{Size.mobile.message}";

	public static final String NOPROFILE_MSG = "{NoProfile.message}";

	public static final String CREDENTIALS_MSG = "{Credentials.message}";

	public static final String PERMISSION_ASSIGNED_ALREADY_MSG = "{AddPermissionToRoleValidation.message}";
	
	public static final String PERMISSION_NOT_YET_ASSIGNED_MSG = "{DeletePermissionFromRoleValidation.message}";
	
	public static final String ROLE_ASSIGNED_ALREADY_MSG = "{AssignRoleToUserValidation.message}";
	
	public static final String ROLE_NOT_YET_ASSIGNED_MSG = "{DeleteRoleFromUserValidation.message}";
	
	private ValidationConstants() {
	}
}
