package com.liferun.definitions;

import com.liferun.definitions.QBQueriesDef.QBQueryType;
import com.quickblox.internal.core.rest.RestResponse;

public interface ActionResultDelegate {

	void completedWithResult(QBQueryType queryType,
			com.liferun.objects.RestResponse response); 
}
