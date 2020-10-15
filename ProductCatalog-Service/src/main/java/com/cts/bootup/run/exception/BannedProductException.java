
package com.cts.bootup.run.exception;

public class BannedProductException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4509561012775565001L;

	public BannedProductException() {
		super();
	}
	
	public BannedProductException(String message) {
		super(message);
	}
	
	public BannedProductException(Throwable throwable) {
		super(throwable);
	}
	
	public BannedProductException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
