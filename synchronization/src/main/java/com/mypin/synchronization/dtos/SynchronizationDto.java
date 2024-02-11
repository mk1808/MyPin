package com.mypin.synchronization.dtos;

public class SynchronizationDto {
	
	public String channel;
	public String content;
	
	@Override
	public String toString() {
		return "SynchronizationDto [channel=" + channel + ", content=" + content + "]";
	}
}
