all: ej1 ej2 ej3 ej4 ej5

ej1: Ejercicio1/YodafyClienteTCP.class Ejercicio1/YodafyServidorIterativo.class

ej2: Ejercicio2/YodafyClienteTCP.class Ejercicio2/YodafyServidorIterativo.class

ej3: Ejercicio3/YodafyClienteTCP.class Ejercicio3/YodafyServidorConcurrente.class

ej4: Ejercicio4/YodafyClienteUDP.class Ejercicio4/YodafyServidorIterativo.class

ej5: Ejercicio5/codigo/RelojClienteTCP.class Ejercicio5/codigo/RelojServidorConcurrente.class

Ejercicio1/YodafyClienteTCP.class: Ejercicio1/YodafyClienteTCP.java
	javac -cp Ejercicio1/ $<

Ejercicio1/YodafyServidorIterativo.class: Ejercicio1/YodafyServidorIterativo.java Ejercicio1/ProcesadorYodafy.java
	javac -cp Ejercicio1/ $<

Ejercicio2/YodafyClienteTCP.class: Ejercicio2/YodafyClienteTCP.java
	javac -cp Ejercicio2/ $<

Ejercicio2/YodafyServidorIterativo.class: Ejercicio2/YodafyServidorIterativo.java Ejercicio2/ProcesadorYodafy.java
	javac -cp Ejercicio2/ $<

Ejercicio3/YodafyClienteTCP.class: Ejercicio3/YodafyClienteTCP.java
		javac -cp Ejercicio3/ $<

Ejercicio3/YodafyServidorConcurrente.class: Ejercicio3/YodafyServidorConcurrente.java Ejercicio3/ProcesadorYodafy.java
		javac -cp Ejercicio3/ $<

Ejercicio4/YodafyClienteUDP.class: Ejercicio4/YodafyClienteUDP.java
		javac -cp Ejercicio4/ $<

Ejercicio4/YodafyServidorIterativo.class: Ejercicio4/YodafyServidorIterativo.java Ejercicio4/ProcesadorYodafy.java
		javac -cp Ejercicio4/ $<

Ejercicio5/codigo/RelojClienteTCP.class: Ejercicio5/codigo/RelojClienteTCP.java
		javac -cp Ejercicio5/codigo $<

Ejercicio5/codigo/RelojServidorConcurrente.class: Ejercicio5/codigo/RelojServidorConcurrente.java Ejercicio5/codigo/ProcesadorReloj.java Ejercicio5/codigo/Alarma.java
		javac -cp Ejercicio5/codigo $<

clean:
	rm Ejercicio*/*.class
	rm Ejercicio5/codigo/*class
