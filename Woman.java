public class Woman extends Person {
	private boolean free;

	public Woman(String n){
		super(n);
		free = true;
	}

	public boolean isFree(){
		return this.free;
	}

	public void setFree(boolean f){
		this.free = f;
	}
}