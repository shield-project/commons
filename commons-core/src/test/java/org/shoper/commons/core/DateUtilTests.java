package org.shoper.commons.core;

import org.junit.Test;

public class DateUtilTests
{
	@Test
	public void TimeToStr_Test() throws InterruptedException
	{
		System.out.println(DateUtil.timeToStr(System.currentTimeMillis()));
	}
}
