package dev.all_things.reference.app.model.user;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum(value = Integer.class)
public enum UserStatus
{
	@XmlEnumValue("0") UNKNOWN,
	@XmlEnumValue("1") CREATED,
	@XmlEnumValue("2") ACTIVE;

	private static final UserStatus[] values = UserStatus.values();
	private static final int LENGTH = values.length;

	public static UserStatus get(final int index)
	{
		return index > 0 && index < LENGTH ? values[index] : UNKNOWN;
	}
}