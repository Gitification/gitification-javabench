package ch.mse.osf.gitification.benchmark;

public abstract class Benchmark implements CallableBenchmark{
	
	public void callBenchmark() {
		try{
			benchmark();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	

}
