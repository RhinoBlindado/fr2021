public class Alarma implements Comparable<Alarma>{
	private int hora;
	private int min;

	public Alarma(int h, int m){
		this.hora = h;
		this.min = m;
	}

	public int getSegundoAlarma(){
		return hora*3600 + min*60;
	}

	@Override
  public int compareTo(Alarma o) {
      if (hora < o.hora) return -1;
			if (hora > o.hora) return 1;
			if (min  < o.min)  return -1;
			if (min  > o.min)  return 1;
      return 0;
  }

	public String toString(){
		String mStr = new String();
		String hStr = new String();

		if(min<10) mStr = "0" + min;
		else	mStr = "" + min;

		if(hora<10) hStr = "0" + hora;
		else	hStr = "" + hora;

		return (hStr + ":" + mStr);
	}

};
