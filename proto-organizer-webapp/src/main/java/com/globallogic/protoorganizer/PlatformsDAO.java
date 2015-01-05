package com.globallogic.protoorganizer;

import java.util.List;

import com.globallogic.protoorganizer.model.Platform;

public interface PlatformsDAO {
		
	public List<Platform> getPlatformsList();	
	public List<Platform> getActiveChildPlatforms(boolean isActive);
	public void insertPlatform(Platform platform);
	public void updatePlatform(Platform platform);
	public void deletePlatform(long id);
	public void deleteBatch(final List<Long> ids);
	public void deactivateBatch(final List<Long> ids, boolean isActive);
	public List<Platform> getChildPlatforms();
	public List<Platform> getMasterPlatforms();
		
}
