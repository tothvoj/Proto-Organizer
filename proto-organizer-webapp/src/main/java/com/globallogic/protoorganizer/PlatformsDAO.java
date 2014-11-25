package com.globallogic.protoorganizer;

import java.util.List;

import com.globallogic.protoorganizer.model.Platform;

public interface PlatformsDAO {
		
	public List<Platform> getPlatformsList();	
	public void insertPlatform(Platform platform);
	public void updatePlatform(Platform platform);
	public void deletePlatform(long id);
	public void deleteBatch(final List<Long> ids);
		
}
