package com.cl.common.util.uuid;

import java.util.UUID;


/**
 * 
 * @author cl 2018年3月20日
 *
 */

public class UUIDUtil {
	
	/**
	 * 随机一个32UUID
	 * @return
	 */
	public static String randomUUID(){
		return UUID.randomUUID().toString();
	}
}
