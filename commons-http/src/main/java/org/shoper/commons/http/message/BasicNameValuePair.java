//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.shoper.commons.http.message;

import org.apache.http.NameValuePair;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.util.Args;
import org.apache.http.util.LangUtils;

import java.io.Serializable;
import java.util.Objects;

@Contract(
		threading = ThreadingBehavior.IMMUTABLE
)
public class BasicNameValuePair implements NameValuePair, Cloneable, Serializable {
	private static final long serialVersionUID = -6437800749411518984L;
	private final String name;
	private final String value;

	public BasicNameValuePair(String name, String value) {
		this.name = (String) Args.notNull(name, "Name");
		this.value = value;
	}

	public String getName () {
		return this.name;
	}

	public String getValue () {
		return this.value;
	}

	public String toString () {
		if (Objects.isNull(this.value))
			return this.name;
		else {
			int len = this.name.length() + 1 + this.value.length();
			StringBuilder buffer = new StringBuilder(len);
			buffer.append(this.name);
			buffer.append("=");
			buffer.append(this.value);
			return buffer.toString();
		}
	}

	public boolean equals (Object object) {
		if (this == object) {
			return true;
		} else if (!(object instanceof NameValuePair)) {
			return false;
		} else {
			BasicNameValuePair that = (BasicNameValuePair) object;
			return this.name.equals(that.name) && LangUtils.equals(this.value, that.value);
		}
	}

	public int hashCode () {
		byte hash = 17;
		int hash1 = LangUtils.hashCode(hash, this.name);
		hash1 = LangUtils.hashCode(hash1, this.value);
		return hash1;
	}

	public Object clone () throws CloneNotSupportedException {
		return super.clone();
	}
}
