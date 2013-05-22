package ch.mse.osf.gitification.benchmark;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Benchmark implements CallableBenchmark{
	
	protected PrintWriter out = null;
	
	public Benchmark() throws IOException {
		File d = new File("logs/");
		d.mkdirs();
		File f = new File(d, this.getClass().getName());
		out = new PrintWriter(new FileWriter(f));
	}
	
	public boolean callBenchmark() {
		try{
			benchmark();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	

}
