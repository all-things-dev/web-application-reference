package dev.all_things.reference.app.model.user;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum(value = Integer.class)
public enum UserRoleType
{
	@XmlEnumValue("0") UNKNOWN,
	@XmlEnumValue("1") SYSTEM;

	private static final UserRoleType[] values = UserRoleType.values();
	private static final int LENGTH = values.length;

	public static UserRoleType get(final int index)
	{
		return index > 0 && index < LENGTH ? values[index] : UNKNOWN;
	}
}