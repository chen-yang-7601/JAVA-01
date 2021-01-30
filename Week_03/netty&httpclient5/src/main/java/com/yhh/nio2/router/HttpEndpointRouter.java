package com.yhh.nio2.router;

import java.util.List;

public interface HttpEndpointRouter {
	String route(List<String> endpoints);
	
	// Random
	// Server01, 0.2
	// Server02, 0.3
	// Server03, 0.5
}
