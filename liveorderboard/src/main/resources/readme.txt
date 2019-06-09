1. LiveBoardImp is the main file where the functionality is implemented.
2. Tuneing is required for Object pool based on the actual load.
3. Assumption is - This is low latency system, so all objects are crated at the system (Object pooling pattern) start to avoid GC overhead as well.