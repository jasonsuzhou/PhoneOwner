package com.mh.redis;

import io.lettuce.core.dynamic.Commands;
import io.lettuce.core.dynamic.annotation.Command;
import io.lettuce.core.dynamic.batch.BatchSize;

@BatchSize(100)
public interface BatchSetCommands extends Commands {

	@Command("SETNX")
	void set(String key, String value);
	
}
